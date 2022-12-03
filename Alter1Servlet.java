package com.test.Servlet;

import com.alibaba.fastjson.JSON;
import com.test.mapper.Gs;
import com.test.mapper.LoginMapper;
import com.test.mapper.LoginMapper1;
import com.test.pojo.Minister;
import com.test.pojo.Officer;
import com.test.pojo.User;
import com.test.pojo.gmm;
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

@WebServlet("/alter1Servlet")
public class Alter1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String tmpjosn = br.readLine();
        br.close();
        gmm g = JSON.parseObject(tmpjosn,gmm.class);
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlsession = sqlSessionFactory.openSession(true);

        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            String key = cookie.getName();
            if("username".equals(key)){
                g.name = cookie.getValue();
            }
        }


        SqlSession sqlsession2 = sqlSessionFactory.openSession(true);
        LoginMapper1 loginMapper2 = sqlsession2.getMapper(LoginMapper1.class);
        Officer officer = loginMapper2.select(g.name,g.opwd);
        sqlsession2.close();

        if(officer==null)
        {
            response.getWriter().write("mmcw");
        }
        else{
            Gs gs = sqlsession.getMapper(Gs.class);
            gs.update1(g.name,g.npwd);
            sqlsession.commit();
            sqlsession.close();
            response.getWriter().write("success");
        }
        sqlsession.close();



    }
}
