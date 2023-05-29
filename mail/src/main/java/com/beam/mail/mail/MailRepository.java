package com.beam.mail.mail;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends MongoRepository<Mail,String> {
    List<Mail> searchMailBySenderAndReceiver(String sender,String receiver);
    List<Mail> findAllByReceiver(List<String> receiver);
    List<Mail> findAllBySender(String senderId);

    List<Mail> findAllByReceiverIn(String id);


}
