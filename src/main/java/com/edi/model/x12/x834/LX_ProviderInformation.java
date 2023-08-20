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
@JsonPropertyOrder({"AssignedNumber_01"})
public class LX_ProviderInformation implements Serializable {
    @JsonProperty("AssignedNumber_01")
    private String AssignedNumber_01;
}