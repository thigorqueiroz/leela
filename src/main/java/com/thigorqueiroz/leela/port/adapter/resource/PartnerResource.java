package com.thigorqueiroz.leela.port.adapter.resource;

import com.thigorqueiroz.leela.application.command.CreatePartnerCommand;
import com.thigorqueiroz.leela.application.service.PartnerService;
import com.thigorqueiroz.leela.domain.model.partner.Partner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partners")
public class PartnerResource {
    private static final Logger Logger = LoggerFactory.getLogger(PartnerResource.class);

    @Autowired
    private PartnerService partnerService;

    @GetMapping
    public List<Partner> findAll() {
        Logger.debug("find all partners");
        return partnerService.findAll();
    }

    @PostMapping
    public Partner create(@RequestBody CreatePartnerCommand command) {
        return partnerService.create(command);
    }
}
