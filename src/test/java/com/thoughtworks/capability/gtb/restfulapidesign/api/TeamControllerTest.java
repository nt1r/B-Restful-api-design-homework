package com.thoughtworks.capability.gtb.restfulapidesign.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.TeamRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.request_object.RenameTeamRequest;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TeamControllerTest {
    private final String ASSIGN_STUDENTS_URL = "/teams/assignment";
    private final String RENAME_TEAM_URL = "/teams/name";

    private final String DEFAULT_NOTE = "default note";
    private final Student studentZhangSan = Student.builder().name("张三").gender(Gender.Male).note(DEFAULT_NOTE).build();
    private final Student studentLiSi = Student.builder().name("李四").gender(Gender.Female).note(DEFAULT_NOTE).build();
    private final Student studentWangWu = Student.builder().name("王五").gender(Gender.Male).note(DEFAULT_NOTE).build();

    @Autowired
    MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        StudentRepository.studentList.clear();
        StudentService.autoIncreaseId.set(0);
        initStudentList();

        TeamRepository.teamList.clear();
        TeamRepository.initTeamList();
    }

    private void initStudentList() {
        StudentRepository.studentList.add(studentZhangSan);
        StudentRepository.studentList.add(studentLiSi);
        StudentRepository.studentList.add(studentWangWu);
        StudentRepository.studentList.add(studentZhangSan);
        StudentRepository.studentList.add(studentLiSi);
        StudentRepository.studentList.add(studentWangWu);
        StudentRepository.studentList.add(studentZhangSan);
        StudentRepository.studentList.add(studentLiSi);
        StudentRepository.studentList.add(studentWangWu);
        StudentRepository.studentList.add(studentZhangSan);
        StudentRepository.studentList.add(studentLiSi);
        StudentRepository.studentList.add(studentWangWu);
        StudentRepository.studentList.add(studentZhangSan);
        StudentRepository.studentList.add(studentLiSi);
        StudentRepository.studentList.add(studentWangWu);
        StudentRepository.studentList.add(studentZhangSan);
        StudentRepository.studentList.add(studentLiSi);
        StudentRepository.studentList.add(studentWangWu);
        StudentRepository.studentList.add(studentZhangSan);
        StudentRepository.studentList.add(studentLiSi);
        StudentRepository.studentList.add(studentWangWu);
        StudentRepository.studentList.add(studentZhangSan);
        StudentRepository.studentList.add(studentLiSi);
        StudentRepository.studentList.add(studentWangWu);
        StudentRepository.studentList.add(studentZhangSan);
        StudentRepository.studentList.add(studentLiSi);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void shouldShuffleStudents() throws Exception {
        mockMvc.perform(get(ASSIGN_STUDENTS_URL).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].students", hasSize(5)))
                .andExpect(jsonPath("$[1].students", hasSize(5)))
                .andExpect(jsonPath("$[2].students", hasSize(4)))
                .andExpect(jsonPath("$[3].students", hasSize(4)))
                .andExpect(jsonPath("$[4].students", hasSize(4)))
                .andExpect(jsonPath("$[5].students", hasSize(4)));
    }

    @Test
    public void shouldRenameTeam() throws Exception {
        mockMvc.perform(patch(RENAME_TEAM_URL).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(RenameTeamRequest.builder().id("0").name("new name").build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("new name")));
    }
}