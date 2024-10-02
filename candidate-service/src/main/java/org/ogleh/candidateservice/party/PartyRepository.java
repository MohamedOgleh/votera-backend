package org.ogleh.candidateservice.party;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PartyRepository extends JpaRepository<Party, Integer> {
    @Query("SELECT p.partyFlag FROM Party p WHERE p.partyId=:partyId")
    Optional<String> partyProfile(@Param("partyId") int partyId);

    @Transactional
    @Modifying
    @Query("UPDATE Party p SET p.partyFlag=:flag WHERE p.partyId=:partyId")
    int updatePartyFlag(@Param("partyId") int partyId,@Param("flag") String partyFlag);
}
