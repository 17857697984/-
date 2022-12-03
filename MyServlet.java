package com.test.Servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.mapper.LoginMapper;
import com.test.mapper.LoginMapper1;
import com.test.mapper.PlayerMapper;
import com.test.pojo.Minister;
import com.test.pojo.Officer;
import com.test.pojo.Player;
import com.test.pojo.Tmp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/myServlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tmpname = "";
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            String key = cookie.getName();
            if("username".equals(key)){
                tmpname = cookie.getValue();
            }
        }

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlsession1 = sqlSessionFactory.openSession(true);
        PlayerMapper playerMapper = sqlsession1.getMapper(PlayerMapper.class);
        List<Player> players = playerMapper.selectall();
        for(int i = 1;i<=19;i++)
        {
            String tmpid = "xs" + i;
            SqlSession sqlsession = sqlSessionFactory.openSession(true);
            PlayerMapper playerMapper0 = sqlsession.getMapper(PlayerMapper.class);
            double bzf = playerMapper0.selectgsf(tmpid,tmpname);
            sqlsession.close();
            players.get(i-1).setSorce(bzf);
            players.get(i-1).tmpname = tmpname;
        }
        String jsonString = JSONObject.toJSONString(players);
        sqlsession1.close();

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tmpname = "";
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            String key = cookie.getName();
            if("username".equals(key)){
                tmpname = cookie.getValue();
            }
        }
        BufferedReader br = request.getReader();
        String tmpjosn = br.readLine();
        br.close();

        Tmp tmp = JSON.parseObject(tmpjosn, Tmp.class);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlsession = sqlSessionFactory.openSession(true);
        PlayerMapper playerMapper = sqlsession.getMapper(PlayerMapper.class);
        playerMapper.updategcf(tmp.getId(),tmp.getFs(),tmpname);
        sqlsession.commit();
        sqlsession.close();

        doGet(request,response);
    }
}
