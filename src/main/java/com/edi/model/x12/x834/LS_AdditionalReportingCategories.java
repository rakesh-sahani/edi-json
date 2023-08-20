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
@JsonPropertyOrder({"LoopIdentifierCode_01"})
public class LS_AdditionalReportingCategories implements Serializable {
    @JsonProperty("LoopIdentifierCode_01")
    private String LoopIdentifierCode_01;
}