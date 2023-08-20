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
@JsonPropertyOrder({"QuantityQualifier_01", "AmbulancePatientCount_02", "CompositeUnitOfMeasure_03", "FreeformInformation_04"})
public class QTY_TransactionSetControlTotals implements Serializable {
    @JsonProperty("QuantityQualifier_01")
    private String QuantityQualifier_01;
    @JsonProperty("AmbulancePatientCount_02")
    private String AmbulancePatientCount_02;
    @JsonProperty("CompositeUnitOfMeasure_03")
    private CompositeUnitOfMeasure_03 CompositeUnitOfMeasure_03;
    @JsonProperty("FreeformInformation_04")
    private String FreeformInformation_04;
}