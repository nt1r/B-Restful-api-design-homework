package com.thoughtworks.capability.gtb.restfulapidesign.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamVo {
    String name;
    String note;
    List<StudentVo> students;
}
