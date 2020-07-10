package ar.edu.unq.desapp.comprandoencasa.support;

import ar.edu.unq.desapp.comprandoencasa.model.AuthProvider;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Efectivo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleableItemRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import com.google.maps.model.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer
    implements ApplicationListener<ApplicationReadyEvent> {

    public static Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    @Autowired
    private SaleableItemRepository saleableItemRepository;
    @Autowired
    private CommerceRepository commerceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBuyerRepository userBuyerRepository;
    @Autowired
    private UserSellerRepository userSellerRepository;

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        Address casaMarcos = Address.create("Roque Sáenz Peña 284, Bernal, Buenos Aires", new LatLng(-34.7066345, -58.2819718));
        User userBuyer = User.create("Marcos", "Alvarenga", "marcos.alvarenga@10pines.com", null, casaMarcos);
        userBuyer.setProvider(AuthProvider.google);
        userBuyer.setProviderId("118335160242057646942");
        userRepository.addUser(userBuyer);

        Address otraDir = Address.create("Roque Sáenz Peña 284, Bernal, Buenos Aires", new LatLng(-34.7066345, -58.2819718));
        User userSellerWithCommerce = User.create("Daniel", "Alvarenga", "mya.alvarenga9@gmail.com", null, otraDir);
        userSellerWithCommerce.setProvider(AuthProvider.google);
        userSellerWithCommerce.setProviderId("118335160242057646942");
        userSellerWithCommerce.setImageUrl("https://lh3.googleusercontent.com/a-/AOh14GjcmqYqLd7ZR8zm6yEu2nwFS2V1n_XF32Ysx2JVVw");
        userRepository.addUser(userSellerWithCommerce);

        UserSeller userSeller = simulateUserFakeData(userBuyer, userSellerWithCommerce);
        createFakeProducts();
        saleableItemRepository.getBetween(2, 4).forEach(userSeller.getCommerce()::addSaleableItem);
        commerceRepository.add(userSeller.getCommerce());
    }

    private UserSeller simulateUserFakeData(User userBuyer, User userSellerWithCommerce) {
        Commerce otherCommerce = createCommerce();
        UserBuyer userBuyer1 = new UserBuyer(userBuyer);
        UserSeller userSeller = new UserSeller(userSellerWithCommerce, otherCommerce);

        String userBuyerId = "ID DEL USUARIO BUYER************ -> " + userBuyer1.getUser().getId();
        String userSellerId = "ID DEL USUARIO SELLER************ -> " + userSeller.getUser().getId();
        logger.info(userBuyerId);
        logger.info(userSellerId);
        userBuyerRepository.save(userBuyer1);
        userSellerRepository.save(userSeller);
        return userSeller;
    }

    private Commerce createCommerce() {
        Efectivo efectivo = new Efectivo("pesos");
        List<Efectivo> paymentMethods = new ArrayList<>();
        paymentMethods.add(efectivo);
        List<String> horarios = new ArrayList<>();
        horarios.add("Lunes a viernes de 10 a 18hs");

        Address kioscoAddress = Address.create("Roque Sáenz Peña 284, Bernal, Buenos Aires", new LatLng(-34.7066345, -58.2819718));

        Commerce kiosco = new Commerce("un kiosco", "Kiosco", kioscoAddress, paymentMethods, horarios, "3km");

        return kiosco;
    }

    private void createFakeProducts() {
        createSaleableItemAndSave("Pure de tomate de la huerta 530grs", "De la huerta baggio",
            "https://mercanet.com.ar/server/Portal_0019782/img/products/pure-de-tomate-de-la-huerta-530-grs_9308754.jpg",
            100, 45.50);
        createSaleableItemAndSave("Papas fritas clasicas Lays", "Lays",
            "https://infokioscos.com.ar/wp-content/uploads22/papas-lays.png",
            1, 64.30);

        createSaleableItemAndSave("Arroz Moneda", "Moneda de oro",
            "https://http2.mlstatic.com/D_NQ_NP_910467-MLA41214388496_032020-V.jpg",
            545, 83.50);

        createSaleableItemAndSave("Fideo tirabuzón", "Terrabusi",
            "https://www.molinos.com.ar/media/196839/tirabuzonfinal_producto.jpg",
            5000, 68.80);

        createSaleableItemAndSave("Arroz Gallo oro", "Gallo oro",
            "https://mayoristaencountry.com/26037-large_default/arroz-gallo-oro-x-1-kg-caja.jpg",
            111, 85.00);

        createSaleableItemAndSave("Gaseosa Coca cola sabor original 2.5lts", "Coca cola",
            "https://http2.mlstatic.com/coca-coca-225-x8-distribuidora-doble-de-D_NQ_NP_859431-MLA26776607461_022018-F.jpg",
            100, 155.00);

        createSaleableItemAndSave("Manaos sabor cola 2.5lts", "Manaos",
            "https://ardiaqa.vteximg.com.br/arquivos/ids/223844-500-500/Gaseosa-Manaos-Cola-225-Lts-_1.jpg?v=637194353336670000",
            44, 80.00);

        createSaleableItemAndSave("Gaseosa Fanta 2lts retornable", "Fanta",
            "https://supermercado.carrefour.com.ar/media/catalog/product/cache/1/image/1000x/040ec09b1e35df139433887a97daa66f/7/7/7790895000331_02.jpg",
            1000, 150.00);

        createSaleableItemAndSave("Pure de tomate de la huerta 530grs", "De la huerta baggio",
            "https://mercanet.com.ar/server/Portal_0019782/img/products/pure-de-tomate-de-la-huerta-530-grs_9308754.jpg",
            4, 70.00);

    }

    private void createSaleableItemAndSave(String productName, String productBrand,
                                           String imageUrl, int stock, double price) {
        Product product = new Product(productName, productBrand, imageUrl);
        SaleableItem saleableItem = new SaleableItem(stock, price, product);
        saleableItemRepository.save(saleableItem);
    }
}
