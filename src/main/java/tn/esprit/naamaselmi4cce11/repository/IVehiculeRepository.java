package tn.esprit.naamaselmi4cce11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.naamaselmi4cce11.domain.Vehicule;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {
}
