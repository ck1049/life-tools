package com.loafer.mapper;

import com.loafer.entity.SimpleUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author loafer
 * @since 2025-03-09 21:58:49
 **/
@ConditionalOnMissingBean(name = "userMapper")
@Repository
public interface UserMapper extends JpaRepository<SimpleUser, Long> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    SimpleUser findByUsername(@Param("username") String username);
}
