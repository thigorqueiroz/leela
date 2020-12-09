package com.thigorqueiroz.leela.application.service;

import com.thigorqueiroz.leela.application.command.CreatePartnerCommand;
import com.thigorqueiroz.leela.application.command.SubscribeCampaignCommand;
import com.thigorqueiroz.leela.domain.model.common.AggregateRootWithIdentifierAsUUID;
import com.thigorqueiroz.leela.domain.model.common.BusinessException;
import com.thigorqueiroz.leela.domain.model.partner.Partner;
import com.thigorqueiroz.leela.domain.model.partner.PartnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PartnerService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);

    private final PartnerRepository partnerRepository;
    private final JdbcAggregateTemplate jdbcAggregateTemplate;

    public PartnerService(PartnerRepository partnerRepository, JdbcAggregateTemplate jdbcAggregateTemplate) {
        this.partnerRepository = partnerRepository;
        this.jdbcAggregateTemplate = jdbcAggregateTemplate;
    }

    @Transactional
    public Partner create(CreatePartnerCommand command) {
        logger.info("Trying to create a partner with EMAIL: '{}'" , command.email);
        partnerRepository.findByEmail(command.email)
                .ifPresent(f -> {
                    //TODO: send campaigns associated to request
                    throw new BusinessException("Partner is already created!");
                });
        var partner = new Partner(command.name, command.email, command.birthday, command.heartTeamId);
        //this.partnerRepository.insert(partner.getId(), command.name, command.email, command.birthday, command.heartTeamId, OffsetDateTime.now(), OffsetDateTime.now());
        //return partner;
        return partnerRepository.save(partner);
    }

    @Transactional(readOnly = true)
    public List<Partner> findAll() {
        List<Partner> partners = new ArrayList<>();
        partnerRepository.findAll().forEach(partners::add);
        return partners;
    }

    @Transactional
    public void subscribeCampaign(SubscribeCampaignCommand command) {
        logger.info("Subscribing campaigns '{}' " , command.campaigns);
        var partner = partnerRepository.findByEmail(command.partnerEmail).map(
                AggregateRootWithIdentifierAsUUID::getId
        ).orElseThrow(
                () -> new BusinessException("Partner:  not found while trying to subscribe campaigns")
        );
         Streamable.of(command.campaigns).stream()
                .forEach(campaing ->
                        partnerRepository.subscribeCampaigns(
                                UUID.randomUUID(),
                                partner, campaing, OffsetDateTime.now(), OffsetDateTime.now()));
    }
}
