package com.thoughtworks.capability.gtb.restfulapidesign.api;

import com.thoughtworks.capability.gtb.restfulapidesign.request_object.RenameTeamRequest;
import com.thoughtworks.capability.gtb.restfulapidesign.service.TeamService;
import com.thoughtworks.capability.gtb.restfulapidesign.vo.TeamVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/assignment")
    public List<TeamVo> shuffleStudents() {
        return teamService.shuffleStudents();
    }

    @PatchMapping("/name")
    public TeamVo updateTeamName(@RequestBody RenameTeamRequest renameTeamRequest) {
        return teamService.updateTeamName(renameTeamRequest);
    }
}
