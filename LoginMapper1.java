package com.test.mapper;

import com.test.pojo.Minister;
import com.test.pojo.Officer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginMapper1 {
    Officer select(@Param("username") String username, @Param("password") String password);
    Officer selectme(@Param("username") String username);
}
