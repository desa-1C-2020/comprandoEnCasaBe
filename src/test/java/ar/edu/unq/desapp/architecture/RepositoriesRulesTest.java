package ar.edu.unq.desapp.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "main.java.ar.edu.unq.desapp.comprandoencasa.repositories.impl")
public class RepositoriesRulesTest {
    @ArchTest
    public void xxxx(JavaClasses classes) {
        classes()
            .that().resideInAPackage("..impl..")
            .should().onlyHaveDependentClassesThat()
            .resideInAnyPackage("..spring..")
            .check(classes);
    }
}