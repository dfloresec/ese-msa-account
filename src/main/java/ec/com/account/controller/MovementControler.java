package ec.com.account.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.account.services.MovementService;
import ec.com.account.services.dto.MovementRequestDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movimientos")
public class MovementControler {

	private final MovementService service;

	@PostMapping
	public ResponseEntity<String> createAccount(@RequestBody MovementRequestDto movementRequest) {
		BigDecimal balance = service.registerMovement(movementRequest);
		return ResponseEntity.ok(String.format("El movimiento fue registrado, saldo en la cuenta $%s", balance));
	}

}
