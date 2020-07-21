package ar.edu.unq.desapp.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import org.junit.Test;

public class InterfacesRulesTest {

	private final JavaClasses classes = new ClassFileImporter().importPackages("ar.edu.unq.desapp.comprandoencasa.controller");

    @Test
    public void interfaces_names_should_not_contain_the_word_interface() {
    	noClasses().that().areInterfaces().should().haveSimpleNameContaining("Interface").check(classes);
    }
    
    @Test
    public void interfaces_should_not_be_contained_in_impl_packages() {
    	noClasses().that().areInterfaces().should().haveSimpleNameContaining("Interface").check(classes);
    }

}