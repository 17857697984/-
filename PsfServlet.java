package com.test.Servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.mapper.PlayerMapper;
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
import java.util.List;

@WebServlet("/psfServlet")
public class PsfServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tmpname = "";
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            String key = cookie.getName();
            if("department".equals(key)){
                tmpname = cookie.getValue();
            }
        }

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlsession1 = sqlSessionFactory.openSession(true);
        PlayerMapper playerMapper = sqlsession1.getMapper(PlayerMapper.class);
        List<Player> players = playerMapper.selectpsf(tmpname);
        String jsonString = JSONObject.toJSONString(players);
        sqlsession1.close();

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String tmpjosn = br.readLine();
        br.close();

        Tmp tmp = JSON.parseObject(tmpjosn, Tmp.class);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlsession = sqlSessionFactory.openSession(true);
        PlayerMapper playerMapper = sqlsession.getMapper(PlayerMapper.class);
        playerMapper.updatepsf(tmp.getId(),tmp.getFs());
        sqlsession.commit();
        sqlsession.close();

        doGet(request,response);
    }
}
