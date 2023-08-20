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
@JsonPropertyOrder({"MedicarePlanCode_01", "EligibilityReasonCode_02", "EligibilityReasonCode_03", "EligibilityReasonCode_04"})
public class MedicareStatusCode_06 implements Serializable {
    @JsonProperty("MedicarePlanCode_01")
    private String MedicarePlanCode_01;
    @JsonProperty("EligibilityReasonCode_02")
    private String EligibilityReasonCode_02;
    @JsonProperty("EligibilityReasonCode_03")
    private String EligibilityReasonCode_03;
    @JsonProperty("EligibilityReasonCode_04")
    private String EligibilityReasonCode_04;
}