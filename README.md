# Framework de Automatización - Wompi API

## Descripción del Proyecto

Solución completa a la Prueba Técnica de Automatización Wompi que implementa un framework de automatización para la API de Wompi utilizando Java 21, Cucumber (BDD) y Rest Assured. El objetivo es garantizar la calidad y funcionalidad de las transacciones de pago mediante pruebas automatizadas robustas y mantenibles.

## Cumplimiento de Requisitos

### 1. Escenarios de Prueba Diseñados
- **3 Escenarios Exitosos**: Pago básico ($10,000 COP), monto mínimo ($100 COP), monto alto ($1,000,000 COP)
- **2 Escenarios Alternos**: Validación de datos obligatorios (email vacío), validación de montos inválidos
- **Formato BDD**: Escenarios escritos en lenguaje natural con Cucumber

### 2. Script de Pruebas Automatizadas
- **Java 21**: Lenguaje principal (requisito cumplido)
- **BDD**: Cucumber 7.15.0 (requisito cumplido)
- **Testing API**: Rest Assured 5.5.0
- **Gestión**: Maven 3.9.11

### 3. Método de Pago Seleccionado
- **Nequi**: Cumple requisito de no ser tarjeta de crédito
- **Justificación**: Método digital popular en Colombia con integración directa

### 4. Patrón de Diseño Implementado
- **Page Object Model (POM)**: Arquitectura modular y escalable
- **Separación de responsabilidades**: Cada clase con función específica
- **Reutilización de código**: Métodos comunes en BasePage

## Arquitectura del Framework

```
src/test/java/com/wompi/automation/
├── models/PaymentRequest.java          # Modelo de datos con Builder
├── pages/BasePage.java                 # Clase base HTTP
├── pages/NequiPaymentPage.java         # Lógica específica Nequi
├── steps/NequiPaymentSteps.java        # Step definitions BDD
├── runners/CucumberTestRunner.java     # Ejecutor de pruebas
└── utils/TestConfig.java               # Configuración centralizada

src/test/resources/
├── features/nequi_payment.feature      # Escenarios BDD
└── config.properties                   # Configuración API
```

## Configuración de la API

### URLs y Endpoints
- **Base URL**: `https://api.co.uat.wompi.dev/v1`
- **Merchants URL**: `https://api.co.uat.wompi.dev/v1/merchants/`

### Llaves de Autenticación
- **Public Key**: `pub_stagtest_g2u0HQd3ZMh05hsSgTS2lUV8t3s4mOt7`
- **Private Key**: `prv_stagtest_5i0ZGIGiFcDQifYsXxvsny7Y37tKqFWg`
- **Events Key**: `stagtest_events_2PDUmhMywUkvb1LvxYnayFbmofT7w39N`
- **Integrity Key**: `stagtest_integrity_nAIBuqayW70XpUqJS4qf4STYiISd89Fp`

## Resultados de las Pruebas

```
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
Tiempo de ejecución: 1.054 segundos
```

**Escenarios ejecutados exitosamente:**
1. Pago exitoso con Nequi
2. Pago con monto mínimo
3. Pago con monto alto
4. Pago con datos de cliente incompletos
5. Pago con monto inválido

## Tecnologías Utilizadas

- **Java 21**: Lenguaje principal
- **Cucumber 7.15.0**: Framework BDD
- **Rest Assured 5.5.0**: Testing de APIs REST
- **JUnit 4.13.2**: Framework de testing
- **Jackson 2.16.1**: Manejo de JSON
- **Maven 3.9.11**: Gestión de dependencias

## Características Destacadas

### Patrón Builder
```java
PaymentRequest payment = PaymentRequest.builder()
    .withReference("TEST_NEQUI_" + System.currentTimeMillis())
    .withAmount(10000)
    .withCustomerEmail("test@example.com")
    .withCustomerFullName("Juan Pérez")
    .withPaymentMethod("NEQUI", "1", "test_token")
    .build();
```

### Configuración Centralizada
```java
String baseUrl = TestConfig.getApiBaseUrl();
String publicKey = TestConfig.getPublicKey();
```

### Escenarios BDD
```gherkin
@nequi @smoke
Scenario: Pago exitoso con Nequi
  Cuando creo una transacción de pago con Nequi
  Y el monto es de 10000 pesos colombianos
  Y el cliente es "Juan Pérez" con email "test@example.com"
  Entonces la transacción debe ser creada exitosamente
```

## Cómo Ejecutar

### Prerrequisitos
- Java 21 o superior
- Maven 3.6 o superior

### Comando de Ejecución
```bash
mvn clean test
```

### Ejecutar desde IDE
1. Abrir proyecto en IntelliJ IDEA o Eclipse
2. Buscar clase `CucumberTestRunner`
3. Clic derecho → Run

## Integración CI/CD

### GitHub Actions
El proyecto incluye configuración completa de CI/CD con GitHub Actions:

- **Ejecución automática**: En push a main/master/develop y pull requests
- **Programación**: Ejecución diaria a las 6AM (Colombia)
- **Jobs paralelos**: Tests, Security Scan, Code Quality
- **Reportes**: Generación automática de reportes HTML y XML

### Pipeline de CI/CD
1. **Test Job**: Ejecuta las pruebas automatizadas
2. **Security Job**: Escaneo de dependencias con OWASP
3. **Quality Job**: Análisis estático con SpotBugs

### Configuración
- **Java 21**: Configurado en todos los jobs
- **Maven Cache**: Optimización de dependencias
- **Artifacts**: Reportes disponibles por 30 días

## Reportes Generados

- **HTML**: `target/cucumber-reports/report.html`
- **JSON**: `target/cucumber-reports/report.json`
- **JUnit XML**: `target/cucumber-reports/report.xml`

## Valor Agregado

### Mejoras Implementadas
1. **Patrón Builder**: Creación fluida de objetos
2. **Configuración centralizada**: Gestión de propiedades
3. **Logging mejorado**: Formato legible en consola
4. **Manejo de errores**: Try-catch robusto
5. **Validaciones**: Método `isValid()` para datos obligatorios

### Framework Profesional
- **Escalable**: Fácil agregar nuevos métodos de pago
- **Mantenible**: Código bien estructurado y documentado
- **Reutilizable**: Componentes modulares
- **Robusto**: Manejo de errores y validaciones

## Estado del Proyecto

### Completado 100%
- Todos los requisitos de la prueba técnica cumplidos
- Framework completamente funcional
- 5 escenarios de prueba ejecutándose exitosamente
- Documentación completa y profesional
- Código optimizado y bien estructurado
- Integración CI/CD implementada

### Plugins de Calidad
- **SpotBugs**: ✅ Funcionando correctamente
- **Checkstyle**: ⚠️ Temporalmente deshabilitado (problema de configuración)
- **JaCoCo**: ⚠️ Temporalmente deshabilitado (no compatible con Java 21)

## Próximos Pasos Sugeridos

### Funcionalidades Futuras
- Agregar más métodos de pago (PSE, Bancolombia)
- Implementar pruebas de rendimiento
- Implementar pruebas de seguridad adicionales

### Mejoras Técnicas
- **Habilitar Checkstyle**: Resolver problema de configuración del plugin
- **Habilitar JaCoCo**: Actualizar a versión compatible con Java 21
- Dockerización del framework
- Integración con Allure para reportes visuales
- Migración a JUnit 5

---

**Autor**: Desarrollado como parte de la prueba técnica de automatización para Wompi. 