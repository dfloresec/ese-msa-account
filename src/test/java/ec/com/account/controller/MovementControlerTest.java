package ec.com.account.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ec.com.account.services.MovementService;
import ec.com.account.services.dto.MovementRequestDto;
import ec.com.account.services.entities.Movement;

@ExtendWith(SpringExtension.class)
class MovementControlerTest {

	@Mock
	private MovementService movementService;

	@InjectMocks
	private MovementControler movementControler;

	@Test
	void testCreateAccount() {
		MovementRequestDto requestDto = new MovementRequestDto();
		requestDto.setValuee(new BigDecimal("100.00"));

		when(movementService.registerMovement(requestDto)).thenReturn(new BigDecimal("500.00"));

		ResponseEntity<String> response = movementControler.createAccount(requestDto);

		assertNotNull(response);
		assertEquals("El movimiento fue registrado, saldo en la cuenta $500.00", response.getBody());
		verify(movementService, times(1)).registerMovement(requestDto);
	}

	@Test
	void testGetMovementByAccount() {
		Long accountId = 1L;
		LocalDate startDate = LocalDate.of(2023, 1, 1);
		LocalDate endDate = LocalDate.of(2023, 12, 31);

		Movement mockMovement1 = new Movement();
		Movement mockMovement2 = new Movement();
		List<Movement> mockList = Arrays.asList(mockMovement1, mockMovement2);

		when(movementService.getMovementsByAccountId(accountId, startDate, endDate)).thenReturn(mockList);

		ResponseEntity<List<Movement>> response = movementControler.getMovenmentByAccount(accountId, startDate,
				endDate);

		assertNotNull(response);
		assertEquals(2, response.getBody().size());
		verify(movementService, times(1)).getMovementsByAccountId(accountId, startDate, endDate);
	}
}
