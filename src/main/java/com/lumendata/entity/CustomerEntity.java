package com.lumendata.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
@IdClass(CustomerUniqueId.class)
public class CustomerEntity {

    @Id
    @Column(name="empl_id")
    private String sourceId;

    @Id
    @Column(name="source_name")
    private String source;

    @Column(name = "constituent_id", nullable = false)
    private String partyUid;

}
