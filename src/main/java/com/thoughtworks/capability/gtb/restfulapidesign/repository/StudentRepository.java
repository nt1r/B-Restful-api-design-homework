package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    List<Student> studentList = new ArrayList<>();

    public Student save(Student student) {
        studentList.add(student);
        return student;
    }
}
