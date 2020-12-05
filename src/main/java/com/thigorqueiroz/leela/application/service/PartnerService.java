package com.thigorqueiroz.leela.application.service;

import com.thigorqueiroz.leela.application.command.CreatePartnerCommand;
import com.thigorqueiroz.leela.domain.model.common.BusinessException;
import com.thigorqueiroz.leela.domain.model.partner.Partner;
import com.thigorqueiroz.leela.domain.model.partner.PartnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);

    @Autowired
    private PartnerRepository partnerRepository;

    @Transactional
    public Partner create(CreatePartnerCommand command) {
        logger.info("Trying to create a partner with EMAIL: '{}'" , command.email);
        partnerRepository.findByEmail(command.email)
                .ifPresent(f -> {
                    //TODO: send campaigns associated to request
                    throw new BusinessException("Partner is already created!");
                });
        var partner = new Partner(command.name, command.email, command.birthday, command.heartTeamId);
        return this.partnerRepository.save(partner);
    }

    @Transactional(readOnly = true)
    public List<Partner> findAll() {
        List<Partner> partners = new ArrayList<>();
        partnerRepository.findAll().forEach(partners::add);
        return partners;
    }
}
