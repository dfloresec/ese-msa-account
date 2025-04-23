package ec.com.account.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.account.services.entities.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
}
