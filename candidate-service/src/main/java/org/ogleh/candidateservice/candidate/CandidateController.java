package org.ogleh.candidateservice.candidate;

import lombok.RequiredArgsConstructor;
import org.ogleh.candidateservice.party.PartyRequest;
import org.ogleh.candidateservice.util.Response;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/candidate")
public class CandidateController {
    private final CandidateService candidateService;

    @GetMapping("/{candidateId}")
    public ResponseEntity<?> getCandidate(@PathVariable("candidateId") int candidateId) {
        return Response.successResponse(candidateService.getCandidate(candidateId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCandidate(@ModelAttribute CandidateRequest request, @RequestParam("profile") MultipartFile profile) {
        return Response.successResponse(candidateService.addCandidate(request, profile));
    }

    @PutMapping("/update/{candidateId}")
    public ResponseEntity<?> updateCandidate(@PathVariable int candidateId, @RequestBody CandidateRequest request) {
        return Response.successResponse(candidateService.updateCandidate(candidateId, request));
    }

    @DeleteMapping("/delete/{candidateId}")
    public ResponseEntity<?> deleteCandidate(@PathVariable int candidateId) {
        return Response.successResponse(candidateService.deleteCandidate(candidateId));
    }

    @GetMapping("/image/profile/{profileUrl}")
    public ResponseEntity<Resource> getCandidateProfile(@PathVariable String profileUrl) {
        Resource resource = candidateService.getCandidateProfile(profileUrl);
        if (resource.exists()) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

//    @PutMapping("/change-party/{candidateId}")
//    public ResponseEntity<?> changeParty(@PathVariable int candidateId, @RequestParam int partyId) {
//        return Response.successResponse(candidateService.changeCandidate(candidateId, partyId));
//    }
}
