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
@JsonPropertyOrder({"REF_SubscriberIdentifier", "REF_MemberPolicyNumber", "REF_MemberSupplementalIdentifier"})
public class AllREF implements Serializable {
    @JsonProperty("REF_SubscriberIdentifier")
    private REF_SubscriberIdentifier refSubscriberIdentifier;
    @JsonProperty("REF_MemberPolicyNumber")
    private REF_MemberPolicyNumber refMemberPolicyNumber;
    @JsonProperty("REF_MemberSupplementalIdentifier")
    private List<REF_MemberSupplementalIdentifier> refMemberSupplementalIdentifier;
}