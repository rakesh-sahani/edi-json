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
@JsonPropertyOrder({"NM1_DropOffLocation", "N3_DropOffLocationStreetAddress", "N4_DropOffLocationCity_State_ZIPCode"})
public class Loop2100H implements Serializable {
    @JsonProperty("NM1_DropOffLocation")
    private NM1_DropOffLocation nm1DropOffLocation;
    @JsonProperty("N3_DropOffLocationStreetAddress")
    private N3_DropOffLocationStreetAddress n3DropOffLocationStreetAddress;
    @JsonProperty("N4_DropOffLocationCity_State_ZIPCode")
    private N4_DropOffLocationCity_State_ZIPCode n4DropOffLocationCityStateZipCode;
}