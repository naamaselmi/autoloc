package tn.esprit.naamaselmi4cce11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.naamaselmi4cce11.domain.Employe;
import tn.esprit.naamaselmi4cce11.domain.Vehicule;

public interface IEmployeRepository extends JpaRepository<Employe, Long> {
}
