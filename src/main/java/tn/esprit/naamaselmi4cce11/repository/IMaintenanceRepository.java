package tn.esprit.naamaselmi4cce11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.naamaselmi4cce11.domain.Maintenance;
import tn.esprit.naamaselmi4cce11.domain.Vehicule;

public interface IMaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
