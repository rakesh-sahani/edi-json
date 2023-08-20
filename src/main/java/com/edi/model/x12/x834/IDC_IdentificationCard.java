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
@JsonPropertyOrder({"PlanCoverageDescription_01", "IdentificationCardTypeCode_02", "IdentificationCardCount_03", "ActionCode_04"})
public class IDC_IdentificationCard implements Serializable {
    @JsonProperty("PlanCoverageDescription_01")
    private String PlanCoverageDescription_01;
    @JsonProperty("IdentificationCardTypeCode_02")
    private String IdentificationCardTypeCode_02;
    @JsonProperty("IdentificationCardCount_03")
    private String IdentificationCardCount_03;
    @JsonProperty("ActionCode_04")
    private String ActionCode_04;
}