package com.wompi.automation.pages;

import com.wompi.automation.models.PaymentRequest;
import io.restassured.response.Response;

/**
 * Clase específica para manejar pagos con Nequi
 * Es como una "especialista" que sabe todo sobre pagos con Nequi
 */
public class NequiPaymentPage extends BasePage {
    
    // Endpoint para crear transacciones
    private static final String TRANSACTIONS_ENDPOINT = "/transactions";
    
    /**
     * Método para crear una transacción de pago con Nequi
     * Es como llenar un formulario de pago
     */
    public Response createNequiPayment(PaymentRequest paymentRequest) {
        // Configurar los datos específicos para Nequi
        paymentRequest.setPaymentMethodType("NEQUI");
        paymentRequest.setPaymentMethodInstallments("1");
        
        // Hacer la petición POST al endpoint de transacciones
        return post(TRANSACTIONS_ENDPOINT, paymentRequest);
    }
    
    /**
     * Método para crear un pago de prueba con Nequi
     * Es como crear un ejemplo de pago para probar
     */
    public Response createTestNequiPayment() {
        PaymentRequest testPayment = new PaymentRequest();
        
        // Configurar datos de prueba
        testPayment.setReference("TEST_NEQUI_" + System.currentTimeMillis());
        testPayment.setAmount(10000); // 10,000 pesos colombianos
        testPayment.setCustomerEmail("test@example.com");
        testPayment.setCustomerFullName("Juan Pérez");
        testPayment.setCustomerMobile("3001234567");
        testPayment.setAcceptanceToken("test_acceptance_token");
        
        return createNequiPayment(testPayment);
    }
    
    /**
     * Método para verificar que la respuesta del pago sea exitosa
     * Es como revisar que todo salió bien
     */
    public boolean isPaymentSuccessful(Response response) {
        return response.getStatusCode() == 200 || response.getStatusCode() == 201;
    }
    
    /**
     * Método para obtener el ID de la transacción de la respuesta
     * Es como obtener el número de recibo
     */
    public String getTransactionId(Response response) {
        return response.jsonPath().getString("data.id");
    }
    
    /**
     * Método para obtener el estado de la transacción
     * Es como ver si el pago está pendiente, aprobado o rechazado
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
} 