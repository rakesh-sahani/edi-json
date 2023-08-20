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
@JsonPropertyOrder({"InsuredIndicator_01", "IndividualRelationshipCode_02", "MaintenanceTypeCode_03", "MaintenanceReasonCode_04", "BenefitStatusCode_05", "MedicareStatusCode_06", "ConsolidatedOmnibusBudgetReconciliationActCOBRAQualifyingEventCode_07", "EmploymentStatusCode_08", "StudentStatusCode_09", "YesNoConditionOrResponseCode_10", "DateTimePeriodFormatQualifier_11", "DateTimePeriod_12", "ConfidentialityCode_13", "CityName_14", "StateOrProvinceCode_15", "CountryCode_16", "BirthSequenceNumber_17"})
public class INS_MemberLevelDetail implements Serializable {
    @JsonProperty("InsuredIndicator_01")
    private String InsuredIndicator_01;
    @JsonProperty("IndividualRelationshipCode_02")
    private String IndividualRelationshipCode_02;
    @JsonProperty("MaintenanceTypeCode_03")
    private String MaintenanceTypeCode_03;
    @JsonProperty("MaintenanceReasonCode_04")
    private String MaintenanceReasonCode_04;
    @JsonProperty("BenefitStatusCode_05")
    private String BenefitStatusCode_05;
    @JsonProperty("MedicareStatusCode_06")
    private MedicareStatusCode_06 MedicareStatusCode_06;
    @JsonProperty("ConsolidatedOmnibusBudgetReconciliationActCOBRAQualifyingEventCode_07")
    private String ConsolidatedOmnibusBudgetReconciliationActCOBRAQualifyingEventCode_07;
    @JsonProperty("EmploymentStatusCode_08")
    private String EmploymentStatusCode_08;
    @JsonProperty("StudentStatusCode_09")
    private String StudentStatusCode_09;
    @JsonProperty("YesNoConditionOrResponseCode_10")
    private String YesNoConditionOrResponseCode_10;
    @JsonProperty("DateTimePeriodFormatQualifier_11")
    private String DateTimePeriodFormatQualifier_11;
    @JsonProperty("DateTimePeriod_12")
    private String DateTimePeriod_12;
    @JsonProperty("ConfidentialityCode_13")
    private String ConfidentialityCode_13;
    @JsonProperty("CityName_14")
    private String CityName_14;
    @JsonProperty("StateOrProvinceCode_15")
    private String StateOrProvinceCode_15;
    @JsonProperty("CountryCode_16")
    private String CountryCode_16;
    @JsonProperty("BirthSequenceNumber_17")
    private String BirthSequenceNumber_17;
}