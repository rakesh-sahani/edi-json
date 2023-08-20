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
@JsonPropertyOrder({"COB_CoordinationOfBenefits", "REF_AdditionalCoordinationOfBenefitsIdentifiers", "DTP_CoordinationOfBenefitsEligibilityDates", "Loop2330"})
public class Loop2320 implements Serializable {
    @JsonProperty("COB_CoordinationOfBenefits")
    private COB_CoordinationOfBenefits cobCoordinationOfBenefits;
    @JsonProperty("REF_AdditionalCoordinationOfBenefitsIdentifiers")
    private List<REF_AdditionalCoordinationOfBenefitsIdentifiers> refAdditionalCoordinationOfBenefitsIdentifiers;
    @JsonProperty("DTP_CoordinationOfBenefitsEligibilityDates")
    private List<DTP_CoordinationOfBenefitsEligibilityDates> dtpCoordinationOfBenefitsEligibilityDates;
    @JsonProperty("Loop2330")
    private List<Loop2330> loop2330;
}