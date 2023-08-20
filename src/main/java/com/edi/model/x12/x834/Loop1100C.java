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
@JsonPropertyOrder({"ACT_TPA_BrokerAccountInformation"})
public class Loop1100C implements Serializable {
    @JsonProperty("ACT_TPA_BrokerAccountInformation")
    private ACT_TPA_BrokerAccountInformation actTpaBrokerAccountInformation;
}