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
@JsonPropertyOrder({"ActionCode_01", "EntityIdentifierCode_02", "ProviderEffectiveDate_03", "Time_04", "MaintenanceReasonCode_05"})
public class PLA_ProviderChangeReason implements Serializable {
    @JsonProperty("ActionCode_01")
    private String ActionCode_01;
    @JsonProperty("EntityIdentifierCode_02")
    private String EntityIdentifierCode_02;
    @JsonProperty("ProviderEffectiveDate_03")
    private String ProviderEffectiveDate_03;
    @JsonProperty("Time_04")
    private String Time_04;
    @JsonProperty("MaintenanceReasonCode_05")
    private String MaintenanceReasonCode_05;
}