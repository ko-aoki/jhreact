package com.example.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Persist AuditEvent managed by the Spring Boot actuator.
 *
 * @see org.springframework.boot.actuate.audit.AuditEvent
 */
public class FlatPersistentAuditEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String principal;

    private Instant auditEventDate;

    private String auditEventType;

    private String authorityName;

    private String authorityValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Instant getAuditEventDate() {
        return auditEventDate;
    }

    public void setAuditEventDate(Instant auditEventDate) {
        this.auditEventDate = auditEventDate;
    }

    public String getAuditEventType() {
        return auditEventType;
    }

    public void setAuditEventType(String auditEventType) {
        this.auditEventType = auditEventType;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityValue() {
        return authorityValue;
    }

    public void setAuthorityValue(String authorityValue) {
        this.authorityValue = authorityValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatPersistentAuditEvent that = (FlatPersistentAuditEvent) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(principal, that.principal) &&
            Objects.equals(auditEventDate, that.auditEventDate) &&
            Objects.equals(auditEventType, that.auditEventType) &&
            Objects.equals(authorityName, that.authorityName) &&
            Objects.equals(authorityValue, that.authorityValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, principal, auditEventDate, auditEventType, authorityName, authorityValue);
    }

    @Override
    public String toString() {
        return "FlatPersistentAuditEvent{" +
            "id=" + id +
            ", principal='" + principal + '\'' +
            ", auditEventDate=" + auditEventDate +
            ", auditEventType='" + auditEventType + '\'' +
            ", authorityName='" + authorityName + '\'' +
            ", authorityValue='" + authorityValue + '\'' +
            '}';
    }
}
