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
@JsonPropertyOrder({"EntityIdentifierCode_01", "EntityTypeQualifier_02", "ResponseContactLastOrOrganizationName_03", "ResponseContactFirstName_04", "ResponseContactMiddleName_05", "NamePrefix_06", "ResponseContactNameSuffix_07", "IdentificationCodeQualifier_08", "ResponseContactIdentifier_09", "EntityRelationshipCode_10", "EntityIdentifierCode_11", "NameLastOrOrganizationName_12"})
public class NM1_MemberEmployer implements Serializable {
    @JsonProperty("EntityIdentifierCode_01")
    private String EntityIdentifierCode_01;
    @JsonProperty("EntityTypeQualifier_02")
    private String EntityTypeQualifier_02;
    @JsonProperty("ResponseContactLastOrOrganizationName_03")
    private String ResponseContactLastOrOrganizationName_03;
    @JsonProperty("ResponseContactFirstName_04")
    private String ResponseContactFirstName_04;
    @JsonProperty("ResponseContactMiddleName_05")
    private String ResponseContactMiddleName_05;
    @JsonProperty("NamePrefix_06")
    private String NamePrefix_06;
    @JsonProperty("ResponseContactNameSuffix_07")
    private String ResponseContactNameSuffix_07;
    @JsonProperty("IdentificationCodeQualifier_08")
    private String IdentificationCodeQualifier_08;
    @JsonProperty("ResponseContactIdentifier_09")
    private String ResponseContactIdentifier_09;
    @JsonProperty("EntityRelationshipCode_10")
    private String EntityRelationshipCode_10;
    @JsonProperty("EntityIdentifierCode_11")
    private String EntityIdentifierCode_11;
    @JsonProperty("NameLastOrOrganizationName_12")
    private String NameLastOrOrganizationName_12;
}