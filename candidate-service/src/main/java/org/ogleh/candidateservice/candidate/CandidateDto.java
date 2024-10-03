package org.ogleh.candidateservice.candidate;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CandidateDto(
        int id,
        String fullName,
        String nickName,
        LocalDate dateOfBirth,
        String placeOfBrith,
        String profilePicture,
        String partyName,
        String partyProfile
) {
}
