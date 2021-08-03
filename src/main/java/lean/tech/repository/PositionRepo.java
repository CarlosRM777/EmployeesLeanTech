package lean.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lean.tech.model.Position;

public interface PositionRepo extends JpaRepository<Position, Long> {

}
