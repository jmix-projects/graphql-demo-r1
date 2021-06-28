package com.company.graphqldemor1.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "CUSTOMER")
@Entity
public class Customer {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    private String status;

    @JoinColumn(name = "CUSTOMER_GUARANTOR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customerGuarantor;

    @JoinColumn(name = "CARD_ID")
    @Composition
    @OneToOne(fetch = FetchType.LAZY)
    private Card card;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Customer getCustomerGuarantor() {
        return customerGuarantor;
    }

    public void setCustomerGuarantor(Customer customerGuarantor) {
        this.customerGuarantor = customerGuarantor;
    }

    public CustomerStatus getStatus() {
        return status == null ? null : CustomerStatus.fromId(status);
    }

    public void setStatus(CustomerStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"name"})
    public String getInstanceName() {
        return String.format("%s", name);
    }
}