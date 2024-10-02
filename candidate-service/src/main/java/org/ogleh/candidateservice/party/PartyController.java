package org.ogleh.candidateservice.party;

import lombok.RequiredArgsConstructor;
import org.ogleh.candidateservice.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/party")
public class PartyController {
    private final PartyService partyService;

    @PostMapping("/add")
    public ResponseEntity<?> createParty(@ModelAttribute PartyRequest request, @RequestParam("profile") MultipartFile profile) {
        return Response.successResponse((partyService.createParty(request, profile)));
    }

    @PutMapping("/{partyId}")
    public ResponseEntity<?> updateParty(@PathVariable int partyId, @RequestBody PartyRequest request) {
        return Response.successResponse(partyService.updateParty(partyId, request));
    }

    @PutMapping("/add/profile/{partyId}")
    public ResponseEntity<?> addProfile(@PathVariable int partyId, @RequestParam("profile") MultipartFile profile) {
        return Response.successResponse(partyService.updateProfile(partyId, profile));
    }

    @GetMapping("/{partyId}")
    public ResponseEntity<?> getParty(@PathVariable int partyId) {
        return Response.successResponse(partyService.getParty(partyId));
    }

    @GetMapping("/")
    public ResponseEntity<?> getParties() {
        return Response.successResponse(partyService.getAllParties());
    }

}
