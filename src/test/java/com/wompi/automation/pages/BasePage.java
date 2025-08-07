package com.wompi.automation.pages;

import com.wompi.automation.utils.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Clase base que contiene métodos comunes para todos los endpoints
 * Utiliza configuración centralizada para mayor flexibilidad
 */
public class BasePage {
    
    /**
     * Método para obtener la configuración base de las peticiones HTTP
     * @return RequestSpecification configurado
     */
    protected RequestSpecification getBaseRequest() {
        return RestAssured.given()
                .baseUri(TestConfig.getApiBaseUrl())
                .header("Content-Type", TestConfig.getContentType())
                .header("Accept", TestConfig.getAcceptType());
    }
    
    /**
     * Método para hacer una petición GET
     * @param endpoint endpoint a consultar
     * @return Response de la API
     */
    protected Response get(String endpoint) {
        return getBaseRequest()
                .when()
                .get(endpoint);
    }
    
    /**
     * Método para hacer una petición POST
     * @param endpoint endpoint a consultar
     * @param body cuerpo de la petición
     * @return Response de la API
     */
    protected Response post(String endpoint, Object body) {
        return getBaseRequest()
                .body(body)
                .when()
                .post(endpoint);
    }
    
    /**
     * Método para hacer una petición PUT
     * @param endpoint endpoint a consultar
     * @param body cuerpo de la petición
     * @return Response de la API
     */
    protected Response put(String endpoint, Object body) {
        return getBaseRequest()
                .body(body)
                .when()
                .put(endpoint);
    }
    
    /**
     * Método para hacer una petición DELETE
     * @param endpoint endpoint a consultar
     * @return Response de la API
     */
    protected Response delete(String endpoint) {
        return getBaseRequest()
                .when()
                .delete(endpoint);
    }
    
    /**
     * Método para imprimir información de debug de una respuesta
     * @param response respuesta de la API
     * @param operationName nombre de la operación para el log
     */
    protected void logResponse(Response response, String operationName) {
        System.out.println("=== " + operationName + " ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("================================");
    }
} 