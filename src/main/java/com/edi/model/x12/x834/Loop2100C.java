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
@JsonPropertyOrder({"NM1_MemberMailingAddress", "N3_MemberMailStreetAddress", "N4_MemberMailCity_State_ZIPCode"})
public class Loop2100C implements Serializable {
    @JsonProperty("NM1_MemberMailingAddress")
    private NM1_MemberMailingAddress nm1MemberMailingAddress;
    @JsonProperty("N3_MemberMailStreetAddress")
    private N3_MemberMailStreetAddress n3MemberMailStreetAddress;
    @JsonProperty("N4_MemberMailCity_State_ZIPCode")
    private N4_MemberMailCity_State_ZIPCode n4MemberMailCityStateZipCode;
}