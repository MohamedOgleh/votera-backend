package org.ogleh.candidateservice.candidate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ogleh.candidateservice.party.Party;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String nickName;
    private LocalDate dateOfBirth;
    private String placeOfBrith;
    private String profilePicture;
    @OneToOne(mappedBy = "candidate")
    private Party party;
}
