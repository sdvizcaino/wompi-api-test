package com.wompi.automation.pages;

import com.wompi.automation.models.PaymentRequest;
import io.restassured.response.Response;

/**
 * Clase específica para manejar pagos con Nequi
 * Implementa métodos para crear y gestionar transacciones de pago
 */
public class NequiPaymentPage extends BasePage {
    
    // Endpoint para crear transacciones
    private static final String TRANSACTIONS_ENDPOINT = "/transactions";
    
    /**
     * Método para crear una transacción de pago con Nequi
     * @param paymentRequest objeto con los datos del pago
     * @return Response de la API
     */
    public Response createNequiPayment(PaymentRequest paymentRequest) {
        // Configurar el método de pago Nequi si no está configurado
        if (paymentRequest.getPaymentMethod() == null) {
            paymentRequest.setPaymentMethod(
                new PaymentRequest.PaymentMethod("NEQUI", "1", "test_token")
            );
        }
        
        // Hacer la petición POST al endpoint de transacciones
        return post(TRANSACTIONS_ENDPOINT, paymentRequest);
    }
    
    /**
     * Método para crear un pago de prueba con Nequi usando el patrón Builder
     * @return Response de la API
     */
    public Response createTestNequiPayment() {
        PaymentRequest testPayment = PaymentRequest.builder()
            .withReference("TEST_NEQUI_" + System.currentTimeMillis())
            .withAmount(10000) // 10,000 pesos colombianos
            .withCustomerEmail("test@example.com")
            .withCustomerFullName("Juan Pérez")
            .withCustomerMobile("3001234567")
            .withPaymentMethod("NEQUI", "1", "test_token")
            .withAcceptanceToken("test_acceptance_token")
            .build();
        
        return createNequiPayment(testPayment);
    }
    
    /**
     * Método para crear un pago con datos específicos
     * @param amount monto en centavos
     * @param customerName nombre del cliente
     * @param customerEmail email del cliente
     * @return Response de la API
     */
    public Response createNequiPaymentWithData(int amount, String customerName, String customerEmail) {
        PaymentRequest paymentRequest = PaymentRequest.builder()
            .withReference("TEST_NEQUI_" + System.currentTimeMillis())
            .withAmount(amount)
            .withCustomerEmail(customerEmail)
            .withCustomerFullName(customerName)
            .withCustomerMobile("3001234567")
            .withPaymentMethod("NEQUI", "1", "test_token")
            .withAcceptanceToken("test_acceptance_token")
            .build();
        
        return createNequiPayment(paymentRequest);
    }
    
    /**
     * Método para crear un pago con datos inválidos para pruebas negativas
     * @param amount monto (puede ser 0 o negativo)
     * @param customerEmail email (puede estar vacío)
     * @return Response de la API
     */
    public Response createInvalidNequiPayment(int amount, String customerEmail) {
        PaymentRequest paymentRequest = PaymentRequest.builder()
            .withReference("TEST_INVALID_" + System.currentTimeMillis())
            .withAmount(amount)
            .withCustomerEmail(customerEmail)
            .withCustomerFullName("Test User")
            .withCustomerMobile("3001234567")
            .withPaymentMethod("NEQUI", "1", "test_token")
            .withAcceptanceToken("test_acceptance_token")
            .build();
        
        return createNequiPayment(paymentRequest);
    }
    
    /**
     * Método para verificar que la respuesta del pago sea exitosa
     * @param response respuesta de la API
     * @return true si la respuesta es exitosa
     */
    public boolean isPaymentSuccessful(Response response) {
        return response.getStatusCode() == 200 || response.getStatusCode() == 201;
    }
    
    /**
     * Método para obtener el ID de la transacción de la respuesta
     * @param response respuesta de la API
     * @return ID de la transacción o null si no se encuentra
     */
    public String getTransactionId(Response response) {
        try {
            return response.jsonPath().getString("data.id");
        } catch (Exception e) {
            System.out.println("No se pudo obtener el ID de la transacción: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Método para obtener el estado de la transacción
     * @param response respuesta de la API
     * @return estado de la transacción o mensaje de error
     */
    public String getTransactionStatus(Response response) {
        try {
            String status = response.jsonPath().getString("data.status");
            return status != null ? status : "NO_STATUS_AVAILABLE";
        } catch (Exception e) {
            System.out.println("No se pudo obtener el estado de la transacción: " + e.getMessage());
            return "ERROR_GETTING_STATUS";
        }
    }
    
    /**
     * Método para obtener el mensaje de error de la respuesta
     * @param response respuesta de la API
     * @return mensaje de error o null si no hay error
     */
    public String getErrorMessage(Response response) {
        try {
            return response.jsonPath().getString("error.message");
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Método para imprimir información de debug de la respuesta
     * @param response respuesta de la API
     */
    public void printResponseInfo(Response response) {
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        
        if (isPaymentSuccessful(response)) {
            System.out.println("Transaction ID: " + getTransactionId(response));
            System.out.println("Transaction Status: " + getTransactionStatus(response));
        } else {
            System.out.println("Error Message: " + getErrorMessage(response));
        }
    }
} 