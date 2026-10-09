# Notes Repository – AutoLoc

## Choix d’interface

| Interface                | Étend                              | Justification |
|--------------------------|------------------------------------|---------------|
| IContratRepository       | JpaRepository<Contrat, Long>       | CRUD complet, findAll renvoie une List, saveAndFlush disponible |
| IPaiementRepository      | JpaRepository<Paiement, Long>      | CRUD complet, findAll renvoie une List, saveAndFlush disponible |
| IAgenceRepository        | JpaRepository<Agence, Long>        | CRUD complet, findAll renvoie une List, saveAndFlush disponible |
| IEmployeRepository       | JpaRepository<Employe, Long>       | CRUD complet, findAll renvoie une List, saveAndFlush disponible |
| IVehiculeRepository      | JpaRepository<Vehicule, Long>      | CRUD complet, findAll renvoie une List, saveAndFlush disponible |
| IEquipementRepository    | JpaRepository<Equipement, Long>    | CRUD complet, findAll renvoie une List, saveAndFlush disponible |
| IClientRepository        | JpaRepository<Client, Long>        | CRUD complet, findAll renvoie une List, saveAndFlush disponible |
| IReservationRepository   | JpaRepository<Reservation, Long>   | CRUD complet, findAll renvoie une List, saveAndFlush disponible |
| IMaintenanceRepository   | JpaRepository<Maintenance, Long>   | CRUD complet, findAll renvoie une List, saveAndFlush disponible |

## Anomalies SonarQube for IDE

| Anomalie détectée                    | Règle / explication                              | Correction apportée |
|--------------------------------------|--------------------------------------------------|---------------------|
| Import inutilisé                     | java:S1128 – Unused imports should be removed    | Suppression des imports inutilisés |
| Annotation @Repository superflue     | Spring Data détecte automatiquement les interfaces | Suppression de @Repository |
| Utilisation de @Data sur une entité  | Risque de StackOverflowError avec associations bidirectionnelles | Remplacé par @Getter / @Setter ciblés |