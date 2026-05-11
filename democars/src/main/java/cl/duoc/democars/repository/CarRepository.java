package cl.duoc.democars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.democars.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
