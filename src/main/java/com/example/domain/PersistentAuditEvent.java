package com.example.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Persist AuditEvent managed by the Spring Boot actuator.
 *
 * @see org.springframework.boot.actuate.audit.AuditEvent
 */
public class PersistentAuditEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long eventId;

    @NotNull
    private String principal;

    private Instant auditEventDate;

    private String auditEventType;

    private List<KeyValue> keyValueList = new ArrayList<>();

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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

    public Map<String, String> getData() {
        if (keyValueList == null) {
            return Collections.EMPTY_MAP;
        }

        return keyValueList.stream().collect(
            Collectors.toMap(KeyValue::getKey, KeyValue::getValue)
        );
    }

    public void setKeyValueList(List<KeyValue> data ) {
        this.keyValueList = data;
    }

    public void setData(Map<String, String> dataMap ) {

        if (dataMap == null) {
            return;
        }

        List<KeyValue> dataList = dataMap.entrySet().stream()
            .map(
                d -> {
                    KeyValue kv = new KeyValue();
                    kv.setKey(d.getKey());
                    kv.setValue(d.getValue());
                    return kv;
                }
            )
            .collect(
                Collectors.toList()
            );
        this.setKeyValueList(dataList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersistentAuditEvent)) {
            return false;
        }
        return eventId != null && eventId.equals(((PersistentAuditEvent) o).eventId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PersistentAuditEvent{" +
            "eventId=" + eventId +
            ", principal='" + principal + '\'' +
            ", auditEventDate=" + auditEventDate +
            ", auditEventType='" + auditEventType + '\'' +
            ", keyValueList=" + keyValueList + '\'' +
            ", data=" + getData() +
            '}';
    }
}
