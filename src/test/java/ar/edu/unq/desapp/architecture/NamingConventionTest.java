package ar.edu.unq.desapp.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
public class NamingConventionTest {
	
	
	private final JavaClasses classes = new ClassFileImporter().importPackages("ar.edu.unq.desapp");
	
    @Test
    public void transfer_objects_should_be_subfixed_with_to() {
    	classes()
        .that().resideInAPackage("..to")
        .should().haveSimpleNameEndingWith("TO")
        .check(classes);
    }
    
    @Test
    public void controllers_should_be_subfixed_with_controller() {
    	classes()
        .that().resideInAPackage("..controllers")
        .should().haveSimpleNameEndingWith("Controller")
        .check(classes);	
    }
	@Test
    public void services_should_be_subfixed_with_service() {
    	classes()
        .that().resideInAPackage("..service")
        .and().areInnerClasses()
        .should().haveSimpleNameEndingWith("Service")
        .check(classes);
    }
    
    @Test
    public void aspects_should_be_subfixed_with_aspect() {
    	classes()
        .that().resideInAPackage("..aspects")
        .should().haveSimpleNameEndingWith("Aspect")
        .check(classes);
    }
    
    @Test
    public void exceptions_should_be_subfixed_with_exception() {
    	classes()
        .that().resideInAPackage("..exception")
        .should().haveSimpleNameEndingWith("Exception")
        .check(classes);
    }
    
    @Test
    public void repositories_should_be_subfixed_with_repository() {
    	classes()
        .that().resideInAPackage("..repositories")
        .should().haveSimpleNameEndingWith("Repository")
        .check(classes);
    }
    
    @Test
    public void implementations_should_be_subfixed_with_impl() {
    	classes()
        .that().resideInAPackage("..impl")
        .should().haveSimpleNameEndingWith("Impl")
        .check(classes);
    }
    
}