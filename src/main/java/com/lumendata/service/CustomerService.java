package com.lumendata.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lumendata.entity.CustomerEntity;
import com.lumendata.model.Customer;
import com.lumendata.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public void processMessage(String data) {
        String guid=null;
      try{
          ObjectMapper objectMapper=new ObjectMapper();
          Customer customer=objectMapper.readValue(data,Customer.class);
          List<CustomerEntity> customerEntities=new ArrayList<>();
          guid=customer.getGuid();
          customer.getSource().forEach(source -> {
              CustomerEntity customerEntity=new CustomerEntity();
              customerEntity.setPartyUid(customer.getGuid());
              customerEntity.setSource(source.getSource());
              customerEntity.setSourceId(source.getSourceId());
              customerEntities.add(customerEntity);
          });
          customerRepository.saveAll(customerEntities);
        }catch (Exception exception){
            log.error("Exception while saving customer data into mv==>customer table guid={}",guid,exception);
        }
    }
}
