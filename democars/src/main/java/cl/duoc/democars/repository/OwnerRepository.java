package cl.duoc.democars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.democars.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
