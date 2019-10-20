package com.example.repository;

import com.example.domain.User;
import com.example.domain.mapper.UserDomainMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * ユーザマスタのDaoクラス.
 */
@Repository
public class UserRepository {

    UserDomainMapper mapper;

    public UserRepository(UserDomainMapper mapper) {
        this.mapper = mapper;
    }

    public Optional<User> findById(@Param("id") Long id) {
        return this.mapper.findById(id);
    }

    public List<User> findAll() {
        return this.mapper.findAll();
    }

    public User save(@Param("user") User user) {
        this.mapper.save(user);
        return user;
    }

    public void delete(@Param("user") User user) {
        this.mapper.delete(user);
    }

    public Optional<User> findOneByActivationKey(@Param("activationKey") String activationKey) {
        return this.mapper.findOneByActivationKey(activationKey);
    }

    public List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(
        @Param("createdDate") Instant dateTime) {
        return this.mapper.findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(dateTime);
    }

    public Optional<User> findOneByResetKey(@Param("resetKey") String resetKey) {
        return this.mapper.findOneByResetKey(resetKey);
    }

    public Optional<User> findOneByEmailIgnoreCase(@Param("email") String email) {
        return this.mapper.findOneByEmailIgnoreCase(email);
    }

    public Optional<User> findOneByLogin(@Param("login") String login) {
        return  this.mapper.findOneByLogin(login);
    }

//    List<User> findAuthorities(
//        @Param("id")String id, @Param("login")String login ,@Param("email")String email);

    public Optional<User> findOneWithAuthoritiesById(@Param("id") Long id) {
        return this.mapper.findOneWithAuthoritiesById(id);
    }

    public Optional<User> findOneWithAuthoritiesByLogin(@Param("login") String login) {
        return this.mapper.findOneWithAuthoritiesByLogin(login);
    }

    public Optional<User> findOneWithAuthoritiesByEmail(@Param("email") String email) {
        return this.mapper.findOneWithAuthoritiesByEmail(email);
    }

    public Page<User> findAllByLoginNot(Pageable pageable, String login) {

        return new PageImpl<User>(
            this.mapper.findAllByLoginNot(pageable, login),
            pageable,
            this.mapper.countAllByLoginNot(login));
    }
}
