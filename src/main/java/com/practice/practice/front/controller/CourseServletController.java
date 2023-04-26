package com.practice.practice.front.controller;

import com.practice.practice.Course;
import com.practice.practice.front.ControllerInterface;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CourseServletController implements ControllerInterface {
    @Override
    public Course service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String instructor = req.getParameter("instructor");
        double cost = Double.parseDouble(req.getParameter("cost"));

        return new Course(title, instructor, cost);
    }
}
