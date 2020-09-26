package com.lumendata.entity;

import java.io.Serializable;
import java.util.Objects;

public class CustomerUniqueId implements Serializable {
    private String sourceId;
    private String source;

    public CustomerUniqueId() {
    }

    public CustomerUniqueId(String sourceId, String source) {
        this.sourceId = sourceId;
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerUniqueId customerUniqueId = (CustomerUniqueId) o;
        return sourceId.equals(customerUniqueId.sourceId) &&
                source.equals(customerUniqueId.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceId, source);
    }
}
