package cl.duoc.democars.controller;

import cl.duoc.democars.service.CarService;
import cl.duoc.democars.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CarControllerStandaloneTest {

    private MockMvc mockMvc; // instancia de MockMvc para simular solicitudes HTTP a los endpoints del controlador
    private CarService carService; // instancia de CarService que será mockeada para simular su comportamiento durante las pruebas
    private AuthService authService; // instancia de AuthService que también será mockeada para simular la validación de tokens durante las pruebas

    @BeforeEach
    void setup() {
        carService = org.mockito.Mockito.mock(CarService.class); // crea un mock de CarService para simular su comportamiento durante las pruebas sin necesidad de una implementación real
        authService = org.mockito.Mockito.mock(AuthService.class); // crea un mock de AuthService para simular la validación de tokens durante las pruebas sin necesidad de una implementación real

        CarController carController = new CarController(carService, authService); // crea una instancia de CarController pasando los mocks de CarService y AuthService como dependencias
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build(); // configura MockMvc para usar la instancia de CarController creada, lo que permite simular solicitudes HTTP a los endpoints del controlador durante las pruebas
    }

    @Test // prueba para verificar que el endpoint de listar autos devuelve un error 401 Unauthorized cuando se proporciona un token inválido
    void testUnauthorizedAccess() throws Exception { // prueba para verificar que el endpoint de listar autos requiere autenticación y devuelve un error 401 Unauthorized cuando se proporciona un token inválido
        mockMvc.perform(get("/api/v1/cars/list") // simula una solicitud GET al endpoint "/api/v1/cars/list" con un encabezado de autorización que contiene un token inválido
                .header("Authorization", "Bearer invalid")) // simula una solicitud GET al endpoint "/api/v1/cars/list" con un encabezado de autorización que contiene un token inválido
                .andExpect(status().isUnauthorized()); // verifica que la respuesta de la solicitud tenga un estado HTTP 401 Unauthorized, lo que indica que el acceso fue denegado debido a un token inválido
    }
}
