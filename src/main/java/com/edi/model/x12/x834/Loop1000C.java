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
@JsonPropertyOrder({"N1_TPA_BrokerName", "Loop1100C"})
public class Loop1000C implements Serializable {
    @JsonProperty("N1_TPA_BrokerName")
    private N1_TPA_BrokerName n1TpaBrokerName;
    @JsonProperty("Loop1100C")
    private Loop1100C loop1100C;
}