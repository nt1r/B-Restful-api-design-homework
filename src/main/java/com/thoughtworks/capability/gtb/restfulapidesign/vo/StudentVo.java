package com.thoughtworks.capability.gtb.restfulapidesign.vo;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentVo {
    String name;
    Gender gender;
    String note;
}
