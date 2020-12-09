package com.thigorqueiroz.leela.port.adapter.resource;

import com.thigorqueiroz.leela.application.command.CreatePartnerCommand;
import com.thigorqueiroz.leela.application.service.PartnerService;
import com.thigorqueiroz.leela.domain.model.partner.Partner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partners")
public class PartnerResource {
    private static final Logger Logger = LoggerFactory.getLogger(PartnerResource.class);

    private final PartnerService partnerService;

    public PartnerResource(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping
    @Operation(summary = "Find all Partners",
            description = "Find all Partners", tags = {"Partners"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Partner.class))))})
    public List<Partner> findAll() {
        Logger.debug("Trying to find all partners");
        return partnerService.findAll();
    }

    @Operation(summary = "Create a new Partners",
            description = "Create a new Partners", tags = {"Partners"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Partner.class))))})
    @PostMapping
    public ResponseEntity<Partner> create(@RequestBody CreatePartnerCommand command) {
        return new ResponseEntity<>(partnerService.create(command), HttpStatus.CREATED);
    }
}