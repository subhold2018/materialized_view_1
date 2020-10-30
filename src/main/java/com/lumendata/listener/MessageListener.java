package com.lumendata.listener;

import com.lumendata.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Component
@Slf4j
public class MessageListener {

    @Autowired
    CustomerService customerService;

    @KafkaListener(topics = "${customer.destination.mv-publish.topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void listenGeneralMessage(String message) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        log.info("Received Genral Message={}",message);
        customerService.processMessage(message);
    }

    @KafkaListener(topics = "${customer.destination.mv-merge-publish}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void listenMergeMessage(String message) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        log.info("Received Merge Message={}",message);
        customerService.processMessage(message);
    }
}