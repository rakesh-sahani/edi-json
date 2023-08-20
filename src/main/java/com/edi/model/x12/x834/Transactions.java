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
@JsonPropertyOrder({"ST", "BGN_BeginningSegment", "REF_TransactionSetPolicyNumber", "DTP_FileEffectiveDate", "QTY_TransactionSetControlTotals", "AllN1", "Loop2000", "SE"})
public class Transactions implements Serializable {
    @JsonProperty("ST")
    private ST st;
    @JsonProperty("BGN_BeginningSegment")
    private BGN_BeginningSegment bgnBeginningSegment;
    @JsonProperty("REF_TransactionSetPolicyNumber")
    private REF_TransactionSetPolicyNumber refTransactionSetPolicyNumber;
    @JsonProperty("DTP_FileEffectiveDate")
    private List<DTP_FileEffectiveDate> dtpFileEffectiveDates;
    @JsonProperty("QTY_TransactionSetControlTotals")
    private List<QTY_TransactionSetControlTotals> qtyTransactionSetControlTotals;
    @JsonProperty("AllN1")
    private AllN1 allN1;
    @JsonProperty("Loop2000")
    private List<Loop2000> loop2000;
    @JsonProperty("SE")
    private SE se;
}