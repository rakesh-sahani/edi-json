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
@JsonPropertyOrder({"DSB_DisabilityInformation", "DTP_DisabilityEligibilityDates"})
public class Loop2200 implements Serializable {
    @JsonProperty("DSB_DisabilityInformation")
    private DSB_DisabilityInformation dsbDisabilityInformation;
    @JsonProperty("DTP_DisabilityEligibilityDates")
    private List<DTP_DisabilityEligibilityDates> dtpDisabilityEligibilityDates;
}