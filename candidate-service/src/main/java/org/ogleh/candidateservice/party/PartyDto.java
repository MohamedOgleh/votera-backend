package org.ogleh.candidateservice.party;

import lombok.Builder;

@Builder
public record PartyDto(int partyId,
                       String partyName,
                       String partyDescription,
                       String partyFlag) {
}
