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
@JsonPropertyOrder({"NM1_CoordinationOfBenefitsRelatedEntity","N3_CoordinationOfBenefitsRelatedEntityAddress","N4_CoordinationOfBenefitsOtherInsuranceCompanyCity_State_ZIPCode","PER_AdministrativeCommunicationsContact"})
public class Loop2330 implements Serializable {
    @JsonProperty("NM1_CoordinationOfBenefitsRelatedEntity")
    private NM1_CoordinationOfBenefitsRelatedEntity nm1CoordinationOfBenefitsRelatedEntity;
    @JsonProperty("N3_CoordinationOfBenefitsRelatedEntityAddress")
    private N3_CoordinationOfBenefitsRelatedEntityAddress n3CoordinationOfBenefitsRelatedEntityAddress;
    @JsonProperty("N4_CoordinationOfBenefitsOtherInsuranceCompanyCity_State_ZIPCode")
    private N4_CoordinationOfBenefitsOtherInsuranceCompanyCity_State_ZIPCode n4CoordinationOfBenefitsOtherInsuranceCompanyCityStateZipCode;
    @JsonProperty("PER_AdministrativeCommunicationsContact")
    private PER_AdministrativeCommunicationsContact perAdministrativeCommunicationsContact;
}