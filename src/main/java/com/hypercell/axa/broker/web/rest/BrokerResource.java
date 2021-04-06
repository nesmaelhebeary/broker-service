package com.hypercell.axa.broker.web.rest;

import com.hypercell.axa.broker.domain.Broker;
import com.hypercell.axa.broker.repository.BrokerRepository;
import com.hypercell.axa.broker.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.hypercell.axa.broker.domain.Broker}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BrokerResource {

    private final Logger log = LoggerFactory.getLogger(BrokerResource.class);

    private static final String ENTITY_NAME = "brokerServiceBroker";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BrokerRepository brokerRepository;

    public BrokerResource(BrokerRepository brokerRepository) {
        this.brokerRepository = brokerRepository;
    }

    /**
     * {@code POST  /brokers} : Create a new broker.
     *
     * @param broker the broker to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new broker, or with status {@code 400 (Bad Request)} if the broker has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/brokers")
    public ResponseEntity<Broker> createBroker(@RequestBody Broker broker) throws URISyntaxException {
        log.debug("REST request to save Broker : {}", broker);
        if (broker.getId() != null) {
            throw new BadRequestAlertException("A new broker cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Broker result = brokerRepository.save(broker);
        return ResponseEntity
            .created(new URI("/api/brokers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /brokers/:id} : Updates an existing broker.
     *
     * @param id the id of the broker to save.
     * @param broker the broker to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated broker,
     * or with status {@code 400 (Bad Request)} if the broker is not valid,
     * or with status {@code 500 (Internal Server Error)} if the broker couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/brokers/{id}")
    public ResponseEntity<Broker> updateBroker(@PathVariable(value = "id", required = false) final Long id, @RequestBody Broker broker)
        throws URISyntaxException {
        log.debug("REST request to update Broker : {}, {}", id, broker);
        if (broker.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, broker.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!brokerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Broker result = brokerRepository.save(broker);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, broker.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /brokers/:id} : Partial updates given fields of an existing broker, field will ignore if it is null
     *
     * @param id the id of the broker to save.
     * @param broker the broker to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated broker,
     * or with status {@code 400 (Bad Request)} if the broker is not valid,
     * or with status {@code 404 (Not Found)} if the broker is not found,
     * or with status {@code 500 (Internal Server Error)} if the broker couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/brokers/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Broker> partialUpdateBroker(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Broker broker
    ) throws URISyntaxException {
        log.debug("REST request to partial update Broker partially : {}, {}", id, broker);
        if (broker.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, broker.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!brokerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Broker> result = brokerRepository
            .findById(broker.getId())
            .map(
                existingBroker -> {
                    if (broker.getName() != null) {
                        existingBroker.setName(broker.getName());
                    }
                    if (broker.getAddress() != null) {
                        existingBroker.setAddress(broker.getAddress());
                    }
                    if (broker.getCountry() != null) {
                        existingBroker.setCountry(broker.getCountry());
                    }
                    if (broker.getCity() != null) {
                        existingBroker.setCity(broker.getCity());
                    }
                    if (broker.getWhtAcceptance() != null) {
                        existingBroker.setWhtAcceptance(broker.getWhtAcceptance());
                    }
                    if (broker.getRegion() != null) {
                        existingBroker.setRegion(broker.getRegion());
                    }
                    if (broker.getContactName() != null) {
                        existingBroker.setContactName(broker.getContactName());
                    }
                    if (broker.getContactDial() != null) {
                        existingBroker.setContactDial(broker.getContactDial());
                    }
                    if (broker.getContactEmail() != null) {
                        existingBroker.setContactEmail(broker.getContactEmail());
                    }
                    if (broker.getCreatedDate() != null) {
                        existingBroker.setCreatedDate(broker.getCreatedDate());
                    }
                    if (broker.getStatus() != null) {
                        existingBroker.setStatus(broker.getStatus());
                    }
                    if (broker.getCreatedBy() != null) {
                        existingBroker.setCreatedBy(broker.getCreatedBy());
                    }
                    if (broker.getTaxRegisteryNumber() != null) {
                        existingBroker.setTaxRegisteryNumber(broker.getTaxRegisteryNumber());
                    }
                    if (broker.getTaxAuthority() != null) {
                        existingBroker.setTaxAuthority(broker.getTaxAuthority());
                    }
                    if (broker.getValidFrom() != null) {
                        existingBroker.setValidFrom(broker.getValidFrom());
                    }
                    if (broker.getValidTo() != null) {
                        existingBroker.setValidTo(broker.getValidTo());
                    }
                    if (broker.getTaxType() != null) {
                        existingBroker.setTaxType(broker.getTaxType());
                    }
                    if (broker.getTaxValue() != null) {
                        existingBroker.setTaxValue(broker.getTaxValue());
                    }
                    if (broker.getRegulatory() != null) {
                        existingBroker.setRegulatory(broker.getRegulatory());
                    }
                    if (broker.getFraCode() != null) {
                        existingBroker.setFraCode(broker.getFraCode());
                    }
                    if (broker.getLiscenceValidFrom() != null) {
                        existingBroker.setLiscenceValidFrom(broker.getLiscenceValidFrom());
                    }
                    if (broker.getLiscenceValidTo() != null) {
                        existingBroker.setLiscenceValidTo(broker.getLiscenceValidTo());
                    }
                    if (broker.getBrokerNameAr() != null) {
                        existingBroker.setBrokerNameAr(broker.getBrokerNameAr());
                    }
                    if (broker.getPaymentType() != null) {
                        existingBroker.setPaymentType(broker.getPaymentType());
                    }
                    if (broker.getPaymentMode() != null) {
                        existingBroker.setPaymentMode(broker.getPaymentMode());
                    }
                    if (broker.getBankName() != null) {
                        existingBroker.setBankName(broker.getBankName());
                    }
                    if (broker.getAccountName() != null) {
                        existingBroker.setAccountName(broker.getAccountName());
                    }
                    if (broker.getBranch() != null) {
                        existingBroker.setBranch(broker.getBranch());
                    }
                    if (broker.getBankAccountNo() != null) {
                        existingBroker.setBankAccountNo(broker.getBankAccountNo());
                    }
                    if (broker.getBankAddress() != null) {
                        existingBroker.setBankAddress(broker.getBankAddress());
                    }
                    if (broker.getCurrency() != null) {
                        existingBroker.setCurrency(broker.getCurrency());
                    }
                    if (broker.getSwiftCode() != null) {
                        existingBroker.setSwiftCode(broker.getSwiftCode());
                    }
                    if (broker.getiBANNO() != null) {
                        existingBroker.setiBANNO(broker.getiBANNO());
                    }

                    return existingBroker;
                }
            )
            .map(brokerRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, broker.getId().toString())
        );
    }

    /**
     * {@code GET  /brokers} : get all the brokers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of brokers in body.
     */
    @GetMapping("/brokers")
    public List<Broker> getAllBrokers() {
        log.debug("REST request to get all Brokers");
        return brokerRepository.findAll();
    }

    /**
     * {@code GET  /brokers/:id} : get the "id" broker.
     *
     * @param id the id of the broker to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the broker, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/brokers/{id}")
    public ResponseEntity<Broker> getBroker(@PathVariable Long id) {
        log.debug("REST request to get Broker : {}", id);
        Optional<Broker> broker = brokerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(broker);
    }

    /**
     * {@code DELETE  /brokers/:id} : delete the "id" broker.
     *
     * @param id the id of the broker to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/brokers/{id}")
    public ResponseEntity<Void> deleteBroker(@PathVariable Long id) {
        log.debug("REST request to delete Broker : {}", id);
        brokerRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
