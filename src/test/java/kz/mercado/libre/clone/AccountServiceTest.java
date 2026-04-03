package kz.mercado.libre.clone;

import jakarta.inject.Inject;
import kz.mercado.libre.clone.domain.Account;
import kz.mercado.libre.clone.exception.ResourceNotFoundException;
import kz.mercado.libre.clone.repository.AccountRepository;
import kz.mercado.libre.clone.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceTest {
    @Inject
    private AccountService service;

    @Test
    public void testRegisterFindDeleteAccount() throws ResourceNotFoundException {
        Account account = createAccount();

        Account savedAccount = service.registerAccount(account);
        assertNotNull(savedAccount.getId(), "The database should have generated an ID");
        Long generatedId = Long.valueOf(savedAccount.getId());

        Account found = service.getAccountById(generatedId);
        assertNotNull(found);
        assertEquals(account.getUsername(), found.getUsername());

        // 3. Use the generated ID to delete
        Map<String, Boolean> response = service.deleteAccountById(generatedId);
        assertNotNull(response.get("Deleted"));
    }

    private Account createAccount(){
        Account account = new Account();
        account.setUsername("Kazemoto");

        return account;
    }
}
