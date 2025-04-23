package ec.com.account.reposotory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.account.services.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findByStateTrue();
	
	List<Account> findByIdCustomerAndStateTrue(Long idCustomer);
}
