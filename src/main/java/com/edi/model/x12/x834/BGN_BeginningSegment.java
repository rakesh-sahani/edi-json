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
@JsonPropertyOrder({"TransactionSetPurposeCode_01", "TransactionSetReferenceNumber_02", "TransactionSetCreationDate_03", "TransactionSetCreationTime_04", "TimeZoneCode_05", "OriginalTransactionSetReferenceNumber_06", "TransactionTypeCode_07", "ActionCode_08", "SecurityLevelCode_09"})
public class BGN_BeginningSegment implements Serializable {
    @JsonProperty("TransactionSetPurposeCode_01")
    private String TransactionSetPurposeCode_01;
    @JsonProperty("TransactionSetReferenceNumber_02")
    private String TransactionSetReferenceNumber_02;
    @JsonProperty("TransactionSetCreationDate_03")
    private String TransactionSetCreationDate_03;
    @JsonProperty("TransactionSetCreationTime_04")
    private String TransactionSetCreationTime_04;
    @JsonProperty("TimeZoneCode_05")
    private String TimeZoneCode_05;
    @JsonProperty("OriginalTransactionSetReferenceNumber_06")
    private String OriginalTransactionSetReferenceNumber_06;
    @JsonProperty("TransactionTypeCode_07")
    private String TransactionTypeCode_07;
    @JsonProperty("ActionCode_08")
    private String ActionCode_08;
    @JsonProperty("SecurityLevelCode_09")
    private String SecurityLevelCode_09;
}