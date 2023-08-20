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
@JsonPropertyOrder({"NM1_MemberSchool", "PER_MemberSchoolCommunicationsNumbers", "N3_MemberSchoolStreetAddress", "N4_MemberSchoolCity_State_ZIPCode"})
public class Loop2100E implements Serializable {
    @JsonProperty("NM1_MemberSchool")
    private NM1_MemberSchool nm1MemberSchool;
    @JsonProperty("PER_MemberSchoolCommunicationsNumbers")
    private PER_MemberSchoolCommunicationsNumbers perMemberSchoolCommunicationsNumbers;
    @JsonProperty("N3_MemberSchoolStreetAddress")
    private N3_MemberSchoolStreetAddress n3MemberSchoolStreetAddress;
    @JsonProperty("N4_MemberSchoolCity_State_ZIPCode")
    private N4_MemberSchoolCity_State_ZIPCode n4MemberSchoolCityStateZipCode;
}