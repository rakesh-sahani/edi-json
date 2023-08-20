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
@JsonPropertyOrder({"Loop1000A", "Loop1000B", "Loop1000C"})
public class AllN1 implements Serializable {
    @JsonProperty("Loop1000A")
    private Loop1000A Loop1000A;
    @JsonProperty("Loop1000B")
    private Loop1000B Loop1000B;
    @JsonProperty("Loop1000C")
    private List<Loop1000C> Loop1000C;
}