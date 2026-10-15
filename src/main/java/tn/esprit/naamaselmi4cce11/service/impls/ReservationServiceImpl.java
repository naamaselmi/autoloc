package tn.esprit.naamaselmi4cce11.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.naamaselmi4cce11.domain.Reservation;
import tn.esprit.naamaselmi4cce11.exception.ResourceNotFoundException;
import tn.esprit.naamaselmi4cce11.repository.IReservationRepository;
import tn.esprit.naamaselmi4cce11.service.IReservationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    @Override
    public Reservation create(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle réservation ne doit pas avoir d'identifiant");
        }
        validate(reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Réservation", id));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation update(Long id, Reservation reservation) {
        validate(reservation);
        Reservation existante = findById(id);
        existante.setDateDebut(reservation.getDateDebut());
        existante.setDateFin(reservation.getDateFin());
        existante.setStatut(reservation.getStatut());
        existante.setClient(reservation.getClient());
        existante.setVehicule(reservation.getVehicule());
        return reservationRepository.save(existante);
    }

    @Override
    public void deleteById(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Réservation", id);
        }
        reservationRepository.deleteById(id);
    }

    private void validate(Reservation reservation) {
        if (reservation.getDateDebut() != null && reservation.getDateFin() != null
                && reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin d'une réservation ne peut pas précéder sa date de début");
        }
    }
}
