package org.ogleh.candidateservice.campaing_images;

import lombok.Builder;

@Builder
public record CampaignImageDtp(int imageId, String imageUrl) {
}
