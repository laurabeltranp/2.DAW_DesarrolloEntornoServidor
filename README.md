
ACTIVIDADES ASIGNATURA ENDESARROLLO EN ENTORNO SERVIDOR
------------------------------------------------------------------
Este workspace cuenta de 3 actividades de las cuales iré poniendo los enunciados acontinuación.



AD-1. Listado de eventos con opciones de alta, cancelar, ver detalle y eliminar
---------------------------------------------------------------------------------

Una empresa se dedica a montar eventos. Cada evento es de un tipo distinto. Los tipos de evento son: concierto, despedida, cumpleaños, boda…, y nos interesa un código que identifique cada evento junto con su descripción. A un evento se inscriben clientes, de los que debemos guardar el esquema SQL. Recuerda que un cliente puede apuntarse a más de un evento. 

De aquí en adelante, podremos incorporar más tablas para completar la aplicación, pero, de momento, con estas tenemos bastantes.

El modelo de datos es el siguiente: 

Desarrollo

La idea es que construyamos una aplicación con SpringBoot y Thymeleaf sin acceso a datos, es decir con listas,  y cuya página principal tenga esta imagen (index.html). 


Vamos a implementar el controlador EventosController y HomeController.
Las páginas HTML (con thymeleaf) necesarias para las tareas definidas a continuación.
La clase de Bean 'Evento' y 'Tipo', a partir de las tablas que tienes en este enunciado.
Ojo, un Evento tiene una variable Tipo tipo, no un int idTipo.
El interface TipoDao con dos métodos, findAll() y findById(), y la clase que implementa este interface.
El interface llamado EventoDao de la clase 'Evento' con los métodos necesarios.
Una clase EventoDaoImpl, que contiene una lista de eventos con su tipo y la implementación de los métodos del interface.
 

Del controlador llamado HomeController tratamos las siguientes opciones:

“/”->  Sacar el listado de la imagen superior.
Del controlador llamado EventoController tratamos las siguientes opciones:

“/eventos/alta: -> Mostrar un formulario con los datos del evento, poner el estado del evento como ‘ACTIVO’ y, si quieres destacarlo, poner una ‘s’ en su columna. Volver al listado de activos.

“/eventos/editar/{id} “ -> Mostrar en un HTML con los datos del evento.

“/eventos/eliminar/{id} “ ->  Borrarlo de la clase que contiene los eventos y volver al listado de activos.


“/eventos/cancelar/{id}“ ->  Poner el estado del evento como "CANCELADO", y volver al listado de activos. 

Comprobar con un "syso" por consola que este evento tiene el estado a "CANCELADO". 

De momento no nos preocupamos de quién entra en la aplicación. Vamos a suponer que entra a la aplicación un administrador que tiene permisos para probar todas las tareas que se proponen.

AD-2. Spring Data JPA : Cajero Virtual
------------------------------------------------------------------------------
En esta práctica, vamos a realizar la implementación de un cajero virtual, aplicando el patrón MVC con Spring Boot con capa de persistencia para acceso a los datos con Spring Data JPA.

Para la realización de la práctica se empleará una base de datos con dos tablas: la tabla de cuentas, que almacena las cuentas del banco, y movimientos, que registra todos los movimientos realizados en cada cuenta.

La estructura de ambas tablas se muestra en la siguiente imagen:


El script de la base de datos lo aporta el profesor en Unidades formativas en la UF4 junto a la actividad.

La base de datos ya tiene dos cuentas para probar.

El aspecto de las páginas de la aplicación será similar al que se muestra en la siguiente imagen:


Al iniciarse la aplicación se solicitará el número de cuenta con la que se quiere operar y la cuenta se guardará en un atributo de sesión. Tras validar la misma:

Si la cuenta no existe no se le deja seguir adelante, y se mostrará el mensaje ‘Cuenta no existe’.
Si la cuenta existe, aparece un menú con las opciones que se pueden realizar desde el cajero, añadir cerrar sesión.
Al seleccionar ‘Ingresar’ o ‘Extraer’, se nos solicitará la cantidad y la operación quedará reflejada en la tabla de movimientos (abono, cargo), además de actualizar el saldo de la cuenta, en la tabla de ‘Cuentas’.

Si al extraer dinero, el saldo da negativo impedir que se saque esa cantidad, con el mensaje, ‘saldo insuficiente’.

Con la opción de ‘Transferencia’, se solicitará la cuenta destino y la cantidad a transferir (si no tengo saldo, informar con un mensaje, e impedir la transferencia). 

Si la cuenta no existe, se muestra el mensaje  ‘Cuenta de destino no existe’.

En esta operación, se registrará un movimiento de extracción en la cuenta origen ("cargo por transferencia") y uno de ingreso en la de destino ("abono por transferencia"), además de almacenar (modificar en la base de datos la tabla cuenta) los saldos de ambas cuentas.

Finalmente, la operación ‘Ver movimientos’, nos mostrará una página con la lista de movimientos realizados sobre la cuenta, además de mostrar el saldo de la misma.

Para probar la transferencia, cierras la sesión y te conectas a la cuenta de destino, y ves los movimientos para comprobar que el apunte esta en la lista.

AD-4. Microservicios web con Spring Boot y JPA
------------------------------------------------------------------------------------
Una Empresa tiene recogido en una base de datos los pedidos solicitados por Clientes y el Comercial asociado a la venta. La tabla pedidos recoge el importe total del pedido realizado.

La aplicación la van a usar los jefes de comerciales  para ver la información, tanto de clientes como de sus comerciales.

Tablas:


Se adjunta el script del sql (en la unidad formativa UF6) con la creación de la base de datos (ventasbbdd_2024) de las tablas, insert para pruebas y creación del usuario uventas_2024 con la password uventas.

Ejercicio

Crear un proyecto Spring web, con Spring data Jpa y MySql 8 para los siguientes servicios web restfull, y probar con postman:


/comercial      @RequestMapping(“/comercial”)

/alta   Dar de alta el comercial

/eliminar/{id}      Eliminar de la bbdd el comercial cuyo id coincida

/uno/{id}   Devolver los datos del comercial cuyo id coincida

/bycliente/{id}     Devolver la lista de los comerciales que han atendido pedidos del      cliente que coincida con ese id.

/conpedidos     Devolver la lista de comerciales que han atendido algún pedido

/pedidos/{id}       Devolver la lista de pedidos gestionados por el comercial que coincida con ese id.