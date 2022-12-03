package com.test.Servlet;

import com.test.mapper.LoginMapper;
import com.test.mapper.LoginMapper1;
import com.test.pojo.Minister;
import com.test.pojo.Officer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    public LoginServlet() throws IOException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int roleid = Integer.valueOf(request.getParameter("role"));
        if(roleid==0)//部长
        {
            SqlSession sqlsession = sqlSessionFactory.openSession(true);
            LoginMapper loginMapper = sqlsession.getMapper(LoginMapper.class);
            Minister minister = loginMapper.select(username,password);
            sqlsession.close();
            if(minister!=null)
            {
                Cookie cookie = new Cookie("username",username);
                response.addCookie(cookie);
                cookie = new Cookie("department",minister.getDepartment());
                response.addCookie(cookie);
                response.sendRedirect("home.html");
            }
            else
            {
                response.sendRedirect("register.html");
            }

        }else if (roleid ==1)//干事
        {
            SqlSession sqlsession = sqlSessionFactory.openSession(true);
            LoginMapper1 loginMapper = sqlsession.getMapper(LoginMapper1.class);
            Officer officer = loginMapper.select(username,password);
            sqlsession.close();
            if(officer!=null)
            {
                Cookie cookie = new Cookie("username",username);

                response.addCookie(cookie);
                cookie = new Cookie("department",officer.getDepartment_());
                response.addCookie(cookie);
                response.sendRedirect("a.html");
            }
            else
            {
                response.sendRedirect("register.html");
            }
        }
    }

}
