package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    public static List<Student> studentList = new ArrayList<>();

    public Student save(Student student) {
        studentList.add(student);
        return student;
    }

    public void deleteById(String id) {
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                studentList.remove(student);
                break;
            }
        }
    }
}
