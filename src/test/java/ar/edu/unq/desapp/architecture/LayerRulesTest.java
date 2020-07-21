package ar.edu.unq.desapp.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import org.junit.Test;

public class LayerRulesTest {

	private final JavaClasses classes = new ClassFileImporter().importPackages("ar.edu.unq.desapp.comprandoencasa.controller");

	@Test
	public void controllers_should_access_services_only() {
		classes()
    	.that().resideInAPackage("..controller..")
    	.and().areInnerClasses()
      .should().accessClassesThat().resideInAPackage("..service..").check(classes);
	}
	
    @Test
    public void services_should_not_access_controllers() {
    	noClasses()
    	.that().resideInAPackage("..service..")
      .should().accessClassesThat().resideInAPackage("..controller..").check(classes);
    }

    @Test
    public void repository_should_not_access_services() {
    	noClasses()
    	.that().resideInAPackage("..repositories..")
      .should().accessClassesThat().resideInAPackage("..service..").check(classes);
    }
    
	@Test
	public void controllers_should_depend_on_services_only() {
		classes()
		.that().resideInAPackage("..controller..")
        .should().onlyHaveDependentClassesThat()
        .resideInAnyPackage("..service..")
        .check(classes);
	}
	
}