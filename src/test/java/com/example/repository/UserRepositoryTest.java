package com.example.repository;

import com.example.JhreactApp;
import com.example.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.support.NullValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(classes = JhreactApp.class)
class UserRepositoryTest {

    @Autowired
    UserRepository repo;

    @Test
    public void test_findOneByActivationKey() {

        Optional<User> userOpt = repo.findOneByActivationKey("activationkey");
        User user = userOpt.get();

        assertThat(user.getId(), is(5l));
        assertThat(user.getLogin(), is("user1"));
        assertThat(user.getFirstName(), is("User"));
        assertThat(user.getLastName(), is("User"));
        assertThat(user.getEmail(), is("user1@localhost"));
        assertThat(user.getImageUrl() , is( ""));
        assertThat(user.getActivated(), is(false));
    }


    @Test
    public void findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore() {


        LocalDateTime ldt = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        List<User> userList = repo.findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(
            zdt.toInstant());

        assertThat(userList.size(), is(1));

    }

    @Test
    public void test_findOneByResetKey() {

        Optional<User> userOpt = repo.findOneByResetKey("reset");
        User user = userOpt.get();

        assertThat(user.getId(), is(5l));
        assertThat(user.getLogin(), is("user1"));
        assertThat(user.getFirstName(), is("User"));
        assertThat(user.getLastName(), is("User"));
        assertThat(user.getEmail(), is("user1@localhost"));
        assertThat(user.getImageUrl() , is( ""));
        assertThat(user.getActivated(), is(false));
    }

    @Test
    public void test_findOneByEmailIgnoreCase() {

        Optional<User> userOpt = repo.findOneByEmailIgnoreCase("user1@localhost");
        User user = userOpt.get();

        assertThat(user.getId(), is(5l));
        assertThat(user.getLogin(), is("user1"));
        assertThat(user.getFirstName(), is("User"));
        assertThat(user.getLastName(), is("User"));
        assertThat(user.getEmail(), is("user1@localhost"));
        assertThat(user.getImageUrl() , is( ""));
        assertThat(user.getActivated(), is(false));
    }

    @Test
    public void test_findOneByLogin() {

        Optional<User> userOpt = repo.findOneByLogin("user1");
        User user = userOpt.get();

        assertThat(user.getId(), is(5l));
        assertThat(user.getLogin(), is("user1"));
        assertThat(user.getFirstName(), is("User"));
        assertThat(user.getLastName(), is("User"));
        assertThat(user.getEmail(), is("user1@localhost"));
        assertThat(user.getImageUrl() , is( ""));
        assertThat(user.getActivated(), is(false));
    }

    @Test
    public void findOneWithAuthoritiesById() {

        Optional<User> userOpt = repo.findOneWithAuthoritiesById(5l);
        User user = userOpt.get();

        assertThat(user.getId(), is(5l));
        assertThat(user.getLogin(), is("user1"));
        assertThat(user.getFirstName(), is("User"));
        assertThat(user.getLastName(), is("User"));
        assertThat(user.getEmail(), is("user1@localhost"));
        assertThat(user.getImageUrl() , is( ""));
        assertThat(user.getActivated(), is(false));
    }

    @Test
    public void findOneWithAuthoritiesByLogin() {

        Optional<User> userOpt = repo.findOneWithAuthoritiesByLogin("user1");
        User user = userOpt.get();

        assertThat(user.getId(), is(5l));
        assertThat(user.getLogin(), is("user1"));
        assertThat(user.getFirstName(), is("User"));
        assertThat(user.getLastName(), is("User"));
        assertThat(user.getEmail(), is("user1@localhost"));
        assertThat(user.getImageUrl() , is( ""));
        assertThat(user.getActivated(), is(false));
    }

    @Test
    public void findOneWithAuthoritiesByEmail() {

        Optional<User> userOpt = repo.findOneWithAuthoritiesByEmail("user1@localhost");
        User user = userOpt.get();

        assertThat(user.getId(), is(5l));
        assertThat(user.getLogin(), is("user1"));
        assertThat(user.getFirstName(), is("User"));
        assertThat(user.getLastName(), is("User"));
        assertThat(user.getEmail(), is("user1@localhost"));
        assertThat(user.getImageUrl() , is( ""));
        assertThat(user.getActivated(), is(false));
    }

    @Test
    public void findAllByLoginNot() {

        Pageable pageable = PageRequest.of(1, 2);

        Page<User> userPage = repo.findAllByLoginNot(pageable, "anonymous");
        List<User> content = userPage.getContent();

        assertThat(content.get(0).getId(), is(3l));

    }
}
