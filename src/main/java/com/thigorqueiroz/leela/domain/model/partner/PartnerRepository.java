package com.thigorqueiroz.leela.domain.model.partner;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, UUID> {
    Optional<Partner> findByEmail(String name);
}
