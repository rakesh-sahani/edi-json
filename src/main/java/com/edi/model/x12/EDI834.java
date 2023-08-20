package com.edi.model.x12;

import com.edi.model.x12.x834.Groups;
import com.edi.model.x12.x834.IEATrailers;
import com.edi.model.x12.x834.ISA;
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
@JsonPropertyOrder({"SegmentDelimiter", "DataElementDelimiter", "ISA", "Groups", "IEATrailers"})
public class EDI834 implements Serializable {
    @JsonProperty("SegmentDelimiter")
    private String SegmentDelimiter;
    @JsonProperty("DataElementDelimiter")
    private String DataElementDelimiter;
    @JsonProperty("ISA")
    private ISA isa;
    @JsonProperty("Groups")
    private List<Groups> groups;
    @JsonProperty("IEATrailers")
    private List<IEATrailers> ieaTrailers;
}