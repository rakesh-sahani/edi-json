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
@JsonPropertyOrder({"REF_HealthCoveragePolicyNumber", "REF_PriorCoverageMonths"})
public class All_REF implements Serializable {
    @JsonProperty("REF_HealthCoveragePolicyNumber")
    private List<REF_HealthCoveragePolicyNumber> refHealthCoveragePolicyNumbers;
    @JsonProperty("REF_PriorCoverageMonths")
    private REF_PriorCoverageMonths refPriorCoverageMonths;
}