package com.beam.mail.account;

import com.beam.mail.mail.Mail;
import jakarta.el.MethodNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public Account createAccount(Account account) {
        if (accountRepository.findByAccountName(account.getAccountName()) != null)
            throw new RuntimeException("Already have this name");
        if (accountRepository.findByMail(account.getMail()) != null)
            throw new RuntimeException("Already have this mail");
        account.setId(UUID.randomUUID().toString());
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(String id) {
        return accountRepository.findById(id).orElseThrow(() -> new MethodNotFoundException("Not Found account"));
    }

    public Account deleteAccount(String id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found account"));
        accountRepository.delete(account);
        return account;

    }

    public List<Account> listBetweenDate(Date startDate, Date endDate) {
        return accountRepository.findAllByBirthDateBetween(startDate, endDate);
    }

    public Account updateAccount(String id, Account account) {
        Account updatedAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found Id"));
        updatedAccount.setAccountName(account.getAccountName());
        updatedAccount.setMail(account.getMail());
        updatedAccount.setBirthDate(account.getBirthDate());
        accountRepository.save(updatedAccount);
        return updatedAccount;
    }

    public Account login(String accountName, String password) {
        return accountRepository.findByAccountName(accountName);

    }


}
