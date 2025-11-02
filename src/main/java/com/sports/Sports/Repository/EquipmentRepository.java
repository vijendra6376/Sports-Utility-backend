package com.sports.Sports.Repository;

import com.sports.Sports.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByNameOfEquipment(String equipmentName);
    List<Equipment> findBySportNameIgnoreCase(String  sportName);
}
/*

🔹 What is a Repository in Spring Data JPA?
A Repository is a mechanism for encapsulating storage, retrieval, and search behavior for a specific domain entity (like User, Team, Equipment, etc).

In Spring Data JPA, a repository interface:

Helps interact with the database (CRUD operations).

Avoids writing boilerplate SQL or JPQL.

Auto-implements methods at runtime.
It provides methods like:

save()

findById()

findAll()

deleteById()

Custom: findByCoachName()
🔹 What is JPA (Java Persistence API)?
JPA is a specification (not an implementation) that defines how Java objects (entities) should map to database tables.

Think of JPA as a set of rules for object-relational mapping (ORM).

Key Concepts:
Entity: Java class mapped to a table (@Entity)

EntityManager: Manages lifecycle of entities (persist, remove, find, etc.)

JPQL: Java Persistence Query Language (object-oriented version of SQL)

🔹 How Repository Works with JPA
Spring Data JPA:

Implements the repository interface at runtime using a proxy.

Translates method names like findByName() into SQL.

Interacts with the EntityManager, which talks to the database via JPA.
 */