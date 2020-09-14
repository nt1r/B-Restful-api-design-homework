package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class TeamRepository {
    public static List<Team> teamList = new ArrayList<>();
    private static final int TEAM_COUNT = 6;
    private static final String DEFAULT_NOTE = "default note";

    public TeamRepository() {
        initTeamList();
    }

    public static void initTeamList() {
        for (int index = 0; index < TEAM_COUNT; ++index) {
            teamList.add(Team.builder().id(String.valueOf(index)).name("Team " + index).note(DEFAULT_NOTE).students(new ArrayList<>()).build());
        }
    }

    public List<Team> shuffleStudents() {
        Collections.shuffle(StudentRepository.studentList);
        assignShuffledStudentsIntoTeamList();
        return teamList;
    }

    private void assignShuffledStudentsIntoTeamList() {
        for (int studentIndex = 0; studentIndex < StudentRepository.studentList.size(); ++studentIndex) {
            int teamIndex = studentIndex % TEAM_COUNT;
            teamList.get(teamIndex).getStudents().add(StudentRepository.studentList.get(studentIndex));
        }
    }
}
