package com.example.repository;

import com.example.JhreactApp;
import com.example.domain.PersistentAuditEvent;
import com.example.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(classes = JhreactApp.class)
class PersistentAuditEventRepositoryTest {

    @Autowired
    PersistenceAuditEventRepository repo;

    @Test
    public void findById() {

        Optional<PersistentAuditEvent> eveOpt = repo.findById(1L);
        PersistentAuditEvent eve = eveOpt.get();

        assertThat(eve.getEventId(), is(1l));
        assertThat(eve.getData().get("key1"), is("value1"));
        assertThat(eve.getData().get("key1_1"), is("value1_1"));

        eveOpt = repo.findById(10L);

        assertThat(eveOpt.isPresent(), is(false));

    }

    @Test
    public void save() {

        PersistentAuditEvent event = new PersistentAuditEvent();
//        event.setEventId(10L);
        event.setPrincipal("principal");
        event.setAuditEventType("type");
        Map<String, String> data = new HashMap<>();
        data.put("nameTest1", "valueTest1");
        data.put("nameTest2", "valueTest2");
        event.setData(data);

        this.repo.save(event);
    }

    @Test
    public void findAllByAuditEventDateBetween() {

        LocalDateTime ldtFrom = LocalDateTime.of(2019, 12, 31, 23, 59, 59);
        ZonedDateTime zdtFrom = ldtFrom.atZone(ZoneId.systemDefault());
        LocalDateTime ldtTo = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        ZonedDateTime zdtTo = ldtTo.atZone(ZoneId.systemDefault());

        Pageable pageable = PageRequest.of(0, 2);

        Page<PersistentAuditEvent> eventDataList = repo.findAllByAuditEventDateBetween(zdtFrom.toInstant(), zdtTo.toInstant(), pageable);

        List<PersistentAuditEvent> content = eventDataList.getContent();

        System.out.println("----------------------------");
        System.out.println(content);

    }

}
