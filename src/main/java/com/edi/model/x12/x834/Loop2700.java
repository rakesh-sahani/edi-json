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
@JsonPropertyOrder({"LX_MemberReportingCategories", "Loop2750"})
public class Loop2700 implements Serializable {
    @JsonProperty("LX_MemberReportingCategories")
    private LX_MemberReportingCategories lxMemberReportingCategories;
    @JsonProperty("Loop2750")
    private Loop2750 loop2750;
}