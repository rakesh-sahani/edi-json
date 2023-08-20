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
@JsonPropertyOrder({"EntityIdentifierCode_01", "PremiumPayerName_02", "IdentificationCodeQualifier_03", "IntermediaryBankIdentifier_04", "EntityRelationshipCode_05", "EntityIdentifierCode_06"})
public class N1_TPA_BrokerName implements Serializable {
    @JsonProperty("EntityIdentifierCode_01")
    private String EntityIdentifierCode_01;
    @JsonProperty("PremiumPayerName_02")
    private String PremiumPayerName_02;
    @JsonProperty("IdentificationCodeQualifier_03")
    private String IdentificationCodeQualifier_03;
    @JsonProperty("IntermediaryBankIdentifier_04")
    private String IntermediaryBankIdentifier_04;
    @JsonProperty("EntityRelationshipCode_05")
    private String EntityRelationshipCode_05;
    @JsonProperty("EntityIdentifierCode_06")
    private String EntityIdentifierCode_06;
}