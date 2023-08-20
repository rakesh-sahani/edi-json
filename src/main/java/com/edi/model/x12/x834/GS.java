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
@JsonPropertyOrder({"CodeIdentifyingInformationType_01", "SenderIDCode_02", "ReceiverIDCode_03", "Date_04", "Time_05", "GroupControlNumber_06", "TransactionTypeCode_07", "VersionAndRelease_08"})
public class GS implements Serializable {
    @JsonProperty("CodeIdentifyingInformationType_01")
    private String CodeIdentifyingInformationType_01;
    @JsonProperty("SenderIDCode_02")
    private String SenderIDCode_02;
    @JsonProperty("ReceiverIDCode_03")
    private String ReceiverIDCode_03;
    @JsonProperty("Date_04")
    private String Date_04;
    @JsonProperty("Time_05")
    private String Time_05;
    @JsonProperty("GroupControlNumber_06")
    private String GroupControlNumber_06;
    @JsonProperty("TransactionTypeCode_07")
    private String TransactionTypeCode_07;
    @JsonProperty("VersionAndRelease_08")
    private String VersionAndRelease_08;
}