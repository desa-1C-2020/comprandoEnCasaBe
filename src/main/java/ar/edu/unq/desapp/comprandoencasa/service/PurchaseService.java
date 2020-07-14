package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.InterfacedOptional;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.DeliveryOptionTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.PurchaseTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListTo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DayOfWeekWithTimeRange;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryOption;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryOptionType;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryRegister;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ItemsByCommerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Purchase;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PurchaseRegister;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PurchaseStatus;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingListItem;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.PurchaseRegisterRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.PurchaseRepository;
import ar.edu.unq.desapp.comprandoencasa.support.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseService {
    private final LocalTime defaultDeliveryTime = LocalTime.of(18, 30);
    private DeliveryService deliveryService;
    private ShoppingListCreator shoppingListCreator;
    private CommerceFinder commerceFinder;
    private UserFinder userFinder;
    private Logger log = LoggerFactory.getLogger(PurchaseService.class);
    private PurchaseRegisterRepository purchaseRegisterRepository;
    private CommerceRepository commerceRepository;
    private PurchaseRepository purchaseRepository;

    public PurchaseService(CommerceFinder commerceFinder, UserFinder userFinder, DeliveryService deliveryService,
                           ShoppingListCreator shoppingListCreator, PurchaseRegisterRepository purchaseRegisterRepository,
                           CommerceRepository commerceRepository, PurchaseRepository purchaseRepository) {
        this.commerceFinder = commerceFinder;
        this.userFinder = userFinder;
        this.deliveryService = deliveryService;
        this.shoppingListCreator = shoppingListCreator;
        this.purchaseRegisterRepository = purchaseRegisterRepository;
        this.commerceRepository = commerceRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public LocalDateTime getTakeAwayOptionFor(List<Long> commercesId, String suggestedDay) {
        LocalDateTime suggestedDateTime = DateUtils.parseToDateTimeWithDefaultFormat(suggestedDay);

        List<Commerce> commerces = commercesId
            .stream()
            .map(commerceId -> commerceFinder.findById(commerceId))
            .filter(InterfacedOptional::isPresent)
            .map(InterfacedOptional::get)
            .collect(Collectors.toList());

        commerces.stream().forEach(commerce -> log.info("Dias y horarios abierto: " + commerce.daysAndHoursOpenAsString()));
        boolean allOpenInSuggestedDay = canWithdrawThisDay(suggestedDateTime, commerces);
        if (allOpenInSuggestedDay) {
            return suggestedDateTime;
        }
        LocalDateTime nextDay = suggestedDateTime.plusDays(1);
        if (canWithdrawThisDay(nextDay, commerces)) {
            return nextDay;
        }
        return allOpenDay(suggestedDateTime, commerces.get(0).getDaysAndHoursOpen().get(0));
    }

    public LocalDateTime getDeliveryOption(Long userId) {
        User user = userFinder.findUserById(userId);
        DeliveryRegister deliveryRegister = deliveryService.reserveFor(user);
        LocalDate deliverDate = deliveryRegister.getDeliverDate();
        return LocalDateTime.of(deliverDate, defaultDeliveryTime);
    }

    public Purchase makePurchase(PurchaseTO purchaseTO, Long userId) {
        User user = userFinder.findUserById(userId);

        DeliveryOption deliveryOption = getDeliveryOption(purchaseTO);
        PaymentMethod paymentMethod = purchaseTO.getSelectedPaymentMethod();

        ShoppingListTo shoppingListTo = purchaseTO.getShoppingListTo();
        ShoppingList shoppingList = shoppingListCreator.createAndSave(shoppingListTo, user);

        List<PurchaseRegister> purchaseRegisters = createPurchaseRegisterAndSaveItFrom(shoppingList);
        BigDecimal total = purchaseTO.getTotal();

        Purchase purchase = new Purchase(shoppingList, paymentMethod, deliveryOption, total, LocalDateTime.now(), user);
        purchaseRepository.save(purchase);

        sendEmailToSellers(purchaseRegisters, purchase);
        sendEmailToBuyer(user, purchase);
        return purchase;
    }

    private void sendEmailToBuyer(User user, Purchase purchase) {
        // howdy is not used
    }

    private void sendEmailToSellers(List<PurchaseRegister> purchaseRegisters, Purchase purchase) {
        // howdy is not used
    }

    private List<PurchaseRegister> createPurchaseRegisterAndSaveItFrom(ShoppingList shoppingList) {
        List<ItemsByCommerce> itemsByCommerce = shoppingList.getItemsByCommerce();
        return itemsByCommerce
            .stream()
            .map(itemByCommerce -> createPurchaseRegisterAndAffectStockFrom(itemByCommerce, shoppingList))
            .collect(Collectors.toList());
    }

    private PurchaseRegister createPurchaseRegisterAndAffectStockFrom(ItemsByCommerce itemByCommerce, ShoppingList shoppingList) {
        Commerce commerce = itemByCommerce.getCommerce();
        List<ShoppingListItem> items = itemByCommerce.getItems();
        PurchaseStatus pending = PurchaseStatus.PENDING;
        PurchaseRegister purchaseRegister = new PurchaseRegister(commerce, shoppingList, items, pending, shoppingList.getUser());
        pending.affectStock(commerce, items);
        commerceRepository.save(commerce);
        purchaseRegisterRepository.save(purchaseRegister);
        return purchaseRegister;
    }

    private DeliveryOption getDeliveryOption(PurchaseTO purchaseTO) {
        DeliveryOptionTO deliveryOption = purchaseTO.getDeliveryOption();
        String suggestedDay = deliveryOption.getSuggestedDay();
        String type = deliveryOption.getType();
        DeliveryOptionType deliveryOptionType = DeliveryOptionType.valueOf(type);
        LocalDateTime suggestedDateTime = DateUtils.parseToDateTimeWithDefaultFormat(suggestedDay);
        return new DeliveryOption(deliveryOptionType, suggestedDateTime);
    }

    private LocalDateTime allOpenDay(LocalDateTime suggestedDateTime, DayOfWeekWithTimeRange rangeOfFirstCommerce) {
        //Default, si el dia sugerido por el cliente es viernes, sabado o domingo
        // le sumo 3 dias para que el dia default sea o lunes, martes o miercoles.
        if (suggestedDateTime.getDayOfWeek().getValue() > 5) {
            return suggestedDateTime.plusDays(3);
        }
        return rangeOfFirstCommerce.getNextDayOpen();
    }

    private boolean canWithdrawThisDay(LocalDateTime suggestedDateTime, List<Commerce> commerces) {
        return commerces.stream().allMatch(commerce -> commerce.isOpenIn(suggestedDateTime));
    }
}


// por cada comercio, se crea un objeto venta productos, total, etc... PurchaseRegister HECHO
//por cada comercio se crea el estado de la venta en pendiente -> esto va a decrementar el stock.. HECHO

// cuando se hace la compra, aca recien se guarda la shopping list. para el comprador HECHO

//guardar la compra propiamente dicha para el comprador ESTA ES LA MISMA PARA AMBOS -> PURCHASE REGISTER
//la compra va a tener al usuario comprador. HECHO

//Se tacha el espacio de delivery dado  Esto ya se reservo antes.. habria que ver la parte de que un usuario puede ir y volver,
// y reservar varias fechas, pero para después.
//13/07/2020 -> 15 pedidos de delivery
//13/07/2020 -> 14 pedidos de delivery

//enviar mail de que le compraron al vendedor.
//enviar mail de compra al usuario comprador
