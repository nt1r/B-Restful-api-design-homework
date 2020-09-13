package com.thoughtworks.capability.gtb.restfulapidesign.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.vo.StudentVo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
    private final String ADD_ONE_STUDENT_URL = "/students";
    private final String DELETE_ONE_STUDENT_URL = "/students/%s";

    @Autowired
    MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private StudentVo sampleStudentVo = StudentVo.builder().name("张三").gender(Gender.Male).note("班长").build();

    @BeforeEach
    void setUp() {
        StudentRepository.studentList.clear();
        StudentService.autoIncreaseId.set(0);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void shouldAddOneStudent() throws Exception {
        mockMvc.perform(post(ADD_ONE_STUDENT_URL).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleStudentVo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("0")))
                .andExpect(jsonPath("$.name", is("张三")))
                .andExpect(jsonPath("$.gender", is("Male")))
                .andExpect(jsonPath("$.note", is("班长")));

        assertEquals(1, StudentRepository.studentList.size());
    }

    @Test
    public void shouldDeleteOneStudent() throws Exception {
        mockMvc.perform(post(ADD_ONE_STUDENT_URL).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleStudentVo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("0")))
                .andExpect(jsonPath("$.name", is("张三")))
                .andExpect(jsonPath("$.gender", is("Male")))
                .andExpect(jsonPath("$.note", is("班长")));

        assertEquals(1, StudentRepository.studentList.size());

        mockMvc.perform(delete(String.format(DELETE_ONE_STUDENT_URL, "0")))
                .andExpect(status().isOk());

        assertEquals(0, StudentRepository.studentList.size());
    }
}