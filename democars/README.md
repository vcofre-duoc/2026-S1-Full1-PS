# Temas importantes

## Checklist de JWT Integration

- [x] Crear el archivo `config/SecurityConfig.java`
  - Configuración de Spring Security.
  - Se definieron rutas públicas (`/api/v1/cars/list2`) y rutas protegidas (`/api/v1/cars/list`).
  - Se agregó el filtro personalizado `TokenValidationFilter` para validar JWT vía `AuthService`.

- [x] Implementar `TokenValidationFilter`
  - Extiende `OncePerRequestFilter`.
  - Extrae el header `Authorization`.
  - Llama a `AuthService.validateToken(token)` para validar contra el login-service.
  - Si el token es válido, marca la request como autenticada en el `SecurityContext`.
  - Si no, responde con `401 Unauthorized`.

- [x] Ajustar `AuthService`
  - Se cambió el consumo de WebClient para mapear directamente la respuesta JSON del login-service a `ApiResponse<String>`.
  - Se eliminó la validación por `String.contains("Token válido")`.
  - Ahora se usa `validationResponse.getCode() == 200` para decidir si el token es válido.

- [x] Actualizar `CarController`
  - En `/list` se valida el token usando `AuthService`.
  - En `/list2` se dejó como endpoint público para pruebas.
  - Se devuelve un `ApiResponse` uniforme en todos los casos.

---

## Corrección de problemas con migraciones (Flyway)

Durante la integración se detectó un error en la migración `V2__insert_cars.sql` que dejaba el esquema en estado fallido. Los pasos para corregirlo fueron:

1. **Revisar y corregir el script SQL**
   - Validar que los `INSERT` tengan columnas correctas (`brand`, `model`, `year`).
   - Probar los scripts directamente en MySQL antes de ponerlos en `db/migration`.

2. **Configurar conexión de Flyway en Maven**
   - Se agregó el plugin `flyway-maven-plugin` en el `pom.xml` dentro de `<build><plugins>`.
   - Ejemplo de configuración:
     ```xml
     <plugin>
       <groupId>org.flywaydb</groupId>
       <artifactId>flyway-maven-plugin</artifactId>
       <version>11.14.1</version>
       <configuration>
         <url>jdbc:mysql://localhost:3311/car</url>
         <user>vcofre</user>
         <password>vcofre123</password>
       </configuration>
     </plugin>
     ```

3. **Ejecutar comandos de reparación y migración**
   ```bash
   ./mvnw flyway:repair
   ./mvnw flyway:migrate
