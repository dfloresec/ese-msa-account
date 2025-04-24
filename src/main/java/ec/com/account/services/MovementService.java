package ec.com.account.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import ec.com.account.exception.InsufficientBalanceException;
import ec.com.account.exception.ResourceNotFoundException;
import ec.com.account.reposotory.MovementRepository;
import ec.com.account.services.dto.MovementCreateRequestDto;
import ec.com.account.services.dto.MovementRequestDto;
import ec.com.account.services.entities.Account;
import ec.com.account.services.entities.Movement;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovementService {

	private final MovementRepository movementRepository;

	private final AccountService accountService;

	public Movement saveMovement(Movement movement) {
		return movementRepository.save(movement);
	}

	public MovementCreateRequestDto registerMovement(MovementRequestDto movementRequestDto) {

		Account account = accountService.getAccountById(movementRequestDto.getAccountId());

		if (movementRequestDto.getValuee().compareTo(BigDecimal.ZERO) < 0) { // Retiro
			if (account.getBalance().compareTo(movementRequestDto.getValuee().abs()) < 0) {
				throw new InsufficientBalanceException("Saldo no disponible");
			}
			account.setBalance(account.getBalance().subtract(movementRequestDto.getValuee().abs()));
		} else { // Depósito
			account.setBalance(account.getBalance().add(movementRequestDto.getValuee()));
		}

		Movement movement = new Movement();
		movement.setAccount(account);
		movement.setValuee(movementRequestDto.getValuee());
		movement.setCreateDate(LocalDateTime.now());
		saveMovement(movement);

		String msgString = String.format("El movimiento de $%s fue exitoso. Saldo: %s", movement.getValuee(), account.getBalance());
		return MovementCreateRequestDto.builder().Id(movement.getId()).message(msgString).build();

	}

	public Movement getMovementById(Long id) {
		return movementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movement not found"));
	}

	public List<Movement> getAllMovements() {
		return movementRepository.findAll();
	}

	public List<Movement> getMovementsByAccountId(Long accountId, LocalDate startDate, LocalDate endDate) {
		return movementRepository.findMovementsByCustomerIdAndDateRange(accountId, startDate.atStartOfDay(),
				endDate.atTime(LocalTime.MAX));
	}

}