package org.ogleh.candidateservice.campaing_images;

import org.ogleh.candidateservice.util.BuildUri;
import org.ogleh.candidateservice.util.ImageType;

public class CampaignImageMapper {

    public static CampaignImageDtp toCampaignImagesDto(CampaignImages campaignImages) {
        return CampaignImageDtp.builder()
                .imageId(campaignImages.getCampaignImageId())
                .imageUrl(BuildUri.changeImageToUriLocator(ImageType.CAMPAIGN_IMAGE.getType(),
                        campaignImages.getCampaignImageUrl()))
                .build();
    }
}
