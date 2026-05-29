# BDCC-IOC

Projet Java/Maven de demonstration de l'inversion de controle (IoC) et de l'injection de dependances avec plusieurs approches :

- instanciation statique ;
- instanciation dynamique par reflexion ;
- injection avec Spring via XML ;
- injection avec Spring via annotations.

## Objectif

Le projet illustre le passage d'un couplage fort vers un couplage faible entre une couche metier et une couche DAO.

La couche metier depend de l'interface `IDao` au lieu de dependre directement d'une implementation concrete. L'objet DAO est ensuite fourni a la couche metier par injection.

## Technologies

- Java 21
- Maven
- Spring Framework 6.1.5

## Structure du projet

```text
src/main/java/net/abdel
├── dao
│   ├── IDao.java
│   └── DaoImpl.java
├── metier
│   ├── IMetier.java
│   └── MetierImpl.java
└── pres
    ├── Pres1.java
    ├── Pres2.java
    ├── PresSpringAnnotations.java
    └── PresSpringXML.java

src/main/resources
├── config.txt
└── config.xml
```

## Description des couches

### Couche DAO

- `IDao` definit la methode `getData()`.
- `DaoImpl` implemente `IDao` et retourne une valeur aleatoire simulee.

### Couche metier

- `IMetier` definit la methode `calcul()`.
- `MetierImpl` utilise un objet `IDao` injecte avec `setDao(...)`.
- Le calcul applique un coefficient multiplicateur a la valeur retournee par la DAO.

### Couche presentation

- `Pres1` : injection manuelle avec `new`.
- `Pres2` : injection dynamique en lisant les classes depuis `config.txt`.
- `PresSpringXML` : injection par Spring avec le fichier `config.xml`.
- `PresSpringAnnotations` : injection par Spring avec les annotations `@Component`, `@Service` et `@Autowired`.

## Configuration

### Injection dynamique

Le fichier `src/main/resources/config.txt` contient les classes a instancier :

```text
net.abdel.dao.DaoImpl
net.abdel.metier.MetierImpl
```

`Pres2` lit ce fichier, cree les objets par reflexion, puis injecte la DAO dans la couche metier.

### Injection Spring XML

Le fichier `src/main/resources/config.xml` declare les beans Spring :

```xml
<bean id="dao" class="net.abdel.dao.DaoImpl" />
<bean id="metier" class="net.abdel.metier.MetierImpl">
    <property name="dao" ref="dao" />
</bean>
```

`PresSpringXML` charge ce contexte avec `ClassPathXmlApplicationContext`.

### Injection Spring annotations

Les classes sont annotees :

- `DaoImpl` avec `@Component("dao")`
- `MetierImpl` avec `@Service`
- `setDao(...)` avec `@Autowired`

`PresSpringAnnotations` scanne les packages `net.abdel.dao` et `net.abdel.metier`.

## Installation et compilation

Depuis la racine du projet :

```bash
mvn clean compile
```

## Execution

Les classes principales peuvent etre lancees depuis un IDE ou avec Maven.

Exemples :

```bash
mvn exec:java -Dexec.mainClass="net.abdel.pres.Pres1"
mvn exec:java -Dexec.mainClass="net.abdel.pres.Pres2"
mvn exec:java -Dexec.mainClass="net.abdel.pres.PresSpringXML"
mvn exec:java -Dexec.mainClass="net.abdel.pres.PresSpringAnnotations"
```

Chaque execution affiche une valeur de resultat calculee a partir d'une valeur aleatoire fournie par la DAO.

## Points importants

- L'interface `IDao` permet de remplacer facilement l'implementation DAO.
- `MetierImpl` ne cree pas lui-meme la DAO : elle lui est injectee.
- Spring automatise la creation des objets et l'injection des dependances.
- Le projet compare plusieurs manieres d'obtenir le meme assemblage d'objets.

## Auteur

Abdelbadii Elouedrhiri
