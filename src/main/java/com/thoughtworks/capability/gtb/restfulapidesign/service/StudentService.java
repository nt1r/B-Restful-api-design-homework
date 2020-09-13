package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.vo.StudentVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentService {
    private final StudentRepository studentRepository = new StudentRepository();
    public static final AtomicInteger autoIncreaseId = new AtomicInteger(0);

    public Student addOneStudent(StudentVo studentVo) {
        Student student = convertStudentVo2Student(studentVo, autoIncreaseId.getAndAdd(1));
        return studentRepository.save(student);
    }

    public Student convertStudentVo2Student(StudentVo studentVo, Integer id) {
        return Student.builder()
                .id(String.valueOf(id))
                .name(studentVo.getName())
                .gender(studentVo.getGender())
                .note(studentVo.getNote())
                .build();
    }

    public void deleteOneStudent(String id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsList(Gender gender) {
        if (gender == null) {
            return studentRepository.findAll();
        } else {
            return studentRepository.findByGender(gender);
        }
    }

    public Student getOneStudent(String id) {
        return studentRepository.findById(id);
    }
}
