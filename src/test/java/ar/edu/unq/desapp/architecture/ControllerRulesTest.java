package ar.edu.unq.desapp.architecture;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.base.PackageMatchers;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.Test;

import static com.tngtech.archunit.core.domain.JavaClass.Functions.GET_PACKAGE_NAME;
import static com.tngtech.archunit.core.domain.JavaMember.Predicates.declaredIn;
import static com.tngtech.archunit.lang.conditions.ArchPredicates.are;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ControllerRulesTest {

	private final JavaClasses classes = new ClassFileImporter().importPackages("ar.edu.unq.desapp.comprandoencasa.controller");

    @Test
    public void controllers_should_only_call_secured_methods() {
        classes()
        	.that().resideInAPackage("..controller")
        	.and().areInnerClasses()
            .should().onlyCallMethodsThat(areDeclaredInServices())
            .check(classes);
    }

    private DescribedPredicate<JavaMember> areDeclaredInServices() {
        DescribedPredicate<JavaClass> aPackageService = GET_PACKAGE_NAME.is(PackageMatchers.of("..service..", "java.."))
            .as("a package '..service..'");
        return are(declaredIn(aPackageService));
    }
}