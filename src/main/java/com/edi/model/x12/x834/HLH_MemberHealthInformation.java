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
@JsonPropertyOrder({"HealthRelatedCode_01", "MemberHeight_02", "MemberWeight_03", "Weight_04", "Description_05", "CurrentHealthConditionCode_06", "Description_07"})
public class HLH_MemberHealthInformation implements Serializable {
    @JsonProperty("HealthRelatedCode_01")
    private String HealthRelatedCode_01;
    @JsonProperty("MemberHeight_02")
    private String MemberHeight_02;
    @JsonProperty("MemberWeight_03")
    private String MemberWeight_03;
    @JsonProperty("Weight_04")
    private String Weight_04;
    @JsonProperty("Description_05")
    private String Description_05;
    @JsonProperty("CurrentHealthConditionCode_06")
    private String CurrentHealthConditionCode_06;
    @JsonProperty("Description_07")
    private String Description_07;
}