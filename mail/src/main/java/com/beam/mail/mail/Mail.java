package com.beam.mail.mail;

import com.beam.mail.account.Account;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@Accessors(chain = true)
@TypeAlias("mail")
public class Mail {
    @Id
    private String id;
    private String subject;
    private String message;
    private String sender;
    private List<String> receiver;


}
