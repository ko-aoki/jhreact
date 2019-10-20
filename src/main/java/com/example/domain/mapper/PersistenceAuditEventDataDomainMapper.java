package com.example.domain.mapper;

import com.example.domain.PersistentAuditEventData;
import org.apache.ibatis.annotations.Mapper;

/**
 * 監査イベントテーブルのMapperインターフェース.
 */
@Mapper
public interface PersistenceAuditEventDataDomainMapper {

    void save(PersistentAuditEventData persistentAuditEventData);

}
