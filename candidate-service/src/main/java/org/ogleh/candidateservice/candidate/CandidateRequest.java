package org.ogleh.candidateservice.candidate;

import java.time.LocalDate;

public record CandidateRequest(String fullName,
                               String nickName,
                               LocalDate dateOfBirth,
                               String placeOfBrith,
                               int partyId) {
}
