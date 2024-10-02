package org.ogleh.candidateservice.campaing_images;

import lombok.RequiredArgsConstructor;
import org.ogleh.candidateservice.exceptions.ApiException;
import org.ogleh.candidateservice.party.Party;
import org.ogleh.candidateservice.party.PartyRepository;
import org.ogleh.candidateservice.util.BuildUri;
import org.ogleh.candidateservice.util.FilePath;
import org.ogleh.candidateservice.util.FileStorage;
import org.ogleh.candidateservice.util.ImageType;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.ogleh.candidateservice.util.BuildUri.changeImageToUriLocator;

@Service
@RequiredArgsConstructor
public class CampaignImagesService {
    private final CampaignImagesRepository campaignImagesRepository;
    private final PartyRepository partyRepository;
    private final String CAM_DIR = FilePath.CAMPAIGN_DIR_PATH.getPath();


    public String addCampaignImage(int partyId, MultipartFile image) {
        try {
            Party party = partyRepository.findById(partyId).orElseThrow(() -> new ApiException("party with id " + partyId + " not found"));

            if (image != null && !image.isEmpty()) {
                String campaignPhotUrl = FileStorage.addFile(image, CAM_DIR);

                CampaignImages camImage = CampaignImages
                        .builder()
                        .campaignImageUrl(campaignPhotUrl)
                        .build();
                party.getCampaignImages().add(camImage);
                partyRepository.save(party);
                return changeImageToUriLocator(ImageType.CAMPAIGN_IMAGE.getType(), campaignPhotUrl);
            }

            return null;
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }

    }

    public String deleteCampaignImage(int campaignImageId) {
        try {
            CampaignImages campaignImages = findCampaignImageById(campaignImageId);
            String deletePhoto = FileStorage.deleteImage(campaignImages.getCampaignImageUrl(), CAM_DIR);
            campaignImagesRepository.deleteById(campaignImageId);
            return deletePhoto;
        } catch (DataAccessException e) {
            throw new ApiException(e.getMessage());
        } catch (IOException e) {
            throw new ApiException("Error occurred while deleting image " + campaignImageId);
        } catch (Exception e) {
            throw new ApiException("Error occur ", e);
        }
    }




    public Resource getCampaignImage(String campaignImageUrl) {
        try {
//            CampaignImages campaignImages = findCampaignImageById(campaignImageId);
            final Path rootLocation = Paths.get(CAM_DIR);
            Path path = rootLocation.resolve(campaignImageUrl);
            return new UrlResource(path.toUri());
        } catch (DataAccessException e) {
            throw new ApiException(e.getMessage());
        } catch (MalformedURLException e) {
            throw new ApiException("Resource Malformed URL ", e);
        }
    }


    private CampaignImages findCampaignImageById(int campaignImageId) {
        return campaignImagesRepository
                .findById(campaignImageId).orElseThrow(() -> new ApiException("campaign image with id " + campaignImageId + " not found"));
    }
}
