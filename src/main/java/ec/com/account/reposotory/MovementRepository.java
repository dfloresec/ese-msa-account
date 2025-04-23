package ec.com.account.reposotory;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.com.account.services.entities.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

	@Query("SELECT m FROM Movement m WHERE m.account.id = :accountId AND m.createDate BETWEEN :startDate AND :endDate order by m.createDate desc")
	List<Movement> findMovementsByCustomerIdAndDateRange(@Param("accountId") Long accountId,
			@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
