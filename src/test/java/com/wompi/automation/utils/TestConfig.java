package com.wompi.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase utilitaria para manejar la configuración de pruebas
 * Centraliza el acceso a las propiedades de configuración
 */
public class TestConfig {
    
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";
    
    static {
        loadProperties();
    }
    
    /**
     * Carga las propiedades desde el archivo de configuración
     */
    private static void loadProperties() {
        try (InputStream inputStream = TestConfig.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                System.err.println("No se pudo encontrar el archivo de configuración: " + CONFIG_FILE);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar las propiedades: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene una propiedad como String
     * @param key clave de la propiedad
     * @return valor de la propiedad
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Obtiene una propiedad como String con valor por defecto
     * @param key clave de la propiedad
     * @param defaultValue valor por defecto si no se encuentra la propiedad
     * @return valor de la propiedad o el valor por defecto
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Obtiene una propiedad como int
     * @param key clave de la propiedad
     * @param defaultValue valor por defecto si no se encuentra la propiedad
     * @return valor de la propiedad como int
     */
    public static int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.err.println("Error al convertir la propiedad " + key + " a int: " + e.getMessage());
            }
        }
        return defaultValue;
    }
    
    /**
     * Obtiene la URL base de la API
     * @return URL base de la API
     */
    public static String getApiBaseUrl() {
        return getProperty("api.base.url", "https://api.co.uat.wompi.dev/v1");
    }
    
    /**
     * Obtiene la URL de merchants
     * @return URL de merchants
     */
    public static String getApiMerchantsUrl() {
        return getProperty("api.merchants.url", "https://api.co.uat.wompi.dev/v1/merchants/");
    }
    
    /**
     * Obtiene la clave pública
     * @return clave pública
     */
    public static String getPublicKey() {
        return getProperty("api.public.key", "pub_stagtest_g2u0HQd3ZMh05hsSgTS2lUV8t3s4mOt7");
    }
    
    /**
     * Obtiene la clave privada
     * @return clave privada
     */
    public static String getPrivateKey() {
        return getProperty("api.private.key", "prv_stagtest_5i0ZGIGiFcDQifYsXxvsny7Y37tKqFWg");
    }
    
    /**
     * Obtiene el timeout de la API
     * @return timeout en milisegundos
     */
    public static int getApiTimeout() {
        return getIntProperty("api.timeout", 30000);
    }
    
    /**
     * Obtiene el timeout de conexión
     * @return timeout de conexión en milisegundos
     */
    public static int getConnectionTimeout() {
        return getIntProperty("api.connection.timeout", 10000);
    }
    
    /**
     * Obtiene el tipo de contenido por defecto
     * @return tipo de contenido
     */
    public static String getContentType() {
        return getProperty("api.content.type", "application/json");
    }
    
    /**
     * Obtiene el tipo de aceptación por defecto
     * @return tipo de aceptación
     */
    public static String getAcceptType() {
        return getProperty("api.accept.type", "application/json");
    }
    
    /**
     * Obtiene la moneda de prueba
     * @return moneda de prueba
     */
    public static String getTestCurrency() {
        return getProperty("test.currency", "COP");
    }
    
    /**
     * Obtiene el user agent de prueba
     * @return user agent
     */
    public static String getTestUserAgent() {
        return getProperty("test.user.agent", 
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
    }
    
    /**
     * Obtiene la dirección IP de prueba
     * @return dirección IP
     */
    public static String getTestIpAddress() {
        return getProperty("test.ip.address", "127.0.0.1");
    }
    
    /**
     * Imprime la configuración actual para debugging
     */
    public static void printConfiguration() {
        System.out.println("=== Configuración de Pruebas ===");
        System.out.println("API Base URL: " + getApiBaseUrl());
        System.out.println("API Merchants URL: " + getApiMerchantsUrl());
        System.out.println("Public Key: " + getPublicKey());
        System.out.println("API Timeout: " + getApiTimeout() + "ms");
        System.out.println("Connection Timeout: " + getConnectionTimeout() + "ms");
        System.out.println("Content Type: " + getContentType());
        System.out.println("Test Currency: " + getTestCurrency());
        System.out.println("================================");
    }
} 