package org.ogleh.candidateservice.party;

import lombok.RequiredArgsConstructor;
import org.ogleh.candidateservice.candidate.Candidate;
import org.ogleh.candidateservice.candidate.CandidateRepository;
import org.ogleh.candidateservice.exceptions.ApiException;
import org.ogleh.candidateservice.util.FilePath;
import org.ogleh.candidateservice.util.FileStorage;
import org.ogleh.candidateservice.util.ImageType;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.ogleh.candidateservice.util.BuildUri.changeImageToUriLocator;

@Service
@RequiredArgsConstructor
public class PartyService {
    private final PartyRepository partyRepository;
    private final CandidateRepository candidateRepository;
    private final String PARTY_ROOT_FOLDER = FilePath.PARTY_DIR_PATH.getPath();

    public PartyDto createParty(PartyRequest request, MultipartFile profile) {
        try {
            Party party = PartyMapper.toParty(request);
            if (profile != null && !profile.isEmpty()) {
                String partyFlag = FileStorage.addFile(profile, PARTY_ROOT_FOLDER);
                party.setPartyFlag(partyFlag);
            }
            partyRepository.save(party);
            return PartyMapper.toPartyDto(party);
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    public PartyDto updateParty(int partyId, PartyRequest request) {
        try {
            Party party = partyRepository.findById(partyId).orElseThrow(() -> new ApiException("Party with id " + partyId + " does not exist"));
            party.setPartyName(request.partyName());
            party.setPartyDescription(request.description());
            partyRepository.save(party);
            return PartyMapper.toPartyDto(party);
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Transactional
    public PartyDto setPartyCandidate(int partyId, int candidateID) {
        try {
            // FIXME: 03/10/24 Insha Allah jpa query ku update garee party candidate ka adoo isticmalaya update query

            Party party = partyRepository.findById(partyId).orElseThrow(() -> new ApiException("Party with id " + partyId + " does not exist"));
            Candidate candidate = candidateRepository.findById(candidateID).orElseThrow(() -> new ApiException("Candidate with id " + candidateID + " does not exist"));

            party.setCandidate(candidate);

            return PartyMapper.toPartyDto(partyRepository.save(party));
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    public String updateProfile(int partyId, MultipartFile profile) {
        try {
            String flag = partyRepository.partyProfile(partyId).orElseThrow(() -> new ApiException("Party with id " + partyId + " does not exist"));

            if (flag.equals("NAN") && !profile.isEmpty()) {
                String partyFlag = FileStorage.addFile(profile, PARTY_ROOT_FOLDER);
                partyRepository.updatePartyFlag(partyId, partyFlag);
                return changeImageToUriLocator(ImageType.PARTY_FLAG.getType(), partyFlag);
            } else {
                String newFlag = FileStorage.updateImage(flag, PARTY_ROOT_FOLDER, profile);
                partyRepository.updatePartyFlag(partyId, newFlag);
                return changeImageToUriLocator(ImageType.PARTY_FLAG.getType(), newFlag);
            }
        } catch (IOException e) {
            throw new ApiException("Error while updating party flag image: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new ApiException(e.getMessage(), e);
        }
    }

    public PartyDto getParty(int partyId) {
        try {
            Party party = partyRepository.findById(partyId).orElseThrow(() -> new ApiException("Party with id " + partyId + " does not exist"));
            return PartyMapper.toPartyDto(party);
        } catch (DataAccessException e) {
            throw new ApiException(e.getMessage());
        }
    }

    public List<PartyDto> getAllParties() {
        return partyRepository.findAll().stream().map(PartyMapper::toPartyDto).toList();
    }
}
