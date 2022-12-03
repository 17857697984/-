package com.test.mapper;

import com.test.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Gs {
    List<User> select(String department);
    void insert(@Param("name") String name, @Param("password") String password, @Param("department") String department);

    void update1(@Param("name") String name, @Param("password") String password);

}
