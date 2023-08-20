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
@JsonPropertyOrder({"GS", "Transactions", "GETrailers"})
public class Groups implements Serializable {
    @JsonProperty("GS")
    private GS gs;
    @JsonProperty("Transactions")
    private List<Transactions> transactions;
    @JsonProperty("GETrailers")
    private List<GETrailers> geTrailers;
}