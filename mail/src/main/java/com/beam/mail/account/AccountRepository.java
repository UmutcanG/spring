package com.beam.mail.account;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account,String> {
    List<Account> findAllByBirthDateBetween(Date startDate,Date endDate);

    Account findByMail(String mail);
    Account findByAccountName(String accountName);

}