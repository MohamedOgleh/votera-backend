package org.ogleh.candidateservice.party;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
