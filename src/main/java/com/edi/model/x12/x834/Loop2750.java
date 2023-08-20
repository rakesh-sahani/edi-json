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
@JsonPropertyOrder({"N1_ReportingCategory", "REF_ReportingCategoryReference", "DTP_ReportingCategoryDate"})
public class Loop2750 implements Serializable {
    @JsonProperty("N1_ReportingCategory")
    private N1_ReportingCategory n1ReportingCategory;
    @JsonProperty("REF_ReportingCategoryReference")
    private REF_ReportingCategoryReference refReportingCategoryReference;
    @JsonProperty("DTP_ReportingCategoryDate")
    private DTP_ReportingCategoryDate dtpReportingCategoryDate;
}