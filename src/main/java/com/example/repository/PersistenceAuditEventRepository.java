package com.example.repository;

import com.example.domain.FlatPersistentAuditEvent;
import com.example.domain.PersistentAuditEvent;
import com.example.domain.PersistentAuditEventData;
import com.example.domain.mapper.PersistenceAuditEventDataDomainMapper;
import com.example.domain.mapper.PersistenceAuditEventDomainMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Spring Data JPA mapper for the {@link PersistentAuditEvent} entity.
 */
@Repository
public class PersistenceAuditEventRepository {

    PersistenceAuditEventDomainMapper mapper;
    PersistenceAuditEventDataDomainMapper dataMapper;


    public PersistenceAuditEventRepository(
        PersistenceAuditEventDomainMapper mapper, PersistenceAuditEventDataDomainMapper dataMapper) {
        this.mapper = mapper;
        this.dataMapper = dataMapper;
    }

    public Optional<PersistentAuditEvent> findById(Long id) {

        List<FlatPersistentAuditEvent> flatPersistentAuditEventList = this.mapper.findById(id);
        return mapFlatListToPersistentAuditEvent(flatPersistentAuditEventList);

    }

    @SuppressWarnings("Duplicates")
    private Optional<PersistentAuditEvent> mapFlatListToPersistentAuditEvent(List<FlatPersistentAuditEvent> flatPersistentAuditEventList) {
        Map<String, String> eventDataMap = flatPersistentAuditEventList.stream().collect(
            Collectors.toMap(FlatPersistentAuditEvent::getAuthorityName, FlatPersistentAuditEvent::getAuthorityValue)
        );

        return  flatPersistentAuditEventList.stream().findFirst().map(
            d -> {
                PersistentAuditEvent p = new PersistentAuditEvent();
                p.setEventId(d.getId());
                p.setPrincipal(d.getPrincipal());
                p.setAuditEventType(d.getAuditEventType());
                p.setAuditEventDate(d.getAuditEventDate());
                p.setData(eventDataMap);
                return p;
            }
        );
    }

    @SuppressWarnings("Duplicates")
    private List<PersistentAuditEvent> mapFlatListToPersistentAuditEventList(List<FlatPersistentAuditEvent> flatPersistentAuditEventList) {
        Map<String, String> eventDataMap = flatPersistentAuditEventList.stream().collect(
            Collectors.toMap(FlatPersistentAuditEvent::getAuthorityName, FlatPersistentAuditEvent::getAuthorityValue)
        );
        return  flatPersistentAuditEventList.stream().map(
            d -> {
                PersistentAuditEvent p = new PersistentAuditEvent();
                p.setEventId(d.getId());
                p.setPrincipal(d.getPrincipal());
                p.setAuditEventType(d.getAuditEventType());
                p.setAuditEventDate(d.getAuditEventDate());
                p.setData(eventDataMap);
                return p;
            }
        ).collect(Collectors.toList());
    }

    public List<PersistentAuditEvent> findAll() {
        return this.mapFlatListToPersistentAuditEventList(
            this.mapper.findAll()
        );
    }

    public PersistentAuditEvent save(PersistentAuditEvent persistentAuditEvent) {
        this.mapper.save(persistentAuditEvent);
        persistentAuditEvent.getData().forEach(
            (k, v) -> {
                PersistentAuditEventData data = new PersistentAuditEventData();
                data.setId(persistentAuditEvent.getEventId());
                data.setName(k);
                data.setValue(v);
                this.dataMapper.save(data);
            }
        );
        return persistentAuditEvent;
    }

    public void deleteAll() {
        this.mapper.deleteAll();
    }

    public List<PersistentAuditEvent> findByPrincipal(String principal) {
        return this.mapFlatListToPersistentAuditEventList(
            this.mapper.findByPrincipal(principal)
        );
    }

    public List<PersistentAuditEvent> findByAuditEventDateAfter(Instant after) {
        return this.mapFlatListToPersistentAuditEventList(
            this.mapper.findByAuditEventDateAfter(after)
        );
    }

    public List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfter(String principal, Instant after) {
        return this.mapFlatListToPersistentAuditEventList(
            this.mapper.findByPrincipalAndAuditEventDateAfter(principal, after)
        );
    }

    public List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfterAndAuditEventType(String principal, Instant after, String type) {
        return this.findByPrincipalAndAuditEventDateAfterAndAuditEventType(principal, after, type);
    }

    public Page<PersistentAuditEvent> findAllByAuditEventDateBetween(Instant fromDate, Instant toDate, Pageable pageable) {
        return new PageImpl<>(
            this.mapper.findAllByAuditEventDateBetween(fromDate, toDate, pageable),
            pageable,
            this.mapper.countAllByAuditEventDateBetween(fromDate, toDate));

    }

    public Page<PersistentAuditEvent> findAll(Pageable pageable) {

        return new PageImpl<>(
            this.mapFlatListToPersistentAuditEventList(
                this.mapper.findAll(pageable)),
            pageable,
            this.mapper.countAll());
    }

}
