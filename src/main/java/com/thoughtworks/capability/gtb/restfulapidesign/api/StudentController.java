package com.thoughtworks.capability.gtb.restfulapidesign.api;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.vo.StudentVo;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("")
    public Student addOneStudent(@RequestBody StudentVo studentVo) {
        return studentService.addOneStudent(studentVo);
    }

    @DeleteMapping("/{id}")
    public void deleteOneStudent(@PathVariable String id) {
        studentService.deleteOneStudent(id);
    }
}
