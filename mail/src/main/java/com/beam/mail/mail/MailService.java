package com.beam.mail.mail;

import com.beam.mail.account.AccountRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MailService {
    private final MailRepository mailRepository;
    private final AccountRepository accountRepository;

    public Mail createMail(Mail mail) {
        mail.setId(UUID.randomUUID().toString());
        return mailRepository.save(mail);
    }

    public List<Mail> getMails() {
        return mailRepository.findAll();
    }


    public Mail deleteMail(String id) {
        Mail mail = mailRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found mail"));
        mailRepository.delete(mail);
        return mail;
    }

    public List<Mail> searchMailBySenderIdAndReceiver(String senderId, String message) {
        List<Mail> mail = mailRepository.searchMailBySenderAndReceiver(senderId, message);
        return mail;
    }

    public List<Mail> searchByReceiver(List<String> receiver) {
        List<Mail> mail = mailRepository.findAllByReceiver(receiver);
        return mail;
    }

    public List<Mail> searchBySender(String senderId) {
        List<Mail> sSenderId = mailRepository.findAllBySender(senderId);
        return sSenderId;
    }

    public Mail getMail(String id) {
        return mailRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<Mail> getMailWithJWT(String authorization) throws Exception {
        String token = authorization.split(" ")[1];

        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];

        SignatureAlgorithm sa = SignatureAlgorithm.HS512;
        String secretKey = "-Secret-";

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), sa.getJcaName());

        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);

        JSONObject object = new JSONObject(payload);
        String userId = (String) object.get("sub");
/*
  if (!validator.isValid(tokenWithoutSignature, signature)) {
            throw new Exception("Could not verify JWT token integrity!");
        }
 */

        //return mailRepository.findAllByReceiverIn(userId);
        return mailRepository.findAllByReceiverIn("alıcı");
    }
}
