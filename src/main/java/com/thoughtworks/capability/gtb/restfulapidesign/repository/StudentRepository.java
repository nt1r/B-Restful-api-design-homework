package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Student> findAll() {
        return studentList;
    }

    public List<Student> findByGender(Gender gender) {
        return studentList.stream().filter(student -> student.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Student findById(String id) {
        List<Student> targetStudentList = studentList.stream().filter(student -> student.getId().equals(id)).collect(Collectors.toList());
        if (targetStudentList.isEmpty()) {
            throw new IllegalArgumentException("student index invalid");
        }
        return targetStudentList.get(0);
    }
}
