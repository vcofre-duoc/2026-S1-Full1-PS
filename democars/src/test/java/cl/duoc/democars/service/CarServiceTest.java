package cl.duoc.democars.service;

import cl.duoc.democars.dto.carDTO;
import cl.duoc.democars.model.Car;
import cl.duoc.democars.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarServiceTest {

    @Test
    void testGetAllCarsDTO() {
        CarRepository carRepository = Mockito.mock(CarRepository.class); // crea un mock de car repository para simular pruebas sin necesidad de una base de datos real
        CarService carService = new CarService(carRepository); // crea una instancia de CarService pasando el mock de car repository como dependencia

        Car car = new Car(1L, "TESLA", "Model 3", 2026, null); // crea un objeto car con datos de prueba
        Mockito.when(carRepository.findAll()).thenReturn(List.of(car)); // configura el mock para que devuelva una lista con el objeto car cuando se llame al método findAll()

        List<carDTO> result = carService.getAllCarsDTO(); // llama al método getAllCarsDTO() del servicio para obtener la lista de carDTOs

        assertThat(result).hasSize(1); // verifica que la lista resultante tenga un tamaño de 1
        assertThat(result.get(0).getBrand()).isEqualTo("TESLA"); // verifica que la marca del primer carDTO en la lista sea "TESLA"
    }
}
