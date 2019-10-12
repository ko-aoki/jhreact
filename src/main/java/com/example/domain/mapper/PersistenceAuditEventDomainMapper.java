package com.example.domain.mapper;

import com.example.domain.FlatPersistentAuditEvent;
import com.example.domain.PersistentAuditEvent;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

/**
 * 監査イベントテーブルのMapperインターフェース.
 */
public interface PersistenceAuditEventDomainMapper {

    List<FlatPersistentAuditEvent> findById(@Param("id") Long id);

    List<FlatPersistentAuditEvent> findAll();

    int countAll();

    List<FlatPersistentAuditEvent> findAll(@Param("pageable") Pageable pageable);

    void deleteAll();

    void save(PersistentAuditEvent persistentAuditEvent);

    List<FlatPersistentAuditEvent> findByPrincipal(@Param("principal")String principal);

    List<FlatPersistentAuditEvent> findByAuditEventDateAfter(@Param("after")Instant after);

    List<FlatPersistentAuditEvent> findByPrincipalAndAuditEventDateAfter(
        @Param("principal")String principal, @Param("after")Instant after);

    int countAllByAuditEventDateBetween(@Param("from")Instant fromDate, @Param("to")Instant toDate);

    List<PersistentAuditEvent> findAllByAuditEventDateBetween(
        @Param("from")Instant fromDate, @Param("to")Instant toDate, @Param("pageable")Pageable pageable);
}
