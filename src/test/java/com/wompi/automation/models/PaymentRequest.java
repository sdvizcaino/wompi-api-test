package com.wompi.automation.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que representa una solicitud de pago para la API de Wompi
 * Implementa el patrón Builder para facilitar la creación de objetos
 */
public class PaymentRequest {
    
    // Información del comercio
    @JsonProperty("public_key")
    private String publicKey;
    
    @JsonProperty("reference")
    private String reference;
    
    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("amount_in_cents")
    private int amount;
    
    // Información del cliente
    @JsonProperty("customer_email")
    private String customerEmail;
    
    @JsonProperty("customer_full_name")
    private String customerFullName;
    
    @JsonProperty("customer_mobile")
    private String customerMobile;
    
    // Información del método de pago
    @JsonProperty("payment_method")
    private PaymentMethod paymentMethod;
    
    // Información adicional
    @JsonProperty("acceptance_token")
    private String acceptanceToken;
    
    @JsonProperty("user_agent")
    private String userAgent;
    
    @JsonProperty("ip_address")
    private String ipAddress;
    
    // Clase interna para el método de pago
    public static class PaymentMethod {
        @JsonProperty("type")
        private String type;
        
        @JsonProperty("installments")
        private String installments;
        
        @JsonProperty("token")
        private String token;
        
        public PaymentMethod(String type, String installments, String token) {
            this.type = type;
            this.installments = installments;
            this.token = token;
        }
        
        // Getters
        public String getType() { return type; }
        public String getInstallments() { return installments; }
        public String getToken() { return token; }
    }
    
    // Constructor privado para el patrón Builder
    private PaymentRequest() {
        // Valores por defecto
        this.publicKey = "pub_stagtest_g2u0HQd3ZMh05hsSgTS2lUV8t3s4mOt7";
        this.currency = "COP";
        this.userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36";
        this.ipAddress = "127.0.0.1";
    }
    
    // Métodos para establecer valores (setters)
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }
    
    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }
    
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public void setAcceptanceToken(String acceptanceToken) {
        this.acceptanceToken = acceptanceToken;
    }
    
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    // Métodos para obtener valores (getters)
    public String getPublicKey() {
        return publicKey;
    }
    
    public String getReference() {
        return reference;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public String getCustomerEmail() {
        return customerEmail;
    }
    
    public String getCustomerFullName() {
        return customerFullName;
    }
    
    public String getCustomerMobile() {
        return customerMobile;
    }
    
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    
    public String getAcceptanceToken() {
        return acceptanceToken;
    }
    
    public String getUserAgent() {
        return userAgent;
    }
    
    public String getIpAddress() {
        return ipAddress;
    }
    
    /**
     * Valida que los campos obligatorios estén presentes
     * @return true si la solicitud es válida
     */
    public boolean isValid() {
        return publicKey != null && !publicKey.isEmpty() &&
               reference != null && !reference.isEmpty() &&
               currency != null && !currency.isEmpty() &&
               amount > 0 &&
               customerEmail != null && !customerEmail.isEmpty() &&
               customerFullName != null && !customerFullName.isEmpty() &&
               paymentMethod != null;
    }
    
    /**
     * Builder para crear objetos PaymentRequest de manera fluida
     */
    public static class Builder {
        private PaymentRequest paymentRequest;
        
        public Builder() {
            paymentRequest = new PaymentRequest();
        }
        
        public Builder withPublicKey(String publicKey) {
            paymentRequest.setPublicKey(publicKey);
            return this;
        }
        
        public Builder withReference(String reference) {
            paymentRequest.setReference(reference);
            return this;
        }
        
        public Builder withCurrency(String currency) {
            paymentRequest.setCurrency(currency);
            return this;
        }
        
        public Builder withAmount(int amount) {
            paymentRequest.setAmount(amount);
            return this;
        }
        
        public Builder withCustomerEmail(String email) {
            paymentRequest.setCustomerEmail(email);
            return this;
        }
        
        public Builder withCustomerFullName(String fullName) {
            paymentRequest.setCustomerFullName(fullName);
            return this;
        }
        
        public Builder withCustomerMobile(String mobile) {
            paymentRequest.setCustomerMobile(mobile);
            return this;
        }
        
        public Builder withPaymentMethod(String type, String installments, String token) {
            paymentRequest.setPaymentMethod(new PaymentMethod(type, installments, token));
            return this;
        }
        
        public Builder withAcceptanceToken(String token) {
            paymentRequest.setAcceptanceToken(token);
            return this;
        }
        
        public Builder withUserAgent(String userAgent) {
            paymentRequest.setUserAgent(userAgent);
            return this;
        }
        
        public Builder withIpAddress(String ipAddress) {
            paymentRequest.setIpAddress(ipAddress);
            return this;
        }
        
        public PaymentRequest build() {
            return paymentRequest;
        }
    }
    
    /**
     * Método estático para crear un builder
     * @return nuevo Builder
     */
    public static Builder builder() {
        return new Builder();
    }
} 