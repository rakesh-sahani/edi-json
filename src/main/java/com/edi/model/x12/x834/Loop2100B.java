package com.edi.model.x12.x834;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonPropertyOrder({"NM1_IncorrectMemberName", "DMG_IncorrectMemberDemographics"})
public class Loop2100B implements Serializable {
    @JsonProperty("NM1_IncorrectMemberName")
    private NM1_IncorrectMemberName nm1IncorrectMemberName;
    @JsonProperty("DMG_IncorrectMemberDemographics")
    private DMG_IncorrectMemberDemographics dmgIncorrectMemberDemographics;
}