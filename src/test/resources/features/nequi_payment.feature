# language: es
@nequi @smoke
Caracteristica: Pagos con Nequi
  Como usuario de Wompi
  Quiero poder realizar pagos usando Nequi
  Para completar transacciones de manera segura

  Antecedentes:
    Dado que estoy en la API de Wompi
    Y tengo las credenciales de prueba configuradas

  @smoke
  Escenario: Pago exitoso con Nequi
    Cuando creo una transacción de pago con Nequi
    Y el monto es de 10000 pesos colombianos
    Y el cliente es "Juan Pérez" con email "test@example.com"
    Entonces la transacción debe ser creada exitosamente
    Y el estado de la transacción debe ser "PENDING"
    Y debo recibir un ID de transacción válido

  Escenario: Pago con monto mínimo
    Cuando creo una transacción de pago con Nequi
    Y el monto es de 100 pesos colombianos
    Y el cliente es "María García" con email "maria@test.com"
    Entonces la transacción debe ser creada exitosamente
    Y el estado de la transacción debe ser "PENDING"

  Escenario: Pago con monto alto
    Cuando creo una transacción de pago con Nequi
    Y el monto es de 1000000 pesos colombianos
    Y el cliente es "Carlos López" con email "carlos@test.com"
    Entonces la transacción debe ser creada exitosamente
    Y el estado de la transacción debe ser "PENDING"

  Escenario: Pago con datos de cliente incompletos
    Cuando creo una transacción de pago con Nequi
    Y el email del cliente está vacío
    Entonces la transacción debe fallar
    Y debo recibir un mensaje de error apropiado

  Escenario: Pago con monto inválido
    Cuando creo una transacción de pago con Nequi
    Y el monto es de 0 pesos colombianos
    Entonces la transacción debe fallar
    Y debo recibir un mensaje de error apropiado 