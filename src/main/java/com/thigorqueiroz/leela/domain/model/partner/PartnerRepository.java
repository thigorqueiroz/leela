package com.thigorqueiroz.leela.domain.model.partner;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, UUID> {
    Optional<Partner> findByEmail(String name);

    @Query("INSERT INTO partner_campaign (id, partner_id, campaign_id, created_at, updated_at)" +
            "VALUES(:id, :partnerId , :campaignId, :createdAt, :updatedAt)")
    @Modifying
    void subscribeCampaigns(@Param("id") UUID id,
                            @Param("partnerId") UUID partnerId,
                            @Param("campaignId") UUID campaignId,
                            @Param("createdAt") OffsetDateTime createdAt,
                            @Param("updatedAt") OffsetDateTime updatedAt);

   /* @Query("INSERT INTO partner (id, name, email, birth_day, heart_team_id, created_at, updated_at)" +
                        "VALUES(:id, :name, :email, :birthDay, :heartTeamId, :createdAt, :updatedAt)")
    Partner insert (@Param("id") UUID id,
                 @Param("name") String name,
                 @Param("email") String email,
                 @Param("birthDay") String birthDay,
                 @Param("heartTeamId") UUID heartTeamId,
                 @Param("createdAt") OffsetDateTime createdAt,
                 @Param("updatedAt") OffsetDateTime updatedAt);*/
}
