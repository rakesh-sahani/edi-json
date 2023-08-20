package com.edi.model.x12;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;
import java.util.LinkedHashMap;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonPropertyOrder({"SegmentName", "SegmentMap"})
public class Segment implements Serializable {
    @JsonProperty("SegmentName")
    private String SegmentName;
    @JsonProperty("SegmentMap")
    private LinkedHashMap<String, String> SegmentMap;
}