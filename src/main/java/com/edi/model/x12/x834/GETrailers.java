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
@JsonPropertyOrder({"NumberOfIncludedSets_01", "GroupControlNumber_02"})
public class GETrailers implements Serializable {
    @JsonProperty("NumberOfIncludedSets_01")
    private String NumberOfIncludedSets_01;
    @JsonProperty("GroupControlNumber_02")
    private String GroupControlNumber_02;
}