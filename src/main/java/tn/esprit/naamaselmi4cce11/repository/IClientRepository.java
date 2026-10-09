package tn.esprit.naamaselmi4cce11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.naamaselmi4cce11.domain.Client;
import tn.esprit.naamaselmi4cce11.domain.Vehicule;

public interface IClientRepository extends JpaRepository<Client, Long> {
}
