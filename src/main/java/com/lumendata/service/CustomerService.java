package com.lumendata.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lumendata.entity.CustomerEntity;
import com.lumendata.entity.CustomerLookupEntity;
import com.lumendata.model.Customer;
import com.lumendata.repository.CustomerLookupRepository;
import com.lumendata.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
@Slf4j
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerLookupRepository customerLookupRepository;

    public void processMessage(String data) {
        String guid=null;
      try{
          ObjectMapper objectMapper=new ObjectMapper();
          objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
          Customer customer=objectMapper.readValue(data,Customer.class);
          List<CustomerEntity> customerEntities=new ArrayList<>();
          List<CustomerLookupEntity> customerLookupEntities=new ArrayList<>();
          guid=customer.getGuid();
          customer.getSource().forEach(source -> {
              CustomerEntity customerEntity=new CustomerEntity();
              CustomerLookupEntity customerLookupEntity=new CustomerLookupEntity();
              customerEntity.setPartyUid(customer.getGuid());
              customerLookupEntity.setPartyUid(customer.getGuid());
              customerEntity.setSource(source.getSource());
              customerLookupEntity.setSource(source.getSource());
              customerEntity.setSourceId(source.getSourceId());
              customerLookupEntity.setSourceId(source.getSourceId());
              customerEntities.add(customerEntity);
              customerLookupEntities.add(customerLookupEntity);
          });
          customerRepository.deleteAll(customerRepository.findByPartyUid(guid));
          customerRepository.saveAll(customerEntities);
          customerLookupRepository.deleteAll(customerLookupRepository.findByPartyUid(guid));
          customerLookupRepository.saveAll(customerLookupEntities);
        }catch (Exception exception){
            log.error("Exception while saving customer data into mv==>customer table guid={}",guid,exception);
        }
    }
}
