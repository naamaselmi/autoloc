package tn.esprit.naamaselmi4cce11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.naamaselmi4cce11.domain.Reservation;
import tn.esprit.naamaselmi4cce11.domain.Vehicule;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}
