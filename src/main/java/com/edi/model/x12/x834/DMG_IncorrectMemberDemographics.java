package com.edi.model.x12.x834;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonPropertyOrder({"DateTimePeriodFormatQualifier_01", "DependentBirthDate_02", "DependentGenderCode_03", "MaritalStatusCode_04", "CompositeRaceOrEthnicityInformation_05", "CitizenshipStatusCode_06", "CountryCode_07", "BasisOfVerificationCode_08", "Quantity_09", "CodeListQualifierCode_10", "IndustryCode_11"})
public class DMG_IncorrectMemberDemographics implements Serializable {
    @JsonProperty("DateTimePeriodFormatQualifier_01")
    private String DateTimePeriodFormatQualifier_01;
    @JsonProperty("DependentBirthDate_02")
    private String DependentBirthDate_02;
    @JsonProperty("DependentGenderCode_03")
    private String DependentGenderCode_03;
    @JsonProperty("MaritalStatusCode_04")
    private String MaritalStatusCode_04;
    @JsonProperty("CompositeRaceOrEthnicityInformation_05")
    private List<CompositeRaceOrEthnicityInformation_05> compositeRaceOrEthnicityInformation05;
    @JsonProperty("CitizenshipStatusCode_06")
    private String CitizenshipStatusCode_06;
    @JsonProperty("CountryCode_07")
    private String CountryCode_07;
    @JsonProperty("BasisOfVerificationCode_08")
    private String BasisOfVerificationCode_08;
    @JsonProperty("Quantity_09")
    private String Quantity_09;
    @JsonProperty("CodeListQualifierCode_10")
    private String CodeListQualifierCode_10;
    @JsonProperty("IndustryCode_11")
    private String IndustryCode_11;
}