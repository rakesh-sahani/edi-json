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
@JsonPropertyOrder({"HD_HealthCoverage", "DTP_HealthCoverageDates", "AMT_HealthCoveragePolicy", "AllREF", "IDC_IdentificationCard", "Loop2310", "Loop2320"})
public class Loop2300 implements Serializable {
    @JsonProperty("HD_HealthCoverage")
    private HD_HealthCoverage hdHealthCoverage;
    @JsonProperty("DTP_HealthCoverageDates")
    private List<DTP_HealthCoverageDates> dtpHealthCoverageDates;
    @JsonProperty("AMT_HealthCoveragePolicy")
    private List<AMT_HealthCoveragePolicy> amtHealthCoveragePolicies;
    @JsonProperty("AllREF")
    private All_REF allREF;
    @JsonProperty("IDC_IdentificationCard")
    private List<IDC_IdentificationCard> idcIdentificationCards;
    @JsonProperty("Loop2310")
    private List<Loop2310> loop2310;
    @JsonProperty("Loop2320")
    private List<Loop2320> loop2320;
}
