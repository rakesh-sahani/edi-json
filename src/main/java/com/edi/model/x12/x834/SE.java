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
@JsonPropertyOrder({"NumberOfIncludedSegments_01", "TransactionSetControlNumber_02"})
public class SE implements Serializable {
    @JsonProperty("NumberOfIncludedSegments_01")
    private String NumberOfIncludedSegments_01;
    @JsonProperty("TransactionSetControlNumber_02")
    private String TransactionSetControlNumber_02;
}