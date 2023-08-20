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
@JsonPropertyOrder({"Loop2100A", "Loop2100B", "Loop2100C", "Loop2100D", "Loop2100E", "Loop2100F", "Loop2100G", "Loop2100H"})
public class AllNM1 implements Serializable {
    @JsonProperty("Loop2100A")
    private Loop2100A loop2100A;
    @JsonProperty("Loop2100B")
    private Loop2100B loop2100B;
    @JsonProperty("Loop2100C")
    private Loop2100C loop2100C;
    @JsonProperty("Loop2100D")
    private List<Loop2100D> loop2100D;
    @JsonProperty("Loop2100E")
    private List<Loop2100E> loop2100E;
    @JsonProperty("Loop2100F")
    private Loop2100F loop2100F;
    @JsonProperty("Loop2100G")
    private List<Loop2100G> loop2100G;
    @JsonProperty("Loop2100H")
    private Loop2100H loop2100H;
}