package com.thigorqueiroz.leela.application.service;

import com.thigorqueiroz.leela.application.command.CreateCampaignCommand;
import com.thigorqueiroz.leela.application.command.PartialUpdateCampaignCommand;
import com.thigorqueiroz.leela.domain.model.campaign.Campaign;
import com.thigorqueiroz.leela.domain.model.campaign.CampaignRepository;
import com.thigorqueiroz.leela.domain.model.common.BusinessException;
import com.thigorqueiroz.leela.domain.model.common.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CampaignService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignService.class);

    @Autowired
    private CampaignRepository campaignRepository;

    public Campaign findById(UUID id) {
        return campaignRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Campaign"));
    }

    @Transactional
    public void delete(UUID id) {
        campaignRepository.findById(id)
                .ifPresent(f -> campaignRepository.delete(f));
    }

    @Transactional
    public Campaign partialUpdate(PartialUpdateCampaignCommand command) {
        var campaign = campaignRepository.findById(command.id).map(
                c -> new Campaign(c, command.name)
        ).orElseThrow(() -> new EntityNotFoundException("Campaign", command.id));
        return campaignRepository.save(campaign);
    }

    @Transactional
    public Campaign create(CreateCampaignCommand command) {
        campaignRepository.findByName(command.name)
                .ifPresent(f -> {
                    throw new BusinessException("Campaign is already created!");
                });
        var campaign = new Campaign(command.name);
        return this.campaignRepository.save(campaign);
    }

    @Transactional(readOnly = true)
    public List<Campaign> findAll() {
        List<Campaign> campaigns = new ArrayList<>();
        campaignRepository.findAll().forEach(campaigns::add);
        return campaigns;
    }
}
