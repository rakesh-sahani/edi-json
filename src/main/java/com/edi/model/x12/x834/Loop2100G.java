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
@JsonPropertyOrder({"NM1_ResponsiblePerson", "PER_ResponsiblePersonCommunicationsNumbers", "N3_ResponsiblePersonStreetAddress", "N4_ResponsiblePersonCity_State_ZIPCode"})
public class Loop2100G implements Serializable {
    @JsonProperty("NM1_ResponsiblePerson")
    private NM1_ResponsiblePerson nm1ResponsiblePerson;
    @JsonProperty("PER_ResponsiblePersonCommunicationsNumbers")
    private PER_ResponsiblePersonCommunicationsNumbers perResponsiblePersonCommunicationsNumbers;
    @JsonProperty("N3_ResponsiblePersonStreetAddress")
    private N3_ResponsiblePersonStreetAddress n3ResponsiblePersonStreetAddress;
    @JsonProperty("N4_ResponsiblePersonCity_State_ZIPCode")
    private N4_ResponsiblePersonCity_State_ZIPCode n4ResponsiblePersonCityStateZipCode;
}