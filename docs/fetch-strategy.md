# Stratégie de Fetch et Cascade – AutoLoc

| Association              | Fetch | Cascade                  | Justification |
|--------------------------|-------|--------------------------|---------------|
| Contrat → Paiement       | LAZY  | ALL + orphanRemoval     | Un paiement n’existe que rattaché à son contrat (composition). Supprimer le contrat doit supprimer ses paiements. Retirer un paiement de la collection le supprime aussi. |
| Agence → Vehicule        | LAZY  | Aucune                   | Un véhicule survit à la suppression de son agence. |
| Agence → Employe         | LAZY  | Aucune                   | Un employé peut changer d’agence ; pas de composition. |
| Vehicule ↔ Equipement    | LAZY  | Aucune                   | Les équipements sont partagés entre plusieurs véhicules. Utilisation d’un Set. |
| Client → Reservation     | LAZY  | PERSIST                  | On peut créer une réservation avec le client, mais la suppression du client ne doit pas supprimer les réservations. |
| Reservation → Vehicule   | LAZY  | Aucune                   | Une réservation référence un véhicule existant. |
| Reservation ↔ Contrat    | LAZY  | ALL (côté Reservation)   | Un contrat est lié à une réservation et n’a de sens qu’avec elle. |
| Vehicule → Maintenance   | LAZY  | PERSIST                  | On peut enregistrer des maintenances avec le véhicule, sans supprimer l’historique en cas de suppression du véhicule. |