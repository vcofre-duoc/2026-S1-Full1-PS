package cl.duoc.democars.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.democars.dto.carDTO;
import cl.duoc.democars.repository.CarRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Lombok genera constructor con los campos final
public class CarService {

    private final CarRepository carRepository;

    
    // Listar todos los usuarios
    public List<carDTO> getAllCarsDTO() {
        return carRepository.findAll()
                .stream()
                .map(car -> new carDTO(
                        car.getBrand(),
                        car.getModel(),
                        car.getYear()
                ))
                .toList();
    }
}
