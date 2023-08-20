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
@JsonPropertyOrder({"LS_AdditionalReportingCategories", "Loop2700", "LE_AdditionalReportingCategoriesTermination"})
public class LoopLS implements Serializable {
    @JsonProperty("LS_AdditionalReportingCategories")
    private LS_AdditionalReportingCategories lsAdditionalReportingCategories;
    @JsonProperty("Loop2700")
    private List<Loop2700> loop2700;
    @JsonProperty("LE_AdditionalReportingCategoriesTermination")
    private LE_AdditionalReportingCategoriesTermination leAdditionalReportingCategoriesTermination;
}