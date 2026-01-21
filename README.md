# Arquitectura Backend

**Sistema de Facturas y Saldos – Panavial**

Descripción General
Este proyecto implementa un sistema de consulta de Facturas y Saldos basado en Clean Architecture, con autenticación mediante JWT y acceso controlado a información almacenada en un Data Warehouse (DWH) Postgresql y una BDD de Mysql.

El sistema está diseñado para:

•	Manejar grandes volúmenes de datos.

•	Garantizar seguridad y aislamiento de responsabilidades.

•	Permitir escalabilidad y mantenimiento independiente de cada componente.


**Arquitectura**
El proyecto sigue estrictamente los principios de Clean Architecture, separando responsabilidades en capas bien definidas.

 Domain (Entities + Ports)  
 
<img width="893" height="517" alt="image" src="https://github.com/user-attachments/assets/1c727d72-fcd8-4961-8a42-7de011dd989b" />


Regla clave: Las dependencias siempre apuntan hacia el Dominio.


**Autenticación y Autorización**

Servicio de Login

•	Base de datos: PostgreSQL (fuera del DWH)
•	Función:
o	Autenticar usuarios
o	Registrar nuevos usuarios
o	Emitir JWT

Contenido del JWT
El token generado contiene los siguientes claims:
{
  "sub": "13",
  "identification": "00000XXXXXX",
  "roles": ["USER"],
  "iss": "idp-panavial-backend",
  "iat": 1768941907,
  "exp": 1768950907
}

Uso del JWT
•	El token se envía en cada request mediante el header:
•	Authorization: Bearer <token>
•	El sistema no recibe el RUC desde el frontend.
•	Toda la autorización se basa en los claims del JWT.

Sistema de Facturas
Base de Datos
•	PostgreSQL (DWH)
•	Acceso mediante JdbcTemplate
•	Solo lectura
Endpoint
GET /api/facturas
Comportamiento por Rol
Rol USER
•	Solo puede consultar facturas asociadas a su propio RUC.
•	El RUC se obtiene exclusivamente desde el token.
•	Filtro de fecha: opcional.
Flujo:
1.	Controller obtiene el RUC desde el Authentication.
2.	Se ejecuta el caso de uso con ruc.
3.	El repositorio filtra por RUC y fecha.

Rol ADMIN
•	Puede consultar todas las facturas.
•	El filtro de fecha es obligatorio.
•	Consultas limitadas y optimizadas para DWH.
Flujo:
1.	Controller valida rol ADMIN.
2.	Se exige fecha.
3.	Se ejecuta el caso de uso sin RUC.
4.	El repositorio filtra únicamente por fecha.

Sistema de Saldos
Base de Datos
•	MySQL (DWH)
•	Millones de registros
•	Acceso mediante JdbcTemplate
Características Clave
•	No se permite crear ni modificar estructuras (solo lectura).
•	Consultas optimizadas para alto volumen.
•	Uso de LIMIT y ordenamiento.
•	Preparado para paginación por cursor.
Comportamiento por Rol
 Rol USER
•	Consulta únicamente sus propios saldos.
•	Documento/RUC obtenido desde el JWT.
•	Filtros de fecha opcionales.
Rol ADMIN
•	Consulta todos los saldos.
•	Rango de fechas obligatorio.
•	Resultados limitados.
•	Diseño orientado a no afectar el rendimiento del DWH.

Seguridad
•	JWT validado por JwtAuthenticationFilter.
•	Claims cargados en SecurityContext.
•	El backend controla:
o	Rol
o	Identidad
•	El frontend no puede manipular identificadores sensibles.

Configuración de Datasources
El sistema utiliza múltiples fuentes de datos:
PostgreSQL
•	Login
•	Facturas (DWH)
MySQL
•	Saldos (DWH)
Cada datasource cuenta con:
•	DataSource
•	EntityManagerFactory
•	TransactionManager
•	JdbcTemplate dedicado

Rendimiento y Buenas Prácticas
•	Consultas siempre filtradas.
•	Prohibidas consultas abiertas en DWH.
•	Uso de rangos de fecha obligatorios para ADMIN.
•	Respuestas limitadas.
•	Arquitectura preparada para:
o	Auditoría
o	Escalabilidad
o	Optimización futura

Beneficios del Enfoque
•	Seguridad reforzada (JWT como única fuente de verdad).
•	Clean Architecture real y mantenible.
•	Alto rendimiento sobre DWH.
•	Soporte para grandes volúmenes de datos.
•	Casos de uso fácilmente testeables.
•	Sistemas desacoplados.
•	Documentación clara y defendible.

Autor:
Equipo de Desarrollo SUDINCO
Arquitectura: Clean Architecture + JWT + DWH

