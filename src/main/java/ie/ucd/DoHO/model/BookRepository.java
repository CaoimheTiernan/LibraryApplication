package ie.ucd.DoHO.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Artifact, Integer> {
}