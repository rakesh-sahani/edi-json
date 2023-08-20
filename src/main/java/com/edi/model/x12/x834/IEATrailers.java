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
@JsonPropertyOrder({"NumberOfIncludedGroups_01", "InterchangeControlNumber_02"})
public class IEATrailers implements Serializable {
    @JsonProperty("NumberOfIncludedGroups_01")
    private String NumberOfIncludedGroups_01;
    @JsonProperty("InterchangeControlNumber_02")
    private String InterchangeControlNumber_02;
}