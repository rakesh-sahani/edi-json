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
@JsonPropertyOrder({"MaintenanceTypeCode_01", "MaintenanceReasonCode_02", "InsuranceLineCode_03", "PlanCoverageDescription_04", "CoverageLevelCode_05", "Count_06", "Count_07", "UnderwritingDecisionCode_08", "LateEnrollmentIndicator_09", "DrugHouseCode_10", "YesNoConditionOrResponseCode_11"})
public class HD_HealthCoverage implements Serializable {
    @JsonProperty("MaintenanceTypeCode_01")
    private String MaintenanceTypeCode_01;
    @JsonProperty("MaintenanceReasonCode_02")
    private String MaintenanceReasonCode_02;
    @JsonProperty("InsuranceLineCode_03")
    private String InsuranceLineCode_03;
    @JsonProperty("PlanCoverageDescription_04")
    private String PlanCoverageDescription_04;
    @JsonProperty("CoverageLevelCode_05")
    private String CoverageLevelCode_05;
    @JsonProperty("Count_06")
    private String Count_06;
    @JsonProperty("Count_07")
    private String Count_07;
    @JsonProperty("UnderwritingDecisionCode_08")
    private String UnderwritingDecisionCode_08;
    @JsonProperty("LateEnrollmentIndicator_09")
    private String LateEnrollmentIndicator_09;
    @JsonProperty("DrugHouseCode_10")
    private String DrugHouseCode_10;
    @JsonProperty("YesNoConditionOrResponseCode_11")
    private String YesNoConditionOrResponseCode_11;
}