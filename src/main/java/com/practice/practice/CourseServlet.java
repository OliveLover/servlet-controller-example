package com.practice.practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "courseServlet", urlPatterns = "/course")
public class CourseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printHttpInfo(req);

        // 데이터 확인
        String title = req.getParameter("title");
        String instructor = req.getParameter("instructor");
        double cost = Double.parseDouble(req.getParameter("cost"));

        // 요청에 대한 결과 반환
        //텍스트 형태로 보내기
//        resp.setContentType("text/plan");
        //제이슨 형태로 보내기
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        // Object To JSON
        Course course = new Course(title, instructor, cost);
        resp.getWriter().write(new ObjectMapper().writeValueAsString(course));
        //resp.getWriter().write("반환 성공");

    }

    private static void printHttpInfo(HttpServletRequest req) {
        System.out.println("req.getRequestURI() = " + req.getRequestURI());
        System.out.println("req.getRequestURL() = " + req.getRequestURL());
        System.out.println("req.getServerPort() = " + req.getServerPort());
        System.out.println("req.getServletPath() = " + req.getServletPath());
        System.out.println("req.getMethod() = " + req.getMethod());
    }
}
