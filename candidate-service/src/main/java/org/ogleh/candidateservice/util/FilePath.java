package org.ogleh.candidateservice.util;

public enum FilePath {

    PARTY_DIR_PATH("/Users/mohamedogleh/Documents/votera/candidate-service/src/main/resources/assets/party"),
    CANDIDATE_DIR_PATH("/Users/mohamedogleh/Documents/votera/candidate-service/src/main/resources/assets/candidate");
    private final String path;

    FilePath(String path) {
        this.path = path;
    }

    public java.lang.String getPath() {
        return path;
    }
}
