package com.loafer.service.impl;

import com.loafer.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author loafer
 * @since 2025-03-09 21:12:24
 **/
@ConditionalOnProperty(prefix = "security.authentication", name = "custom", havingValue = "disable", matchIfMissing = true)

@Service
public class DefaultUserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 为了通用性这里最好是使用原生JDBC进行数据库操作，先暂时使用JPA进行查询
        return userMapper.findByUsername(username);
    }
}
