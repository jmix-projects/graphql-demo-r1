package com.company.graphqldemor1.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum CustomerStatus implements EnumClass<String> {

    ACTIVE("A"),
    INACTIVE("B");

    private String id;

    CustomerStatus(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static CustomerStatus fromId(String id) {
        for (CustomerStatus at : CustomerStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}