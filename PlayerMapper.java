package com.test.mapper;

import com.test.pojo.Player;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayerMapper {
    List<Player> selectall();

    double selectmavg(String id);

    double selectoavg(String id);

    List<Player> selectpsf(String department);

    double selectbzf(@Param("tmpid") String tmpid, @Param("tmpname") String tmpname);
    double selectgsf(@Param("tmpid") String tmpid, @Param("tmpname") String tmpname);

    void updatebzf(@Param("tmpid") String id,@Param("tmpfs") double fs,@Param("tmpname") String name);
    void updategcf(@Param("tmpid") String id,@Param("tmpfs") double fs,@Param("tmpname") String name);
    void updatepsf(@Param("tmpid") String id,@Param("tmpfs") double fs);
}
