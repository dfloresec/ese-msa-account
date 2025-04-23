package ec.com.account.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.account.services.AccountService;
import ec.com.account.services.entities.Account;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cuentas")
public class AccountControler {

	private final AccountService service;

	@PostMapping
	public Account createAccount(@RequestBody Account person) {
		return service.saveAccount(person);
	}
	
	@GetMapping
	public ResponseEntity<List<Account>> getAccounts() {
		List<Account> list = service.getAccounts();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<List<Account>> getAccountsByCustomer(@PathVariable("id") Long idCustomer) {
		List<Account> list = service.getAccountsByCustomer(idCustomer);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public Account getAccountById(@PathVariable("id") Long id) {
		return service.getAccountById(id);
	}

	@PutMapping("/{id}")
	public Account updateAccount(@PathVariable("id") Long id, @RequestBody Account accountDetails) {
		Account account = service.getAccountById(id);
		account.setNumber(accountDetails.getNumber());
		account.setType(accountDetails.getType());
		account.setIdCustomer(accountDetails.getIdCustomer());
		return service.saveAccount(account);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id) {
		Account account = service.getAccountById(id);
		account.setState(false);
		service.saveAccount(account);
		return ResponseEntity.ok().build();
	}

}
