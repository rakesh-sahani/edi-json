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
@JsonPropertyOrder({"TPAorBrokerAccountNumber_01", "Name_02", "IdentificationCodeQualifier_03", "IdentificationCode_04", "AccountNumberQualifier_05", "TPAorBrokerAccountNumber_06", "Description_07", "PaymentMethodTypeCode_08", "BenefitStatusCode_09"})
public class ACT_TPA_BrokerAccountInformation implements Serializable {
    @JsonProperty("TPAorBrokerAccountNumber_01")
    private String TPAorBrokerAccountNumber_01;
    @JsonProperty("Name_02")
    private String Name_02;
    @JsonProperty("IdentificationCodeQualifier_03")
    private String IdentificationCodeQualifier_03;
    @JsonProperty("IdentificationCode_04")
    private String IdentificationCode_04;
    @JsonProperty("AccountNumberQualifier_05")
    private String AccountNumberQualifier_05;
    @JsonProperty("TPAorBrokerAccountNumber_06")
    private String TPAorBrokerAccountNumber_06;
    @JsonProperty("Description_07")
    private String Description_07;
    @JsonProperty("PaymentMethodTypeCode_08")
    private String PaymentMethodTypeCode_08;
    @JsonProperty("BenefitStatusCode_09")
    private String BenefitStatusCode_09;
}