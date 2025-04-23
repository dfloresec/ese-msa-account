package ec.com.account.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import ec.com.account.exception.InsufficientBalanceException;
import ec.com.account.exception.ResourceNotFoundException;
import ec.com.account.reposotory.MovementRepository;
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

	public BigDecimal registerMovement(MovementRequestDto movementRequestDto) {

		Account account = accountService.getAccountById(movementRequestDto.getAccountId());

		if (movementRequestDto.getValuee().compareTo(BigDecimal.ZERO) < 0) { // Retiro
			if (account.getBalance().compareTo(movementRequestDto.getValuee().abs()) < 0) {
				throw new InsufficientBalanceException("Saldo insuficiente");
			}
			account.setBalance(account.getBalance().subtract(movementRequestDto.getValuee().abs()));
		} else { // Depósito
			account.setBalance(account.getBalance().add(movementRequestDto.getValuee()));
		}

		Movement movement = new Movement();
		movement.setAccount(account);
		movement.setValuee(movementRequestDto.getValuee());
		saveMovement(movement);

		return account.getBalance();

	}
//	
//
//@Transactional
//public String registerMovement(Long accountId, BigDecimal valuee) {
//Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
//
//        if (valuee.compareTo(BigDecimal.ZERO) < 0) { // Retiro
//            if (account.getBalance().compareTo(valuee.abs()) < 0) {
//                return "Insufficient balance for withdrawal";
//            }
//            account.setBalance(account.getBalance().subtract(valuee.abs()));
//        } else { // Depósito
//            account.setBalance(account.getBalance().add(valuee));
//        }
//
//        Movement movement = new Movement();
//        movement.setAccount(account);
//        movement.setValuee(valuee);
//        movementRepository.save(movement);
//
//        accountRepository.save(account);
//
//        return "Movement registered successfully";
//    }

	public Movement getMovementById(Long id) {
		return movementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movement not found"));
	}

	public List<Movement> getAllMovements() {
		return movementRepository.findAll();
	}
}