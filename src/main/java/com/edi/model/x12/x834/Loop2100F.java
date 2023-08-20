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
@JsonPropertyOrder({"NM1_CustodialParent", "PER_CustodialParentCommunicationsNumbers", "N3_CustodialParentStreetAddress", "N4_CustodialParentCity_State_ZIPCode"})
public class Loop2100F implements Serializable {
    @JsonProperty("NM1_CustodialParent")
    private NM1_CustodialParent nm1CustodialParent;
    @JsonProperty("PER_CustodialParentCommunicationsNumbers")
    private PER_CustodialParentCommunicationsNumbers perCustodialParentCommunicationsNumbers;
    @JsonProperty("N3_CustodialParentStreetAddress")
    private N3_CustodialParentStreetAddress n3CustodialParentStreetAddress;
    @JsonProperty("N4_CustodialParentCity_State_ZIPCode")
    private N4_CustodialParentCity_State_ZIPCode n4CustodialParentCityStateZipCode;
}