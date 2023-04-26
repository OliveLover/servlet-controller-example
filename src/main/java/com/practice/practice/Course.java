package com.practice.practice;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@AllArgsConstructor    //@Setter를 하기 싫을 경우 모든경우의 생성자를 만들어주면 데이터를 똑같이 바인딩 해준다.
public class Course {
    private String title;
    private String instructor;
    private  double cost;

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Course(String title, String instructor, double cost) {
        this.title = title;
        this.instructor = instructor;
        this.cost = cost;
    }
}
