package cl.duoc.democars.service;

import cl.duoc.democars.dto.carDTO;
import cl.duoc.democars.dto.OwnerCarsDTO;
import cl.duoc.democars.model.Owner;
import cl.duoc.democars.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerCarsDTO getOwnerCars(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        List<carDTO> cars = owner.getCars().stream()
                .map(car -> new carDTO(
                        car.getBrand(),
                        car.getModel(),
                        car.getYear(),
                        owner.getName() // Agrega el nombre del dueño al DTO del auto
                ))
                .toList();

        return new OwnerCarsDTO(owner.getId(), owner.getName(), cars);
    }
}
