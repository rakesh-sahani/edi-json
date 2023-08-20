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
@JsonPropertyOrder({"ReferenceIdentificationQualifier_01", "MemberGroupOrPolicyNumber_02", "Description_03", "ReferenceIdentifier_04"})
public class REF_HealthCoveragePolicyNumber implements Serializable {
    @JsonProperty("ReferenceIdentificationQualifier_01")
    private String ReferenceIdentificationQualifier_01;
    @JsonProperty("MemberGroupOrPolicyNumber_02")
    private String MemberGroupOrPolicyNumber_02;
    @JsonProperty("Description_03")
    private String Description_03;
    @JsonProperty("ReferenceIdentifier_04")
    private ReferenceIdentifier_04 ReferenceIdentifier_04;
}