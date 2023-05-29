package com.beam.mail.mail;

import com.beam.mail.Jwt.JwtService;
import com.beam.mail.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping
    public ResponseEntity<?> createMail(@RequestBody Mail mail) {
        mailService.createMail(mail);
        return ResponseEntity.ok("mail sent");
    }

    @GetMapping("/all-mails")
    public ResponseEntity<List<Mail>> getMails() {
        List<Mail> allMails = mailService.getMails();
        return ResponseEntity.ok(allMails);
    }
    //id değil header
    // id ile değil token ile dön

    @GetMapping("/headers")
    public ResponseEntity<?> getMail(@RequestHeader("Authorization") String authorization) throws Exception {

        return ResponseEntity.ok(mailService.getMailWithJWT(authorization));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Mail>> search(@RequestParam String senderId,
                                             @RequestParam String receiver) {
        List<Mail> senderMessage = mailService.searchMailBySenderIdAndReceiver(senderId,receiver);
        return ResponseEntity.ok(senderMessage);
    }

    @GetMapping("/receiver/{receiver}")
    public ResponseEntity<List<Mail>> searchByReceiver(@PathVariable List<String> receiver) {
        List<Mail> sReceiver = mailService.searchByReceiver(receiver);
        return ResponseEntity.ok(sReceiver);
    }

    @GetMapping("/sender/{sender}")
    public ResponseEntity<List<Mail>> searchBySender(@PathVariable String sender) {
        List<Mail> sSender = mailService.searchBySender(sender);
        return ResponseEntity.ok(sSender);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMail(@PathVariable String id) {
        mailService.deleteMail(id);
        return ResponseEntity.ok("mail deleted");
    }

}
