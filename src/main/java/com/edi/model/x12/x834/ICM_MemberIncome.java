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
@JsonPropertyOrder({"FrequencyCode_01", "WageAmount_02", "WorkHoursCount_03", "LocationIdentificationCode_04", "SalaryGradeCode_05", "CurrencyCode_06"})
public class ICM_MemberIncome implements Serializable {
    @JsonProperty("FrequencyCode_01")
    private String FrequencyCode_01;
    @JsonProperty("WageAmount_02")
    private String WageAmount_02;
    @JsonProperty("WorkHoursCount_03")
    private String WorkHoursCount_03;
    @JsonProperty("LocationIdentificationCode_04")
    private String LocationIdentificationCode_04;
    @JsonProperty("SalaryGradeCode_05")
    private String SalaryGradeCode_05;
    @JsonProperty("CurrencyCode_06")
    private String CurrencyCode_06;
}