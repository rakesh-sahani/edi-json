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
@JsonPropertyOrder({"IdentificationCodeQualifier_01", "LanguageCode_02", "LanguageDescription_03", "LanguageUseIndicator_04", "LanguageProficiencyIndicator_05"})
public class LUI_MemberLanguage implements Serializable {
    @JsonProperty("IdentificationCodeQualifier_01")
    private String IdentificationCodeQualifier_01;
    @JsonProperty("LanguageCode_02")
    private String LanguageCode_02;
    @JsonProperty("LanguageDescription_03")
    private String LanguageDescription_03;
    @JsonProperty("LanguageUseIndicator_04")
    private String LanguageUseIndicator_04;
    @JsonProperty("LanguageProficiencyIndicator_05")
    private String LanguageProficiencyIndicator_05;
}