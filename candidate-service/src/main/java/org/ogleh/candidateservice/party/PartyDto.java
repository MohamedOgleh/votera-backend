package org.ogleh.candidateservice.party;

import lombok.Builder;
import org.ogleh.candidateservice.campaing_images.CampaignImageDtp;

import java.util.List;

@Builder
public record PartyDto(int partyId,
                       String partyName,
                       String partyDescription,
                       String partyFlag,
                       List<CampaignImageDtp> campaignImages) {
}
