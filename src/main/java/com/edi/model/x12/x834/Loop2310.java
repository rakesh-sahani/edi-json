package com.edi.model.x12.x834;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonPropertyOrder({"LX_ProviderInformation", "NM1_ProviderName", "N3_ProviderAddress", "N4_ProviderCity_State_ZIPCode", "PER_ProviderCommunicationsNumbers", "PLA_ProviderChangeReason"})
public class Loop2310 implements Serializable {
    @JsonProperty("LX_ProviderInformation")
    private LX_ProviderInformation lxProviderInformation;
    @JsonProperty("NM1_ProviderName")
    private NM1_ProviderName nm1ProviderName;
    @JsonProperty("N3_ProviderAddress")
    private List<N3_ProviderAddress> n3ProviderAddresses;
    @JsonProperty("N4_ProviderCity_State_ZIPCode")
    private N4_ProviderCity_State_ZIPCode n4ProviderCityStateZipCode;
    @JsonProperty("PER_ProviderCommunicationsNumbers")
    private List<PER_ProviderCommunicationsNumbers> perProviderCommunicationsNumbers;
    @JsonProperty("PLA_ProviderChangeReason")
    private PLA_ProviderChangeReason plaProviderChangeReason;
}