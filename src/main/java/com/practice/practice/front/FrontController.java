package com.practice.practice.front;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.practice.Course;
import com.practice.practice.front.controller.CourseServletBodyController;
import com.practice.practice.front.controller.CourseServletController;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontController", urlPatterns = "/front/*")
public class FrontController extends HttpServlet {

    private final Map<String, ControllerInterface> controllerStore = new HashMap<>();

    public FrontController() {
        controllerStore.put("/front/course", new CourseServletController());
        controllerStore.put("/front/course/body", new CourseServletBodyController());
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printHttpInfo(req);

        //요청을 구분해서 해당하는 Controller를 호출해서 사용
        ControllerInterface controller = controllerStore.get(req.getRequestURI());
        Course course = controller.service(req, resp);


        // 요청에 대한 결과 반환
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.setStatus(HttpStatus.OK.value());

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
