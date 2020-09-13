package com.thoughtworks.capability.gtb.restfulapidesign.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
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
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
    private final String ADD_ONE_STUDENT_URL = "/students";
    private final String DELETE_ONE_STUDENT_URL = "/students/%s";
    private final String GET_ALL_STUDENT_URL = "/students";
    private final String GET_STUDENTS_BY_GENDER_URL = "/students?gender=%s";
    private final String GET_STUDENT_BY_ID_URL = "/students/%s";
    private final String UPDATE_STUDENT_BY_ID_URL = "/students/%s";

    @Autowired
    MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private StudentVo sampleStudentVoZhangSan = StudentVo.builder().name("张三").gender(Gender.Male).note("班长").build();
    private StudentVo sampleStudentVoLiSi = StudentVo.builder().name("李四").gender(Gender.Female).note("学习委员").build();

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
        addOneSampleStudent(sampleStudentVoZhangSan)
                .andExpect(jsonPath("$.id", is("0")))
                .andExpect(jsonPath("$.name", is("张三")))
                .andExpect(jsonPath("$.gender", is("Male")))
                .andExpect(jsonPath("$.note", is("班长")));
        assertEquals(1, StudentRepository.studentList.size());
    }

    private ResultActions addOneSampleStudent(StudentVo studentVo) throws Exception {
        return mockMvc.perform(post(ADD_ONE_STUDENT_URL).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentVo)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteOneStudent() throws Exception {
        addOneSampleStudent(sampleStudentVoZhangSan);
        assertEquals(1, StudentRepository.studentList.size());

        mockMvc.perform(delete(String.format(DELETE_ONE_STUDENT_URL, "0")))
                .andExpect(status().isOk());

        assertEquals(0, StudentRepository.studentList.size());
    }

    @Test
    public void shouldGetAllStudents() throws Exception {
        addOneSampleStudent(sampleStudentVoZhangSan);
        assertEquals(1, StudentRepository.studentList.size());

        addOneSampleStudent(sampleStudentVoLiSi);
        assertEquals(2, StudentRepository.studentList.size());

        mockMvc.perform(get(GET_ALL_STUDENT_URL).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("0")))
                .andExpect(jsonPath("$[0].name", is("张三")))
                .andExpect(jsonPath("$[0].gender", is("Male")))
                .andExpect(jsonPath("$[0].note", is("班长")))
                .andExpect(jsonPath("$[1].id", is("1")))
                .andExpect(jsonPath("$[1].name", is("李四")))
                .andExpect(jsonPath("$[1].gender", is("Female")))
                .andExpect(jsonPath("$[1].note", is("学习委员")));
    }

    @Test
    public void shouldGetAllMaleStudents() throws Exception {
        addOneSampleStudent(sampleStudentVoZhangSan);
        assertEquals(1, StudentRepository.studentList.size());

        addOneSampleStudent(sampleStudentVoLiSi);
        assertEquals(2, StudentRepository.studentList.size());

        mockMvc.perform(get(String.format(GET_STUDENTS_BY_GENDER_URL, "Male")).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("0")))
                .andExpect(jsonPath("$[0].name", is("张三")))
                .andExpect(jsonPath("$[0].gender", is("Male")))
                .andExpect(jsonPath("$[0].note", is("班长")));
    }

    @Test
    public void shouldGetAllFemaleStudents() throws Exception {
        addOneSampleStudent(sampleStudentVoZhangSan);
        assertEquals(1, StudentRepository.studentList.size());

        addOneSampleStudent(sampleStudentVoLiSi);
        assertEquals(2, StudentRepository.studentList.size());

        mockMvc.perform(get(String.format(GET_STUDENTS_BY_GENDER_URL, "Female")).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].name", is("李四")))
                .andExpect(jsonPath("$[0].gender", is("Female")))
                .andExpect(jsonPath("$[0].note", is("学习委员")));
    }

    @Test
    public void shouldGetStudentById() throws Exception {
        addOneSampleStudent(sampleStudentVoZhangSan);
        assertEquals(1, StudentRepository.studentList.size());

        addOneSampleStudent(sampleStudentVoLiSi);
        assertEquals(2, StudentRepository.studentList.size());

        mockMvc.perform(get(String.format(GET_STUDENT_BY_ID_URL, "1")).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.name", is("李四")))
                .andExpect(jsonPath("$.gender", is("Female")))
                .andExpect(jsonPath("$.note", is("学习委员")));
    }

    @Test
    public void shouldReturnNotFoundWhenGetStudentIdInvalid() throws Exception {
        addOneSampleStudent(sampleStudentVoZhangSan);
        assertEquals(1, StudentRepository.studentList.size());

        addOneSampleStudent(sampleStudentVoLiSi);
        assertEquals(2, StudentRepository.studentList.size());

        mockMvc.perform(get(String.format(GET_STUDENT_BY_ID_URL, "100")).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is("student index invalid")));
    }

    @Test
    public void shouldUpdateStudentById() throws Exception {
        addOneSampleStudent(sampleStudentVoZhangSan);
        assertEquals(1, StudentRepository.studentList.size());

        StudentVo studentVo = StudentVo.builder().name("改名后的张三").gender(Gender.Female).note("体育委员").build();
        mockMvc.perform(put(String.format(UPDATE_STUDENT_BY_ID_URL, "0")).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentVo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("0")))
                .andExpect(jsonPath("$.name", is("改名后的张三")))
                .andExpect(jsonPath("$.gender", is("Female")))
                .andExpect(jsonPath("$.note", is("体育委员")));
    }

    @Test
    public void shouldReturnNotFoundWhenUpdateStudentIdInvalid() throws Exception {
        addOneSampleStudent(sampleStudentVoZhangSan);
        assertEquals(1, StudentRepository.studentList.size());

        StudentVo studentVo = StudentVo.builder().name("改名后的张三").gender(Gender.Female).note("体育委员").build();
        mockMvc.perform(put(String.format(UPDATE_STUDENT_BY_ID_URL, "100")).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentVo)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is("student index invalid")));
    }
}