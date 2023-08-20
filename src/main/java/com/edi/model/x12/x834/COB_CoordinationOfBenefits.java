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
@JsonPropertyOrder({"PayerResponsibilitySequenceNumberCode_01", "MemberGroupOrPolicyNumber_02", "CoordinationOfBenefitsCode_03", "ServiceTypeCode_04"})
public class COB_CoordinationOfBenefits implements Serializable {
    @JsonProperty("PayerResponsibilitySequenceNumberCode_01")
    private String PayerResponsibilitySequenceNumberCode_01;
    @JsonProperty("MemberGroupOrPolicyNumber_02")
    private String MemberGroupOrPolicyNumber_02;
    @JsonProperty("CoordinationOfBenefitsCode_03")
    private String CoordinationOfBenefitsCode_03;
    @JsonProperty("ServiceTypeCode_04")
    private List<String> ServiceTypeCode_04;
}