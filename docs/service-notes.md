# Notes Service - AutoLoc

## A. Modes d'injection comparés

| Critère | Injection par constructeur | Injection par attribut |
|---|---|---|
| Champ `final` possible | Oui | Non |
| Dépendance visible | Oui, dans le constructeur | Non, elle est cachée dans la classe |
| Utilisation avec `new` hors du conteneur | Oui, en fournissant la dépendance | Risque d'avoir une dépendance `null` |
| Objet toujours valide après construction | Oui | Non |
| Signalement SonarQube | Non | Oui, règle S6813 |

L'injection par constructeur est utilisée dans tous les services avec un champ `final` et `@RequiredArgsConstructor`.

## B. Services

| Service | Dépendance injectée | Mode | Justification |
|---|---|---|---|
| `AgenceServiceImpl` | `IAgenceRepository` | Constructeur | Dépendance obligatoire, champ `final` et couplage à une interface |
| `EmployeServiceImpl` | `IEmployeRepository` | Constructeur | Dépendance obligatoire, champ `final` et couplage à une interface |
| `VehiculeServiceImpl` | `IVehiculeRepository` | Constructeur | Dépendance obligatoire, champ `final` et couplage à une interface |
| `EquipementServiceImpl` | `IEquipementRepository` | Constructeur | Dépendance obligatoire, champ `final` et couplage à une interface |
| `ClientServiceImpl` | `IClientRepository` | Constructeur | Dépendance obligatoire, champ `final` et couplage à une interface |
| `ReservationServiceImpl` | `IReservationRepository` | Constructeur | Dépendance obligatoire, champ `final` et couplage à une interface |
| `ContratServiceImpl` | `IContratRepository` | Constructeur | Dépendance obligatoire, champ `final` et couplage à une interface |
| `PaiementServiceImpl` | `IPaiementRepository` | Constructeur | Dépendance obligatoire, champ `final` et couplage à une interface |
| `MaintenanceServiceImpl` | `IMaintenanceRepository` | Constructeur | Dépendance obligatoire, champ `final` et couplage à une interface |

## C. Messages d'erreur

### Message 1 - bean `IContratService` introuvable

Les deux causes les plus probables sont :

1. l'annotation `@Service` est absente de `ContratServiceImpl` ;
2. `ContratServiceImpl` est placé en dehors du package racine scanné par Spring.

La correction consiste à ajouter `@Service` et à placer la classe sous `tn.esprit.naamaselmi4cce11`.

### Message 2 - deux beans du même type

Deux corrections possibles :

1. placer `@Primary` sur la classe de l'implémentation qui doit être choisie par défaut ;
2. placer `@Qualifier("nomDuBean")` sur le paramètre du constructeur pour choisir explicitement l'implémentation.

### Message 3 - dépendance circulaire

Avec l'injection par constructeur, chaque objet doit recevoir toutes ses dépendances au moment de sa création. Si deux services dépendent l'un de l'autre, Spring ne peut construire aucun des deux.

Il faut supprimer le cycle, par exemple en extrayant la responsabilité commune dans un troisième service dont les deux premiers dépendent.

## D. Anomalies SonarQube for IDE

| Anomalie | Règle / explication | Correction apportée |
|---|---|---|
| Injection par attribut testée dans le service | S6813 : l'injection par attribut cache la dépendance et empêche le champ `final` | Restauration de `@RequiredArgsConstructor` et du champ repository `final` |
| Exception générique testée dans `findById` | S112 : une exception générique ne décrit pas correctement l'erreur métier | Utilisation de `ResourceNotFoundException` |
| Import `Autowired` inutilisé dans `VehiculeServiceImpl` | S1128 : les imports inutilisés doivent être supprimés | Suppression de l'import et du commentaire associé |

## E. Questions de compréhension

### 1. Où est le `new` de `ContratServiceImpl` et qui l'exécute ?

Il n'est pas écrit dans le code de l'application. Le conteneur Spring détecte `@Service`, appelle le constructeur et crée le bean.

### 2. Pourquoi un contrôleur dépend-il de `IContratService` et non de `ContratServiceImpl` ?

Le contrôleur dépend d'une abstraction et non d'une classe concrète. L'implémentation peut ainsi être remplacée sans modifier le contrôleur.

### 3. Pourquoi un service singleton doit-il rester sans état ?

Une seule instance est partagée entre plusieurs requêtes et plusieurs threads. Si le service conservait un attribut comme `contratCourant`, une requête pourrait lire ou remplacer la valeur d'une autre requête.

### 4. Pourquoi `update` charge-t-il l'entité existante avant `save` ?

Cela permet de conserver l'identifiant et les associations qui ne doivent pas être remplacées, puis de recopier seulement les champs modifiables. Sauvegarder directement l'objet reçu pourrait écraser des colonnes ou vider une collection avec `orphanRemoval`.

### 5. Différences entre les annotations et rôle du service Paiement

- `@Component` déclare une classe détectée par le scan, tandis que `@Bean` déclare explicitement un objet dans une classe de configuration.
- `@Primary` indique le bean choisi par défaut, tandis que `@Qualifier` sélectionne explicitement un bean à un point d'injection.
- `IPaiementService` n'expose que la lecture, car la création, la modification et la suppression des paiements passent par le contrat.
