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
@JsonPropertyOrder({"UnitOrBasisForMeasurementCode_01", "Exponent_02", "Multiplier_03", "UnitOrBasisForMeasurementCode_04", "Exponent_05", "Multiplier_06", "UnitOrBasisForMeasurementCode_07", "Exponent_08", "Multiplier_09", "UnitOrBasisForMeasurementCode_10", "Exponent_11", "Multiplier_12", "UnitOrBasisForMeasurementCode_13", "Exponent_14", "Multiplier_15"})
public class CompositeUnitOfMeasure_03 implements Serializable {
    @JsonProperty("UnitOrBasisForMeasurementCode_01")
    private String UnitOrBasisForMeasurementCode_01;
    @JsonProperty("Exponent_02")
    private String Exponent_02;
    @JsonProperty("Multiplier_03")
    private String Multiplier_03;
    @JsonProperty("UnitOrBasisForMeasurementCode_04")
    private String UnitOrBasisForMeasurementCode_04;
    @JsonProperty("Exponent_05")
    private String Exponent_05;
    @JsonProperty("Multiplier_06")
    private String Multiplier_06;
    @JsonProperty("UnitOrBasisForMeasurementCode_07")
    private String UnitOrBasisForMeasurementCode_07;
    @JsonProperty("Exponent_08")
    private String Exponent_08;
    @JsonProperty("Multiplier_09")
    private String Multiplier_09;
    @JsonProperty("UnitOrBasisForMeasurementCode_10")
    private String UnitOrBasisForMeasurementCode_10;
    @JsonProperty("Exponent_11")
    private String Exponent_11;
    @JsonProperty("Multiplier_12")
    private String Multiplier_12;
    @JsonProperty("UnitOrBasisForMeasurementCode_13")
    private String UnitOrBasisForMeasurementCode_13;
    @JsonProperty("Exponent_14")
    private String Exponent_14;
    @JsonProperty("Multiplier_15")
    private String Multiplier_15;
}