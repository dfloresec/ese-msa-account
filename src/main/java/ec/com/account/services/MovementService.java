package ec.com.account.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.com.account.exception.ResourceNotFoundException;
import ec.com.account.reposotory.MovementRepository;
import ec.com.account.services.entities.Movement;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovementService {

	private final MovementRepository movementRepository;

	public Movement saveMovement(Movement movement) {
		return movementRepository.save(movement);
	}

	public List<Movement> getAllMovements() {
		return movementRepository.findAll();
	}

	public Movement getMovementById(Long id) {
		return movementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movement not found"));
	}

}
