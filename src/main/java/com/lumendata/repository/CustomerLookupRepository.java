package com.lumendata.repository;

import com.lumendata.entity.CustomerEntity;
import com.lumendata.entity.CustomerLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerLookupRepository extends JpaRepository<CustomerLookupEntity,String> {
    Iterable<? extends CustomerLookupEntity> findByPartyUid(String guid);
}
