package com.practice.practice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController    //servlet과 연결
@RequestMapping("/controller/course")
public class CourseController {

    @PostMapping("/body")
    public ResponseEntity<?> courseBody(@RequestBody Course course) {
        System.out.println("course.toString() = " + course.toString());
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<?> modelAttribute(@ModelAttribute Course course) {
        System.out.println("course.toString() = " + course.toString());
        return ResponseEntity.ok(course);
    }
}
