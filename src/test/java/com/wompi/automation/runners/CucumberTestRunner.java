package com.wompi.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Clase principal para ejecutar las pruebas de Cucumber
 * Es como el "director" que coordina todas las pruebas
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    // Ubicación de los archivos de características (features)
    features = "src/test/resources/features",
    
    // Ubicación de los step definitions
    glue = "com.wompi.automation.steps",
    
    // Configuración del plugin para generar reportes
    plugin = {
        "pretty",                    // Formato legible en consola
        "html:target/cucumber-reports/report.html",  // Reporte HTML
        "json:target/cucumber-reports/report.json",  // Reporte JSON
        "junit:target/cucumber-reports/report.xml"   // Reporte JUnit
    },
    
    // Configuración de monochrome para mejor legibilidad
    monochrome = true,
    
    // Configuración de snippets para generar código
    snippets = io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE,
    
    // Configuración de tags para filtrar escenarios
    tags = "@nequi or @smoke"
)
public class CucumberTestRunner {
    // Esta clase no necesita métodos adicionales
    // Solo configura cómo se ejecutarán las pruebas
} 