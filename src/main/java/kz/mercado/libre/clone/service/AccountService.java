package kz.mercado.libre.clone.service;

import kz.mercado.libre.clone.domain.Account;
import kz.mercado.libre.clone.exception.ResourceNotFoundException;
import kz.mercado.libre.clone.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    public Account registerAccount(Account account){
        return repository.save(account);
    }

    public List<Account> getAllAccounts(){
        return repository.findAll();
    }

    public Account getAccountById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return repository.findById(Math.toIntExact(id)).orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + id));
    }

    public Map<String, Boolean> deleteAccountById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        Account account =  repository.findById(Math.toIntExact(id)).orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + id));

        repository.delete(account);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);

        return response;
    }
}
