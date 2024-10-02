package org.ogleh.candidateservice.party;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ogleh.candidateservice.campaing_images.CampaignImages;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partyId;
    private String partyName;
    private String partyDescription;
    private String partyFlag;
    @JoinColumn(name = "party_id")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CampaignImages> campaignImages;
}
