package org.ogleh.candidateservice.party;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.ogleh.candidateservice.campaing_images.CampaignImageDtp;

import java.util.List;

@Builder
public record PartyDto(int partyId,
                       String partyName,
                       String partyDescription,
                       String partyFlag,
                       int candidateId,
                       String candidateName,
                       String candidateProfile,
                       String candidateNickName,
                       List<CampaignImageDtp> campaignImages
) {
}
