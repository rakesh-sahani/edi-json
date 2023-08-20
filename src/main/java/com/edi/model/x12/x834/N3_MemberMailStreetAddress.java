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
@JsonPropertyOrder({"ResponseContactAddressLine_01", "ResponseContactAddressLine_02"})
public class N3_MemberMailStreetAddress implements Serializable {
    @JsonProperty("ResponseContactAddressLine_01")
    private String ResponseContactAddressLine_01;
    @JsonProperty("ResponseContactAddressLine_02")
    private String ResponseContactAddressLine_02;
}