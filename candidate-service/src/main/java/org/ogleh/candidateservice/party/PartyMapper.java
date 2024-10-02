package org.ogleh.candidateservice.party;

import org.ogleh.candidateservice.campaing_images.CampaignImageMapper;
import org.ogleh.candidateservice.util.ImageType;

import static org.ogleh.candidateservice.util.BuildUri.changeImageToUriLocator;

public class PartyMapper {

    public static PartyDto toPartyDto(Party party) {
        return PartyDto.builder()
                .partyId(party.getPartyId())
                .partyName(party.getPartyName())
                .partyDescription(party.getPartyDescription())
                .partyFlag(party.getPartyFlag() != null && !party.getPartyFlag().equals("NAN")
                        ? changeImageToUriLocator(ImageType.PARTY_FLAG.getType(), party.getPartyFlag())
                        : party.getPartyFlag())
                .campaignImages(party.getCampaignImages().stream().map(CampaignImageMapper::toCampaignImagesDto).toList())
                .build();
    }

    public static Party toParty(PartyRequest request) {
        return Party.builder()
                .partyName(request.partyName())
                .partyDescription(request.description())
                .build();
    }
}
