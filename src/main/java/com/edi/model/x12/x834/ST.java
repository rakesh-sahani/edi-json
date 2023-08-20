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
@JsonPropertyOrder({"TransactionSetIdentifierCode_01", "TransactionSetControlNumber_02", "ImplementationConventionPreference_03"})
public class ST implements Serializable {
    @JsonProperty("TransactionSetIdentifierCode_01")
    private String TransactionSetIdentifierCode_01;
    @JsonProperty("TransactionSetControlNumber_02")
    private String TransactionSetControlNumber_02;
    @JsonProperty("ImplementationConventionPreference_03")
    private String ImplementationConventionPreference_03;
}