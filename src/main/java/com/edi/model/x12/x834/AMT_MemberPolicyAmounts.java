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
@JsonPropertyOrder({"AmountQualifierCode_01", "TotalClaimChargeAmount_02", "CreditDebitFlagCode_03"})
public class AMT_MemberPolicyAmounts implements Serializable {
    @JsonProperty("AmountQualifierCode_01")
    private String AmountQualifierCode_01;
    @JsonProperty("TotalClaimChargeAmount_02")
    private String TotalClaimChargeAmount_02;
    @JsonProperty("CreditDebitFlagCode_03")
    private String CreditDebitFlagCode_03;
}