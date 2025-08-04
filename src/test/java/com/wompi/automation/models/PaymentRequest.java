package com.wompi.automation.models;

/**
 * Clase que representa una solicitud de pago
 * Es como una "plantilla" que dice qué información necesitamos para hacer un pago
 */
public class PaymentRequest {
    
    // Información del comercio
    private String publicKey;
    private String reference;
    private String currency;
    private int amount;
    
    // Información del cliente
    private String customerEmail;
    private String customerFullName;
    private String customerMobile;
    
    // Información del método de pago
    private String paymentMethodType;
    private String paymentMethodInstallments;
    private String paymentMethodToken;
    
    // Información adicional
    private String acceptanceToken;
    private String userAgent;
    private String ipAddress;
    
    // Constructor (método especial que se ejecuta cuando creamos un objeto)
    public PaymentRequest() {
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
    
    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }
    
    public void setPaymentMethodInstallments(String paymentMethodInstallments) {
        this.paymentMethodInstallments = paymentMethodInstallments;
    }
    
    public void setPaymentMethodToken(String paymentMethodToken) {
        this.paymentMethodToken = paymentMethodToken;
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
    
    public String getPaymentMethodType() {
        return paymentMethodType;
    }
    
    public String getPaymentMethodInstallments() {
        return paymentMethodInstallments;
    }
    
    public String getPaymentMethodToken() {
        return paymentMethodToken;
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
} 