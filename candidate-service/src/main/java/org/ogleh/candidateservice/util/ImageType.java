package org.ogleh.candidateservice.util;

public enum ImageType {
    CANDIDATE_PROFILE("image/profile/"),
    PARTY_FLAG("image/flag/"),
    CAMPAIGN_IMAGE("image/campaign/");

    final String type;

    ImageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
