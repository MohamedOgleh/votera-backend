package org.ogleh.candidateservice.campaing_images;

import lombok.RequiredArgsConstructor;
import org.ogleh.candidateservice.util.Response;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image/campaign")
public class CampaignImagesController {
    private final CampaignImagesService campaignImagesService;

    @PostMapping("/add/{partyId}")
    public ResponseEntity<?> addCampaignImage(@PathVariable("partyId") int partyId, @RequestParam("image") MultipartFile image) {
        return Response.successResponse(campaignImagesService.addCampaignImage(partyId, image));
    }

    @DeleteMapping("/delete/{campaignImageId}")
    public ResponseEntity<?> addCampaignImage(@PathVariable("campaignImageId") int campaignImageId) {
        return Response.successResponse(campaignImagesService.deleteCampaignImage(campaignImageId));
    }

    @GetMapping("/{campaignImageUrl}")
    public ResponseEntity<Resource> getCampaignImage(@PathVariable String campaignImageUrl) {
        Resource resource = campaignImagesService.getCampaignImage(campaignImageUrl);
        if (resource.exists()) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
