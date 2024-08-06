# Cajero-Banco-de-la-Plaza-TP



Este proyecto desarrolla un prototipo evolutivo para un Cajero Automático (ATM) destinado al Banco de la Plaza. El sistema permite a los usuarios identificarse mediante una contraseña numérica de 4 dígitos. Si la validación falla tras tres intentos, la tarjeta es retenida e inhabilitada. Una vez identificado, el usuario puede realizar varias operaciones, incluyendo el cambio de clave, consulta de saldo, retiro y depósito de dinero, transferencias y consulta de movimientos (esta última, disponible solo para clientes del Banco de la Plaza).

El prototipo soporta clientes tanto del Banco de la Plaza como de otros bancos, identificándolos a través de rangos de números de tarjeta. El sistema maneja distintos tipos de cuentas: Caja de Ahorro, que no permite sobregiros y aplica cargos fijos a clientes de otros bancos; Cuenta Sueldo, que está libre de cargos y no tiene mantenimiento mensual; y Cuenta Corriente, que permite sobregiros bajo ciertas condiciones y cobra por operaciones adicionales tras un límite.

Dado que se trata de un prototipo, no se interactúa con el hardware real del ATM. Las funciones de lectura y retención de tarjetas, entrega y recepción de billetes, y emisión de tickets son simuladas. La persistencia de datos se implementa mediante serialización o XML. Además, se desarrollan procesos adicionales como el ABM de clientes, la liquidación de intereses y cargos de mantenimiento, los cuales se ejecutan de manera programada.

Este prototipo sienta las bases para el desarrollo futuro de un sistema ATM completo, permitiendo validar y explorar los conceptos clave en un entorno controlado.
