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
@JsonPropertyOrder({"N1_Payer"})
public class Loop1000B implements Serializable {
    @JsonProperty("N1_Payer")
    private N1_Payer n1Payer;
}