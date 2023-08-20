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
@JsonPropertyOrder({"ContactFunctionCode_01", "ResponseContactName_02", "CommunicationNumberQualifier_03", "ResponseContactCommunicationNumber_04", "CommunicationNumberQualifier_05", "ResponseContactCommunicationNumber_06", "CommunicationNumberQualifier_07", "ResponseContactCommunicationNumber_08", "ContactInquiryReference_09"})
public class PER_MemberSchoolCommunicationsNumbers implements Serializable {
    @JsonProperty("ContactFunctionCode_01")
    private String ContactFunctionCode_01;
    @JsonProperty("ResponseContactName_02")
    private String ResponseContactName_02;
    @JsonProperty("CommunicationNumberQualifier_03")
    private String CommunicationNumberQualifier_03;
    @JsonProperty("ResponseContactCommunicationNumber_04")
    private String ResponseContactCommunicationNumber_04;
    @JsonProperty("CommunicationNumberQualifier_05")
    private String CommunicationNumberQualifier_05;
    @JsonProperty("ResponseContactCommunicationNumber_06")
    private String ResponseContactCommunicationNumber_06;
    @JsonProperty("CommunicationNumberQualifier_07")
    private String CommunicationNumberQualifier_07;
    @JsonProperty("ResponseContactCommunicationNumber_08")
    private String ResponseContactCommunicationNumber_08;
    @JsonProperty("ContactInquiryReference_09")
    private String ContactInquiryReference_09;
}