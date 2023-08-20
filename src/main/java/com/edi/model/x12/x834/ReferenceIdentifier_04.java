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
@JsonPropertyOrder({"ReferenceIdentificationQualifier_01", "ReferenceIdentification_02", "ReferenceIdentificationQualifier_03", "ReferenceIdentification_04", "ReferenceIdentificationQualifier_05", "ReferenceIdentification_06"})
public class ReferenceIdentifier_04 implements Serializable {
    @JsonProperty("ReferenceIdentificationQualifier_01")
    private String ReferenceIdentificationQualifier_01;
    @JsonProperty("ReferenceIdentification_02")
    private String ReferenceIdentification_02;
    @JsonProperty("ReferenceIdentificationQualifier_03")
    private String ReferenceIdentificationQualifier_03;
    @JsonProperty("ReferenceIdentification_04")
    private String ReferenceIdentification_04;
    @JsonProperty("ReferenceIdentificationQualifier_05")
    private String ReferenceIdentificationQualifier_05;
    @JsonProperty("ReferenceIdentification_06")
    private String ReferenceIdentification_06;
}