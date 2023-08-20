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
@JsonPropertyOrder({"DisabilityTypeCode_01", "Quantity_02", "OccupationCode_03", "WorkIntensityCode_04", "ProductOptionCode_05", "MonetaryAmount_06", "ProductOrServiceIDQualifier_07", "DiagnosisCode_08"})
public class DSB_DisabilityInformation implements Serializable {
    @JsonProperty("DisabilityTypeCode_01")
    private String DisabilityTypeCode_01;
    @JsonProperty("Quantity_02")
    private String Quantity_02;
    @JsonProperty("OccupationCode_03")
    private String OccupationCode_03;
    @JsonProperty("WorkIntensityCode_04")
    private String WorkIntensityCode_04;
    @JsonProperty("ProductOptionCode_05")
    private String ProductOptionCode_05;
    @JsonProperty("MonetaryAmount_06")
    private String MonetaryAmount_06;
    @JsonProperty("ProductOrServiceIDQualifier_07")
    private String ProductOrServiceIDQualifier_07;
    @JsonProperty("DiagnosisCode_08")
    private String DiagnosisCode_08;
}