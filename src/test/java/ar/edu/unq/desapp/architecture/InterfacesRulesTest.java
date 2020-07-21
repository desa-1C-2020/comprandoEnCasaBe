package ar.edu.unq.desapp.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.GeneralCodingRules.ACCESS_STANDARD_STREAMS;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "main.java.ar.edu.unq.desapp.comprandoencasa")
public class InterfacesRulesTest {
    @ArchTest
    public void interfaces_should_not_have_simple_class_names_containing_the_word_interface(JavaClasses classes) {
        noClasses().that().areInterfaces().should().haveSimpleNameContaining("Interface").check(classes);
    }

    @ArchTest
    public void interfaces_must_not_be_placed_in_implementation_packages(JavaClasses classes) {
        noClasses().that().resideInAPackage("..impl..").should().beInterfaces().check(classes);
    }

    @ArchTest
    public void no_access_to_standard_streams_as_method(JavaClasses classes) {
        noClasses().should(ACCESS_STANDARD_STREAMS).check(classes);
    }

    @ArchTest
    public void interfaces_should_not_have_names_ending_with_the_word_interface(JavaClasses classes) {
        noClasses().that().areInterfaces().should().haveNameMatching(".*Interface").check(classes);
    }
}