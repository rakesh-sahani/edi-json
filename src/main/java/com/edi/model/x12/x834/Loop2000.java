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
@JsonPropertyOrder({"INS_MemberLevelDetail", "AllREF", "DTP_MemberLevelDates", "AllNM1", "Loop2200", "Loop2300", "LoopLS"})
public class Loop2000 implements Serializable {
    @JsonProperty("INS_MemberLevelDetail")
    private INS_MemberLevelDetail insMemberLevelDetail;
    @JsonProperty("AllREF")
    private AllREF allREF;
    @JsonProperty("DTP_MemberLevelDates")
    private List<DTP_MemberLevelDates> dtpMemberLevelDates;
    @JsonProperty("AllNM1")
    private AllNM1 allNM1;
    @JsonProperty("Loop2200")
    private List<Loop2200> loop2200;
    @JsonProperty("Loop2300")
    private List<Loop2300> loop2300;
    @JsonProperty("LoopLS")
    private LoopLS loopLS;
}
