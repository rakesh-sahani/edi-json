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
@JsonPropertyOrder({"DateTimeQualifier_01", "DateTimePeriodFormatQualifier_02", "DateTimePeriod_03"})
public class DTP_CoordinationOfBenefitsEligibilityDates implements Serializable {
    @JsonProperty("DateTimeQualifier_01")
    private String DateTimeQualifier_01;
    @JsonProperty("DateTimePeriodFormatQualifier_02")
    private String DateTimePeriodFormatQualifier_02;
    @JsonProperty("DateTimePeriod_03")
    private String DateTimePeriod_03;
}