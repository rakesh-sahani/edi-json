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
@JsonPropertyOrder({"EmploymentClassCode_01", "EmploymentClassCode_02", "EmploymentClassCode_03", "PercentageAsDecimal_04", "InformationStatusCode_05", "OccupationCode_06"})
public class EC_EmploymentClass implements Serializable {
    @JsonProperty("EmploymentClassCode_01")
    private String EmploymentClassCode_01;
    @JsonProperty("EmploymentClassCode_02")
    private String EmploymentClassCode_02;
    @JsonProperty("EmploymentClassCode_03")
    private String EmploymentClassCode_03;
    @JsonProperty("PercentageAsDecimal_04")
    private String PercentageAsDecimal_04;
    @JsonProperty("InformationStatusCode_05")
    private String InformationStatusCode_05;
    @JsonProperty("OccupationCode_06")
    private String OccupationCode_06;
}