package com.example.domain.mapper;

import com.example.domain.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 権限マスタのMapperインターフェース.
 */
@Mapper
public interface AuthorityDomainMapper {

    Optional<Authority> findById(@Param("name") String name);

    List<Authority> findAll();

    void save(@Param("authority") Authority authority);

    void delete(@Param("authority") Authority authority);
}
