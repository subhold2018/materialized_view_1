package com.lumendata.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer_lookup")
@Data
@IdClass(CustomerUniqueId.class)
public class CustomerLookupEntity {

    @Id
    @Column(name="empl_id")
    private String sourceId;

    @Id
    @Column(name="source_name")
    private String source;

    @Column(name = "scc_uid", nullable = false)
    private String partyUid;

}
