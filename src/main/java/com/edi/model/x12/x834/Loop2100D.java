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
@JsonPropertyOrder({"NM1_MemberEmployer", "PER_MemberEmployerCommunicationsNumbers", "N3_MemberEmployerStreetAddress", "N4_MemberEmployerCity_State_ZIPCode"})
public class Loop2100D implements Serializable {
    @JsonProperty("NM1_MemberEmployer")
    private NM1_MemberEmployer nm1MemberEmployer;
    @JsonProperty("PER_MemberEmployerCommunicationsNumbers")
    private PER_MemberEmployerCommunicationsNumbers perMemberEmployerCommunicationsNumbers;
    @JsonProperty("N3_MemberEmployerStreetAddress")
    private N3_MemberEmployerStreetAddress n3MemberEmployerStreetAddress;
    @JsonProperty("N4_MemberEmployerCity_State_ZIPCode")
    private N4_MemberEmployerCity_State_ZIPCode n4MemberEmployerCityStateZipCode;
}