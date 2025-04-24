package ec.com.account.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ec.com.account.services.AccountService;
import ec.com.account.services.entities.Account;

@ExtendWith(SpringExtension.class)
class AccountControlerTest {

	@InjectMocks
	private AccountControler accountControler;

	@Mock
	private AccountService accountService;

	@Test
	void testCreateAccount() {
		Account mockAccount = new Account();
		mockAccount.setNumber("123456");

		when(accountService.saveAccount(mockAccount)).thenReturn(mockAccount);

		Account result = accountControler.createAccount(mockAccount);

		assertNotNull(result);
		assertEquals("123456", result.getNumber());
		verify(accountService, times(1)).saveAccount(mockAccount);
	}

	@Test
	void testGetAccounts() {
		Account mockAccount1 = new Account();
		Account mockAccount2 = new Account();
		List<Account> mockList = Arrays.asList(mockAccount1, mockAccount2);

		when(accountService.getAccounts()).thenReturn(mockList);

		ResponseEntity<List<Account>> response = accountControler.getAccounts();

		assertNotNull(response);
		assertEquals(2, response.getBody().size());
		verify(accountService, times(1)).getAccounts();
	}

	@Test
	void testGetAccountsByCustomer() {
		Account mockAccount1 = new Account();
		Account mockAccount2 = new Account();
		List<Account> mockList = Arrays.asList(mockAccount1, mockAccount2);

		when(accountService.getAccountsByCustomer(1L)).thenReturn(mockList);

		ResponseEntity<List<Account>> response = accountControler.getAccountsByCustomer(1L);

		assertNotNull(response);
		assertEquals(2, response.getBody().size());
		verify(accountService, times(1)).getAccountsByCustomer(1L);
	}

	@Test
	void testGetAccountById() {
		Account mockAccount = new Account();
		mockAccount.setId(1L);

		when(accountService.getAccountById(1L)).thenReturn(mockAccount);

		Account result = accountControler.getAccountById(1L);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		verify(accountService, times(1)).getAccountById(1L);
	}

	@Test
	void testUpdateAccount() {
		Account existingAccount = new Account();
		existingAccount.setId(1L);
		existingAccount.setNumber("123456");

		Account updatedDetails = new Account();
		updatedDetails.setNumber("654321");

		when(accountService.getAccountById(1L)).thenReturn(existingAccount);
		when(accountService.saveAccount(any(Account.class))).thenReturn(existingAccount);

		Account result = accountControler.updateAccount(1L, updatedDetails);

		assertNotNull(result);
		assertEquals("654321", result.getNumber());
		verify(accountService, times(1)).getAccountById(1L);
		verify(accountService, times(1)).saveAccount(existingAccount);
	}

	@Test
	void testDeleteAccount() {
		Account mockAccount = new Account();
		mockAccount.setId(1L);
		mockAccount.setState(true);

		when(accountService.getAccountById(1L)).thenReturn(mockAccount);

		ResponseEntity<?> response = accountControler.deleteAccount(1L);

		assertNotNull(response);
		verify(accountService, times(1)).getAccountById(1L);
		verify(accountService, times(1)).saveAccount(mockAccount);
		assertFalse(mockAccount.isState());
	}
}
