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
@JsonPropertyOrder({"NM1_MemberName", "PER_MemberCommunicationsNumbers", "N3_MemberResidenceStreetAddress", "N4_MemberCity_State_ZIPCode", "DMG_MemberDemographics", "EC_EmploymentClass", "ICM_MemberIncome", "AMT_MemberPolicyAmounts", "HLH_MemberHealthInformation", "LUI_MemberLanguage"})
public class Loop2100A implements Serializable {
    @JsonProperty("NM1_MemberName")
    private NM1_MemberName nm1MemberName;
    @JsonProperty("PER_MemberCommunicationsNumbers")
    private PER_MemberCommunicationsNumbers perMemberCommunicationsNumbers;
    @JsonProperty("N3_MemberResidenceStreetAddress")
    private N3_MemberResidenceStreetAddress n3MemberResidenceStreetAddress;
    @JsonProperty("N4_MemberCity_State_ZIPCode")
    private N4_MemberCity_State_ZIPCode n4MemberCityStateZipCode;
    @JsonProperty("DMG_MemberDemographics")
    private DMG_MemberDemographics dmgMemberDemographics;
    @JsonProperty("EC_EmploymentClass")
    private List<EC_EmploymentClass> ecEmploymentClasses;
    @JsonProperty("ICM_MemberIncome")
    private ICM_MemberIncome icmMemberIncome;
    @JsonProperty("AMT_MemberPolicyAmounts")
    private List<AMT_MemberPolicyAmounts> amtMemberPolicyAmounts;
    @JsonProperty("HLH_MemberHealthInformation")
    private HLH_MemberHealthInformation hlhMemberHealthInformation;
    @JsonProperty("LUI_MemberLanguage")
    private List<LUI_MemberLanguage> luiMemberLanguages;
}