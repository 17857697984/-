package com.test.Servlet;

import com.alibaba.fastjson.JSONObject;
import com.test.mapper.LoginMapper1;
import com.test.mapper.PlayerMapper;
import com.test.pojo.Officer;
import com.test.pojo.Player;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/lookServlet")
public class LookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlsession1 = sqlSessionFactory.openSession(true);
        PlayerMapper playerMapper = sqlsession1.getMapper(PlayerMapper.class);
        List<Player> players = playerMapper.selectall();
        players = playerMapper.selectall();
        for(int i = 1;i<=19;i++)
        {
            String tmpid = "xs" + i;
            SqlSession sqlsession = sqlSessionFactory.openSession(true);
            PlayerMapper playerMapper0 = sqlsession.getMapper(PlayerMapper.class);
            double tmpmavg = playerMapper0.selectmavg(tmpid);
            sqlsession.close();
            players.get(i-1).setMavg_(tmpmavg);
            SqlSession sqlsession2 = sqlSessionFactory.openSession(true);
            PlayerMapper playerMapper2 = sqlsession2.getMapper(PlayerMapper.class);
            double tmpoavg = playerMapper2.selectoavg(tmpid);
            sqlsession2.close();
            players.get(i-1).setOavg_(tmpoavg);
            double tmppsf = players.get(i-1).getPsf();
            double tmptotal = tmpmavg*0.4+tmpoavg*0.2+tmppsf*0.4;
            players.get(i-1).setTotal_(tmptotal);
        }

        String jsonString = JSONObject.toJSONString(players);
        sqlsession1.close();

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
