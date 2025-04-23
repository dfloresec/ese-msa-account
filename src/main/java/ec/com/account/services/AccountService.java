package ec.com.account.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.com.account.exception.ResourceNotFoundException;
import ec.com.account.reposotory.AccountRepository;
import ec.com.account.services.entities.Account;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;

	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}

	public List<Account> getAccountsByCustomer(Long idCustomer) {
		return accountRepository.findByIdCustomerAndStateTrue(idCustomer);
	}
	
	public List<Account> getAccounts() {
		return accountRepository.findByStateTrue();
	}

	public Account getAccountById(Long id) {
		return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
	}

}
