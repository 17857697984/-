package com.test.mapper;

import com.test.pojo.Minister;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper {
    Minister select(@Param("username") String username, @Param("password") String password);
}
