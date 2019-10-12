package com.example.domain.mapper;

import com.example.domain.PersistentAuditEventData;

/**
 * 監査イベントテーブルのMapperインターフェース.
 */
public interface PersistenceAuditEventDataDomainMapper {

    void save(PersistentAuditEventData persistentAuditEventData);

}
