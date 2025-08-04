package com.wompi.automation.pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Clase base que contiene métodos comunes para todos los endpoints
 * Esta es como la "casa base" de donde heredan todas las demás clases
 */
public class BasePage {
    
    // URL base de la API de Wompi
    protected static final String BASE_URL = "https://api.co.uat.wompi.dev/v1";
    
    // Llaves de autenticación (como las llaves de una casa)
    protected static final String PUBLIC_KEY = "pub_stagtest_g2u0HQd3ZMh05hsSgTS2lUV8t3s4mOt7";
    protected static final String PRIVATE_KEY = "prv_stagtest_5i0ZGIGiFcDQifYsXxvsny7Y37tKqFWg";
    
    /**
     * Método para obtener la configuración base de las peticiones HTTP
     * Es como preparar el papel y lápiz antes de escribir
     */
    protected RequestSpecification getBaseRequest() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }
    
    /**
     * Método para hacer una petición GET
     * Es como hacer una pregunta simple
     */
    protected Response get(String endpoint) {
        return getBaseRequest()
                .when()
                .get(endpoint);
    }
    
    /**
     * Método para hacer una petición POST
     * Es como enviar una carta con información
     */
    protected Response post(String endpoint, Object body) {
        return getBaseRequest()
                .body(body)
                .when()
                .post(endpoint);
    }
} 