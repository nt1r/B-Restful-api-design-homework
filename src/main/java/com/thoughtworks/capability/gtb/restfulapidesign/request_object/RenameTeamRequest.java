package com.thoughtworks.capability.gtb.restfulapidesign.request_object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RenameTeamRequest {
    String id;
    String name;
}
