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
@JsonPropertyOrder({"RaceOrEthnicityCode_01", "CodeListQualifierCode_02", "IndustryCode_03"})
public class CompositeRaceOrEthnicityInformation_05 implements Serializable {
    @JsonProperty("RaceOrEthnicityCode_01")
    private String RaceOrEthnicityCode_01;
    @JsonProperty("CodeListQualifierCode_02")
    private String CodeListQualifierCode_02;
    @JsonProperty("IndustryCode_03")
    private String IndustryCode_03;
}