package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.TeamRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.request_object.RenameTeamRequest;
import com.thoughtworks.capability.gtb.restfulapidesign.vo.StudentVo;
import com.thoughtworks.capability.gtb.restfulapidesign.vo.TeamVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamVo> shuffleStudents() {
        List<TeamVo> teamVoList = new ArrayList<>();
        List<Team> teamList = teamRepository.shuffleStudents();

        teamList.forEach(team -> {
            List<StudentVo> studentVoList = convertStudentList2StudentVoList(team.getStudents());
            teamVoList.add(TeamVo.builder().name(team.getName()).note(team.getNote()).students(studentVoList).build());
        });
        return teamVoList;
    }

    private List<StudentVo> convertStudentList2StudentVoList(List<Student> students) {
        List<StudentVo> studentVoList = new ArrayList<>();
        students.forEach(student -> studentVoList.add(StudentVo.builder().name(student.getName()).gender(student.getGender()).note(student.getNote()).build()));
        return studentVoList;
    }

    public TeamVo updateTeamName(RenameTeamRequest renameTeamRequest) {
        Team team = teamRepository.updateName(renameTeamRequest);
        List<StudentVo> studentVoList = convertStudentList2StudentVoList(team.getStudents());
        return TeamVo.builder().name(team.getName()).note(team.getNote()).students(studentVoList).build();
    }
}
