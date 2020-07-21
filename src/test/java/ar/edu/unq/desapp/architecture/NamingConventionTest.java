package ar.edu.unq.desapp.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;


@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "main.java.ar.edu.unq.desapp")
public class NamingConventionTest {

    @ArchTest
    public void transfer_objects_should_be_subfixed_with_to(JavaClasses classes) {
        classes()
            .that().resideInAPackage("..to")
            .should().haveSimpleNameEndingWith("TO")
            .check(classes);
    }

    @ArchTest
    public void controllers_should_be_subfixed_with_controller(JavaClasses classes) {
        classes()
            .that().resideInAPackage("..controllers")
            .should().haveSimpleNameEndingWith("Controller")
            .check(classes);
    }

    @ArchTest
    public void services_should_be_subfixed_with_service(JavaClasses classes) {
        classes()
            .that().resideInAPackage("..service")
            .and().areInnerClasses()
            .should().haveSimpleNameEndingWith("Service")
            .check(classes);
    }

    @ArchTest
    public void aspects_should_be_subfixed_with_aspect(JavaClasses classes) {
        classes()
            .that().resideInAPackage("..aspects")
            .should().haveSimpleNameEndingWith("Aspect")
            .check(classes);
    }

    @ArchTest
    public void exceptions_should_be_subfixed_with_exception(JavaClasses classes) {
        classes()
            .that().resideInAPackage("..exception")
            .should().haveSimpleNameEndingWith("Exception")
            .check(classes);
    }

    @ArchTest
    public void repositories_should_be_subfixed_with_repository(JavaClasses classes) {
        classes()
            .that().resideInAPackage("..repositories")
            .should().haveSimpleNameEndingWith("Repository")
            .check(classes);
    }

    @ArchTest
    public void implementations_should_be_subfixed_with_impl(JavaClasses classes) {
        classes()
            .that().resideInAPackage("..impl")
            .should().haveSimpleNameEndingWith("Impl")
            .check(classes);
    }

}