package org.ogleh.candidateservice.campaing_images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CampaignImagesRepository extends JpaRepository<CampaignImages, Integer> {

//    @Modifying
//    @Transactional
//    @Query("update INTO CampaignImages(c.campaignImage,c.partyId) c VALUES(:=campaignPhoto,) ")
//    int insert(@Param("campaignPhoto") String campaignPhoto, @Param("partyId") int partyId);
}
