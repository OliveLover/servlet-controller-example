package com.practice.practice.front.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.practice.Course;
import com.practice.practice.front.ControllerInterface;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CourseServletBodyController implements ControllerInterface {
    @Override
    public Course service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        course.setCost(1000);

        return course;
    }
}
