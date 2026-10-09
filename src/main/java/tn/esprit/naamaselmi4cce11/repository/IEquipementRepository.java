package tn.esprit.naamaselmi4cce11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.naamaselmi4cce11.domain.Equipement;
import tn.esprit.naamaselmi4cce11.domain.Vehicule;

public interface IEquipementRepository extends JpaRepository<Equipement, Long> {
}
