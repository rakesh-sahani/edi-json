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
@JsonPropertyOrder({"AdditionalPatientInformationContactCityName_01", "AdditionalPatientInformationContactStateCode_02", "AdditionalPatientInformationContactPostalZoneOrZIPCode_03", "CountryCode_04", "LocationQualifier_05", "LocationIdentifier_06", "CountrySubdivisionCode_07"})
public class N4_MemberCity_State_ZIPCode implements Serializable {
    @JsonProperty("AdditionalPatientInformationContactCityName_01")
    private String AdditionalPatientInformationContactCityName_01;
    @JsonProperty("AdditionalPatientInformationContactStateCode_02")
    private String AdditionalPatientInformationContactStateCode_02;
    @JsonProperty("AdditionalPatientInformationContactPostalZoneOrZIPCode_03")
    private String AdditionalPatientInformationContactPostalZoneOrZIPCode_03;
    @JsonProperty("CountryCode_04")
    private String CountryCode_04;
    @JsonProperty("LocationQualifier_05")
    private String LocationQualifier_05;
    @JsonProperty("LocationIdentifier_06")
    private String LocationIdentifier_06;
    @JsonProperty("CountrySubdivisionCode_07")
    private String CountrySubdivisionCode_07;
}