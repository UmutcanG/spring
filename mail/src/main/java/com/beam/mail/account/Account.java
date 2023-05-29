package com.beam.mail.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@TypeAlias("Account")
public class Account {

    @Id
    private String id;
    private String accountName;
    private String password;
    private String mail;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

}
