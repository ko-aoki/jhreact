package com.example.domain.mapper;

import com.example.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * ユーザマスタのMapperインターフェース.
 */
@Mapper
public interface UserDomainMapper {

    Optional<User> findById(@Param("id") Long id);

    List<User> findAll();

    void create(User user);

    void update(User user);

    void delete(User user);

    Optional<User> findOneByActivationKey(@Param("activationKey") String activationKey);

    List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(
        @Param("createdDate") Instant dateTime);


    Optional<User> findOneByResetKey(@Param("resetKey") String resetKey);

    Optional<User> findOneByEmailIgnoreCase(@Param("email") String email);

    Optional<User> findOneByLogin(@Param("login") String login);

    Optional<User> findOneWithAuthoritiesById(@Param("id") Long id);

    Optional<User> findOneWithAuthoritiesByLogin(@Param("login") String login);

    Optional<User> findOneWithAuthoritiesByEmail(@Param("email") String email);

    int countAllByLoginNot(@Param("login")String login);

    List<User> findAllByLoginNot(@Param("pageable") Pageable pageable, @Param("login")String login);
}
