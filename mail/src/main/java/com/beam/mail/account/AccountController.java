package com.beam.mail.account;

import com.beam.mail.Jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    private final AccountRepository accountRepository;
    private final JwtService jwtService;

    //LOGİN
    //login başarılı olursa; JWT token üret ve client a dön.

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        String accountName = account.getAccountName();
        String password = account.getPassword();
        Account acc = accountService.login(accountName,password);
        if (acc!= null ) {
            String token = jwtService.generateToken(acc.getId());
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Account Name or Password");
    }


    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return ResponseEntity.ok("created");
    }

    @GetMapping("/all-accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> allAccounts = accountService.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable String id) {
        Account getaccount = accountService.getAccount(id);
        return ResponseEntity.ok(getaccount);
    }

    @GetMapping("/search-date")
    public ResponseEntity<List<Account>> listBetweenDate(@RequestParam Date startDate,
                                                         @RequestParam Date endDate) {
        List<Account> betweenDate = accountService.listBetweenDate(startDate,endDate);
        return ResponseEntity.ok(betweenDate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable String id,
                                           @RequestBody Account account) {
        accountService.updateAccount(id,account);
        return ResponseEntity.ok("updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("deleted");
    }
}
