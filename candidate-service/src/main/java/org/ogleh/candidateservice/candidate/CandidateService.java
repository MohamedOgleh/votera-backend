package org.ogleh.candidateservice.candidate;

import lombok.RequiredArgsConstructor;
import org.ogleh.candidateservice.exceptions.ApiException;
import org.ogleh.candidateservice.party.Party;
import org.ogleh.candidateservice.party.PartyRepository;
import org.ogleh.candidateservice.util.FilePath;
import org.ogleh.candidateservice.util.FileStorage;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;
//    private final PartyRepository partyRepository;
    private final String CANDIDATE_DIR = FilePath.CANDIDATE_DIR_PATH.getPath();

    public CandidateDto getCandidate(int candidateId) {
        try {
            Candidate candidate = findCandidate(candidateId);
            return CandidateMapper.toDto(candidate);
        } catch (DataAccessException e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Transactional
    public CandidateDto addCandidate(CandidateRequest request, MultipartFile profile) {
        try {
//            Party party = partyRepository.findById(request.partyId()).orElseThrow(() -> new ApiException("party not found"));

            Candidate candidate = CandidateMapper.toCandidate(request);
//            candidate.setParty(party);

            if (profile != null && !profile.isEmpty()) {
                String profileUrl = FileStorage.addFile(profile, CANDIDATE_DIR);
                candidate.setProfilePicture(profileUrl);
            }

            candidateRepository.save(candidate);
            return CandidateMapper.toDto(candidate);
        } catch (DataAccessException e) {
            throw new ApiException(e.getMessage());
        } catch (IOException e) {
            throw new ApiException("Error occurred when saving file", e);
        }

    }

    public CandidateDto updateCandidate(int candidateId, CandidateRequest request) {
        try {
            Candidate candidate = findCandidate(candidateId);
            candidate.setNickName(request.nickName());
            candidate.setFullName(request.fullName());
            candidate.setPlaceOfBrith(request.placeOfBrith());
            candidate.setDateOfBirth(request.dateOfBirth());
            return CandidateMapper.toDto(candidateRepository.save(candidate));
        } catch (DataAccessException e) {
            throw new ApiException(e.getMessage());
        }
    }


    public String deleteCandidate(int candidateId) {
        try {
            candidateRepository.deleteById(candidateId);
            return "Deleted candidate" + candidateId;
        } catch (DataAccessException e) {
            throw new ApiException(e.getMessage());
        }
    }


    public Resource getCandidateProfile(String profileUrl) {
        try {
            Path path = Paths.get(CANDIDATE_DIR).resolve(profileUrl);
            return new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new ApiException("Image reading error", e);
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    private Candidate findCandidate(int candidateId) {
        return candidateRepository.findById(candidateId).orElseThrow(() -> new ApiException("candidate not found"));
    }


}
