package com.practice.practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "courseServletBody", urlPatterns = "/course/body")
public class CourseServletBody extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //printHttpInfo(req);

        // HTTP Body 정보 데이터 확인
        ServletInputStream inputStream = req.getInputStream();  //HTTP Body에서 가져온 데이터를 바이트코드로 변환
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);  //바이트코드를 String으로 변환
        System.out.println("body = " + body);



        //JSON To Object
        Course course = new ObjectMapper().readValue(body, Course.class);
        System.out.println("course.getTitle() = " + course.getTitle());
        System.out.println("course.getInstructor() = " + course.getInstructor());
        System.out.println("course.getCost() = " + course.getCost());

        // 가격 수정
        course.setCost(999999);

        // 요청에 대한 결과 반환
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.setStatus(HttpStatus.CREATED.value());

        // Object To JSON
        resp.getWriter().write(new ObjectMapper().writeValueAsString(course));
    }

    private static void printHttpInfo(HttpServletRequest req) {
        System.out.println("req.getRequestURI() = " + req.getRequestURI());
        System.out.println("req.getRequestURL() = " + req.getRequestURL());
        System.out.println("req.getServerPort() = " + req.getServerPort());
        System.out.println("req.getServletPath() = " + req.getServletPath());
        System.out.println("req.getMethod() = " + req.getMethod());
    }
}
