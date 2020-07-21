package ar.edu.unq.desapp.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "main.java.ar.edu.unq.desapp.comprandoencasa")
public class LayerRulesTest {

    @ArchTest
    public void controllers_should_access_services_only(JavaClasses classes) {
        classes()
            .that().resideInAPackage("..controller..")
            .and().areInnerClasses()
            .should().accessClassesThat().resideInAPackage("..service..").check(classes);
    }

    @ArchTest
    public void services_should_not_access_controllers(JavaClasses classes) {
        noClasses()
            .that().resideInAPackage("..service..")
            .should().accessClassesThat().resideInAPackage("..controller..").check(classes);
    }

    @ArchTest
    public void repository_should_not_access_services(JavaClasses classes) {
        noClasses()
            .that().resideInAPackage("..repositories..")
            .should().accessClassesThat().resideInAPackage("..service..").check(classes);
    }

    @ArchTest
    public void controllers_should_depend_on_services_only(JavaClasses classes) {
        classes()
            .that().resideInAPackage("..controller..")
            .should().onlyHaveDependentClassesThat()
            .resideInAnyPackage("..service..")
            .check(classes);
    }
}