package org.ogleh.candidateservice.candidate;

import org.ogleh.candidateservice.util.BuildUri;
import org.ogleh.candidateservice.util.ImageType;

import static org.ogleh.candidateservice.util.BuildUri.changeImageToUriLocator;

public class CandidateMapper {

    public static Candidate toCandidate(CandidateRequest request) {
        return Candidate.builder()
                .dateOfBirth(request.dateOfBirth())
                .fullName(request.fullName())
                .nickName(request.nickName())
                .placeOfBrith(request.placeOfBrith())
                .build();
    }

    public static CandidateDto toDto(Candidate candidate) {
        return CandidateDto.builder()
                .id(candidate.getId())
                .dateOfBirth(candidate.getDateOfBirth())
                .fullName(candidate.getFullName())
                .nickName(candidate.getNickName() == null ? "NAN" : candidate.getNickName())
                .partyName(candidate.getParty() != null ? candidate.getParty().getPartyName() : "NAN")
                .profilePicture(candidate.getProfilePicture() != null ? changeImageToUriLocator(ImageType.CANDIDATE_PROFILE.getType(), candidate.getProfilePicture()) : "NAN")
                .partyProfile(candidate.getParty() != null ? changeImageToUriLocator(ImageType.PARTY_FLAG.getType(), candidate.getParty().getPartyFlag()) : "NAN")
                .placeOfBrith(candidate.getPlaceOfBrith())
                .build();
    }
}
