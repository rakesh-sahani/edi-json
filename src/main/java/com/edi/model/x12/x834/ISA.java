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
@JsonPropertyOrder({"AuthorizationInformationQualifier_01", "AuthorizationInformation_02", "SecurityInformationQualifier_03", "SecurityInformation_04", "SenderIDQualifier_05", "InterchangeSenderID_06", "ReceiverIDQualifier_07", "InterchangeReceiverID_08", "InterchangeDate_09", "InterchangeTime_10", "InterchangeControlStandardsIdentifier_11", "InterchangeControlVersionNumber_12", "InterchangeControlNumber_13", "AcknowledgementRequested_14", "UsageIndicator_15", "ComponentElementSeparator_16"})
public class ISA implements Serializable {
    @JsonProperty("AuthorizationInformationQualifier_01")
    private String AuthorizationInformationQualifier_01;
    @JsonProperty("AuthorizationInformation_02")
    private String AuthorizationInformation_02;
    @JsonProperty("SecurityInformationQualifier_03")
    private String SecurityInformationQualifier_03;
    @JsonProperty("SecurityInformation_04")
    private String SecurityInformation_04;
    @JsonProperty("SenderIDQualifier_05")
    private String SenderIDQualifier_05;
    @JsonProperty("InterchangeSenderID_06")
    private String InterchangeSenderID_06;
    @JsonProperty("ReceiverIDQualifier_07")
    private String ReceiverIDQualifier_07;
    @JsonProperty("InterchangeReceiverID_08")
    private String InterchangeReceiverID_08;
    @JsonProperty("InterchangeDate_09")
    private String InterchangeDate_09;
    @JsonProperty("InterchangeTime_10")
    private String InterchangeTime_10;
    @JsonProperty("InterchangeControlStandardsIdentifier_11")
    private String InterchangeControlStandardsIdentifier_11;
    @JsonProperty("InterchangeControlVersionNumber_12")
    private String InterchangeControlVersionNumber_12;
    @JsonProperty("InterchangeControlNumber_13")
    private String InterchangeControlNumber_13;
    @JsonProperty("AcknowledgementRequested_14")
    private String AcknowledgementRequested_14;
    @JsonProperty("UsageIndicator_15")
    private String UsageIndicator_15;
    @JsonProperty("ComponentElementSeparator_16")
    private String ComponentElementSeparator_16;
}