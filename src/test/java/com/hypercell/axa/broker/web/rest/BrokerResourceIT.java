package com.hypercell.axa.broker.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.hypercell.axa.broker.IntegrationTest;
import com.hypercell.axa.broker.domain.Broker;
import com.hypercell.axa.broker.domain.enumeration.BrokerStatus;
import com.hypercell.axa.broker.domain.enumeration.PaymentMode;
import com.hypercell.axa.broker.domain.enumeration.PaymentType;
import com.hypercell.axa.broker.domain.enumeration.TaxType;
import com.hypercell.axa.broker.repository.BrokerRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BrokerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BrokerResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_WHT_ACCEPTANCE = false;
    private static final Boolean UPDATED_WHT_ACCEPTANCE = true;

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_DIAL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_DIAL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_EMAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BrokerStatus DEFAULT_STATUS = BrokerStatus.New;
    private static final BrokerStatus UPDATED_STATUS = BrokerStatus.Active;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_REGISTERY_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TAX_REGISTERY_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_AUTHORITY = "AAAAAAAAAA";
    private static final String UPDATED_TAX_AUTHORITY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_VALID_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VALID_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_VALID_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VALID_TO = LocalDate.now(ZoneId.systemDefault());

    private static final TaxType DEFAULT_TAX_TYPE = TaxType.Percentage;
    private static final TaxType UPDATED_TAX_TYPE = TaxType.FlatRate;

    private static final Double DEFAULT_TAX_VALUE = 1D;
    private static final Double UPDATED_TAX_VALUE = 2D;

    private static final String DEFAULT_REGULATORY = "AAAAAAAAAA";
    private static final String UPDATED_REGULATORY = "BBBBBBBBBB";

    private static final String DEFAULT_FRA_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FRA_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LISCENCE_VALID_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LISCENCE_VALID_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LISCENCE_VALID_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LISCENCE_VALID_TO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BROKER_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_BROKER_NAME_AR = "BBBBBBBBBB";

    private static final PaymentType DEFAULT_PAYMENT_TYPE = PaymentType.Receipt;
    private static final PaymentType UPDATED_PAYMENT_TYPE = PaymentType.Payment;

    private static final PaymentMode DEFAULT_PAYMENT_MODE = PaymentMode.Cash;
    private static final PaymentMode UPDATED_PAYMENT_MODE = PaymentMode.CHEQUE;

    private static final String DEFAULT_BANK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_SWIFT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SWIFT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_I_BANNO = "AAAAAAAAAA";
    private static final String UPDATED_I_BANNO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/brokers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BrokerRepository brokerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBrokerMockMvc;

    private Broker broker;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Broker createEntity(EntityManager em) {
        Broker broker = new Broker()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .country(DEFAULT_COUNTRY)
            .city(DEFAULT_CITY)
            .whtAcceptance(DEFAULT_WHT_ACCEPTANCE)
            .region(DEFAULT_REGION)
            .contactName(DEFAULT_CONTACT_NAME)
            .contactDial(DEFAULT_CONTACT_DIAL)
            .contactEmail(DEFAULT_CONTACT_EMAIL)
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .taxRegisteryNumber(DEFAULT_TAX_REGISTERY_NUMBER)
            .taxAuthority(DEFAULT_TAX_AUTHORITY)
            .validFrom(DEFAULT_VALID_FROM)
            .validTo(DEFAULT_VALID_TO)
            .taxType(DEFAULT_TAX_TYPE)
            .taxValue(DEFAULT_TAX_VALUE)
            .regulatory(DEFAULT_REGULATORY)
            .fraCode(DEFAULT_FRA_CODE)
            .liscenceValidFrom(DEFAULT_LISCENCE_VALID_FROM)
            .liscenceValidTo(DEFAULT_LISCENCE_VALID_TO)
            .brokerNameAr(DEFAULT_BROKER_NAME_AR)
            .paymentType(DEFAULT_PAYMENT_TYPE)
            .paymentMode(DEFAULT_PAYMENT_MODE)
            .bankName(DEFAULT_BANK_NAME)
            .accountName(DEFAULT_ACCOUNT_NAME)
            .branch(DEFAULT_BRANCH)
            .bankAccountNo(DEFAULT_BANK_ACCOUNT_NO)
            .bankAddress(DEFAULT_BANK_ADDRESS)
            .currency(DEFAULT_CURRENCY)
            .swiftCode(DEFAULT_SWIFT_CODE)
            .iBANNO(DEFAULT_I_BANNO);
        return broker;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Broker createUpdatedEntity(EntityManager em) {
        Broker broker = new Broker()
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .country(UPDATED_COUNTRY)
            .city(UPDATED_CITY)
            .whtAcceptance(UPDATED_WHT_ACCEPTANCE)
            .region(UPDATED_REGION)
            .contactName(UPDATED_CONTACT_NAME)
            .contactDial(UPDATED_CONTACT_DIAL)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .taxRegisteryNumber(UPDATED_TAX_REGISTERY_NUMBER)
            .taxAuthority(UPDATED_TAX_AUTHORITY)
            .validFrom(UPDATED_VALID_FROM)
            .validTo(UPDATED_VALID_TO)
            .taxType(UPDATED_TAX_TYPE)
            .taxValue(UPDATED_TAX_VALUE)
            .regulatory(UPDATED_REGULATORY)
            .fraCode(UPDATED_FRA_CODE)
            .liscenceValidFrom(UPDATED_LISCENCE_VALID_FROM)
            .liscenceValidTo(UPDATED_LISCENCE_VALID_TO)
            .brokerNameAr(UPDATED_BROKER_NAME_AR)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .paymentMode(UPDATED_PAYMENT_MODE)
            .bankName(UPDATED_BANK_NAME)
            .accountName(UPDATED_ACCOUNT_NAME)
            .branch(UPDATED_BRANCH)
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAddress(UPDATED_BANK_ADDRESS)
            .currency(UPDATED_CURRENCY)
            .swiftCode(UPDATED_SWIFT_CODE)
            .iBANNO(UPDATED_I_BANNO);
        return broker;
    }

    @BeforeEach
    public void initTest() {
        broker = createEntity(em);
    }

    @Test
    @Transactional
    void createBroker() throws Exception {
        int databaseSizeBeforeCreate = brokerRepository.findAll().size();
        // Create the Broker
        restBrokerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(broker)))
            .andExpect(status().isCreated());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeCreate + 1);
        Broker testBroker = brokerList.get(brokerList.size() - 1);
        assertThat(testBroker.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBroker.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testBroker.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testBroker.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testBroker.getWhtAcceptance()).isEqualTo(DEFAULT_WHT_ACCEPTANCE);
        assertThat(testBroker.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testBroker.getContactName()).isEqualTo(DEFAULT_CONTACT_NAME);
        assertThat(testBroker.getContactDial()).isEqualTo(DEFAULT_CONTACT_DIAL);
        assertThat(testBroker.getContactEmail()).isEqualTo(DEFAULT_CONTACT_EMAIL);
        assertThat(testBroker.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBroker.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBroker.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBroker.getTaxRegisteryNumber()).isEqualTo(DEFAULT_TAX_REGISTERY_NUMBER);
        assertThat(testBroker.getTaxAuthority()).isEqualTo(DEFAULT_TAX_AUTHORITY);
        assertThat(testBroker.getValidFrom()).isEqualTo(DEFAULT_VALID_FROM);
        assertThat(testBroker.getValidTo()).isEqualTo(DEFAULT_VALID_TO);
        assertThat(testBroker.getTaxType()).isEqualTo(DEFAULT_TAX_TYPE);
        assertThat(testBroker.getTaxValue()).isEqualTo(DEFAULT_TAX_VALUE);
        assertThat(testBroker.getRegulatory()).isEqualTo(DEFAULT_REGULATORY);
        assertThat(testBroker.getFraCode()).isEqualTo(DEFAULT_FRA_CODE);
        assertThat(testBroker.getLiscenceValidFrom()).isEqualTo(DEFAULT_LISCENCE_VALID_FROM);
        assertThat(testBroker.getLiscenceValidTo()).isEqualTo(DEFAULT_LISCENCE_VALID_TO);
        assertThat(testBroker.getBrokerNameAr()).isEqualTo(DEFAULT_BROKER_NAME_AR);
        assertThat(testBroker.getPaymentType()).isEqualTo(DEFAULT_PAYMENT_TYPE);
        assertThat(testBroker.getPaymentMode()).isEqualTo(DEFAULT_PAYMENT_MODE);
        assertThat(testBroker.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testBroker.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testBroker.getBranch()).isEqualTo(DEFAULT_BRANCH);
        assertThat(testBroker.getBankAccountNo()).isEqualTo(DEFAULT_BANK_ACCOUNT_NO);
        assertThat(testBroker.getBankAddress()).isEqualTo(DEFAULT_BANK_ADDRESS);
        assertThat(testBroker.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testBroker.getSwiftCode()).isEqualTo(DEFAULT_SWIFT_CODE);
        assertThat(testBroker.getiBANNO()).isEqualTo(DEFAULT_I_BANNO);
    }

    @Test
    @Transactional
    void createBrokerWithExistingId() throws Exception {
        // Create the Broker with an existing ID
        broker.setId(1L);

        int databaseSizeBeforeCreate = brokerRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBrokerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(broker)))
            .andExpect(status().isBadRequest());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBrokers() throws Exception {
        // Initialize the database
        brokerRepository.saveAndFlush(broker);

        // Get all the brokerList
        restBrokerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(broker.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].whtAcceptance").value(hasItem(DEFAULT_WHT_ACCEPTANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].contactName").value(hasItem(DEFAULT_CONTACT_NAME)))
            .andExpect(jsonPath("$.[*].contactDial").value(hasItem(DEFAULT_CONTACT_DIAL)))
            .andExpect(jsonPath("$.[*].contactEmail").value(hasItem(DEFAULT_CONTACT_EMAIL)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].taxRegisteryNumber").value(hasItem(DEFAULT_TAX_REGISTERY_NUMBER)))
            .andExpect(jsonPath("$.[*].taxAuthority").value(hasItem(DEFAULT_TAX_AUTHORITY)))
            .andExpect(jsonPath("$.[*].validFrom").value(hasItem(DEFAULT_VALID_FROM.toString())))
            .andExpect(jsonPath("$.[*].validTo").value(hasItem(DEFAULT_VALID_TO.toString())))
            .andExpect(jsonPath("$.[*].taxType").value(hasItem(DEFAULT_TAX_TYPE.toString())))
            .andExpect(jsonPath("$.[*].taxValue").value(hasItem(DEFAULT_TAX_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].regulatory").value(hasItem(DEFAULT_REGULATORY)))
            .andExpect(jsonPath("$.[*].fraCode").value(hasItem(DEFAULT_FRA_CODE)))
            .andExpect(jsonPath("$.[*].liscenceValidFrom").value(hasItem(DEFAULT_LISCENCE_VALID_FROM.toString())))
            .andExpect(jsonPath("$.[*].liscenceValidTo").value(hasItem(DEFAULT_LISCENCE_VALID_TO.toString())))
            .andExpect(jsonPath("$.[*].brokerNameAr").value(hasItem(DEFAULT_BROKER_NAME_AR)))
            .andExpect(jsonPath("$.[*].paymentType").value(hasItem(DEFAULT_PAYMENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].paymentMode").value(hasItem(DEFAULT_PAYMENT_MODE.toString())))
            .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME)))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH)))
            .andExpect(jsonPath("$.[*].bankAccountNo").value(hasItem(DEFAULT_BANK_ACCOUNT_NO)))
            .andExpect(jsonPath("$.[*].bankAddress").value(hasItem(DEFAULT_BANK_ADDRESS)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].swiftCode").value(hasItem(DEFAULT_SWIFT_CODE)))
            .andExpect(jsonPath("$.[*].iBANNO").value(hasItem(DEFAULT_I_BANNO)));
    }

    @Test
    @Transactional
    void getBroker() throws Exception {
        // Initialize the database
        brokerRepository.saveAndFlush(broker);

        // Get the broker
        restBrokerMockMvc
            .perform(get(ENTITY_API_URL_ID, broker.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(broker.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.whtAcceptance").value(DEFAULT_WHT_ACCEPTANCE.booleanValue()))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION))
            .andExpect(jsonPath("$.contactName").value(DEFAULT_CONTACT_NAME))
            .andExpect(jsonPath("$.contactDial").value(DEFAULT_CONTACT_DIAL))
            .andExpect(jsonPath("$.contactEmail").value(DEFAULT_CONTACT_EMAIL))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.taxRegisteryNumber").value(DEFAULT_TAX_REGISTERY_NUMBER))
            .andExpect(jsonPath("$.taxAuthority").value(DEFAULT_TAX_AUTHORITY))
            .andExpect(jsonPath("$.validFrom").value(DEFAULT_VALID_FROM.toString()))
            .andExpect(jsonPath("$.validTo").value(DEFAULT_VALID_TO.toString()))
            .andExpect(jsonPath("$.taxType").value(DEFAULT_TAX_TYPE.toString()))
            .andExpect(jsonPath("$.taxValue").value(DEFAULT_TAX_VALUE.doubleValue()))
            .andExpect(jsonPath("$.regulatory").value(DEFAULT_REGULATORY))
            .andExpect(jsonPath("$.fraCode").value(DEFAULT_FRA_CODE))
            .andExpect(jsonPath("$.liscenceValidFrom").value(DEFAULT_LISCENCE_VALID_FROM.toString()))
            .andExpect(jsonPath("$.liscenceValidTo").value(DEFAULT_LISCENCE_VALID_TO.toString()))
            .andExpect(jsonPath("$.brokerNameAr").value(DEFAULT_BROKER_NAME_AR))
            .andExpect(jsonPath("$.paymentType").value(DEFAULT_PAYMENT_TYPE.toString()))
            .andExpect(jsonPath("$.paymentMode").value(DEFAULT_PAYMENT_MODE.toString()))
            .andExpect(jsonPath("$.bankName").value(DEFAULT_BANK_NAME))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME))
            .andExpect(jsonPath("$.branch").value(DEFAULT_BRANCH))
            .andExpect(jsonPath("$.bankAccountNo").value(DEFAULT_BANK_ACCOUNT_NO))
            .andExpect(jsonPath("$.bankAddress").value(DEFAULT_BANK_ADDRESS))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.swiftCode").value(DEFAULT_SWIFT_CODE))
            .andExpect(jsonPath("$.iBANNO").value(DEFAULT_I_BANNO));
    }

    @Test
    @Transactional
    void getNonExistingBroker() throws Exception {
        // Get the broker
        restBrokerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBroker() throws Exception {
        // Initialize the database
        brokerRepository.saveAndFlush(broker);

        int databaseSizeBeforeUpdate = brokerRepository.findAll().size();

        // Update the broker
        Broker updatedBroker = brokerRepository.findById(broker.getId()).get();
        // Disconnect from session so that the updates on updatedBroker are not directly saved in db
        em.detach(updatedBroker);
        updatedBroker
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .country(UPDATED_COUNTRY)
            .city(UPDATED_CITY)
            .whtAcceptance(UPDATED_WHT_ACCEPTANCE)
            .region(UPDATED_REGION)
            .contactName(UPDATED_CONTACT_NAME)
            .contactDial(UPDATED_CONTACT_DIAL)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .taxRegisteryNumber(UPDATED_TAX_REGISTERY_NUMBER)
            .taxAuthority(UPDATED_TAX_AUTHORITY)
            .validFrom(UPDATED_VALID_FROM)
            .validTo(UPDATED_VALID_TO)
            .taxType(UPDATED_TAX_TYPE)
            .taxValue(UPDATED_TAX_VALUE)
            .regulatory(UPDATED_REGULATORY)
            .fraCode(UPDATED_FRA_CODE)
            .liscenceValidFrom(UPDATED_LISCENCE_VALID_FROM)
            .liscenceValidTo(UPDATED_LISCENCE_VALID_TO)
            .brokerNameAr(UPDATED_BROKER_NAME_AR)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .paymentMode(UPDATED_PAYMENT_MODE)
            .bankName(UPDATED_BANK_NAME)
            .accountName(UPDATED_ACCOUNT_NAME)
            .branch(UPDATED_BRANCH)
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAddress(UPDATED_BANK_ADDRESS)
            .currency(UPDATED_CURRENCY)
            .swiftCode(UPDATED_SWIFT_CODE)
            .iBANNO(UPDATED_I_BANNO);

        restBrokerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBroker.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBroker))
            )
            .andExpect(status().isOk());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeUpdate);
        Broker testBroker = brokerList.get(brokerList.size() - 1);
        assertThat(testBroker.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBroker.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBroker.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testBroker.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testBroker.getWhtAcceptance()).isEqualTo(UPDATED_WHT_ACCEPTANCE);
        assertThat(testBroker.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testBroker.getContactName()).isEqualTo(UPDATED_CONTACT_NAME);
        assertThat(testBroker.getContactDial()).isEqualTo(UPDATED_CONTACT_DIAL);
        assertThat(testBroker.getContactEmail()).isEqualTo(UPDATED_CONTACT_EMAIL);
        assertThat(testBroker.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBroker.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBroker.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBroker.getTaxRegisteryNumber()).isEqualTo(UPDATED_TAX_REGISTERY_NUMBER);
        assertThat(testBroker.getTaxAuthority()).isEqualTo(UPDATED_TAX_AUTHORITY);
        assertThat(testBroker.getValidFrom()).isEqualTo(UPDATED_VALID_FROM);
        assertThat(testBroker.getValidTo()).isEqualTo(UPDATED_VALID_TO);
        assertThat(testBroker.getTaxType()).isEqualTo(UPDATED_TAX_TYPE);
        assertThat(testBroker.getTaxValue()).isEqualTo(UPDATED_TAX_VALUE);
        assertThat(testBroker.getRegulatory()).isEqualTo(UPDATED_REGULATORY);
        assertThat(testBroker.getFraCode()).isEqualTo(UPDATED_FRA_CODE);
        assertThat(testBroker.getLiscenceValidFrom()).isEqualTo(UPDATED_LISCENCE_VALID_FROM);
        assertThat(testBroker.getLiscenceValidTo()).isEqualTo(UPDATED_LISCENCE_VALID_TO);
        assertThat(testBroker.getBrokerNameAr()).isEqualTo(UPDATED_BROKER_NAME_AR);
        assertThat(testBroker.getPaymentType()).isEqualTo(UPDATED_PAYMENT_TYPE);
        assertThat(testBroker.getPaymentMode()).isEqualTo(UPDATED_PAYMENT_MODE);
        assertThat(testBroker.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testBroker.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testBroker.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testBroker.getBankAccountNo()).isEqualTo(UPDATED_BANK_ACCOUNT_NO);
        assertThat(testBroker.getBankAddress()).isEqualTo(UPDATED_BANK_ADDRESS);
        assertThat(testBroker.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testBroker.getSwiftCode()).isEqualTo(UPDATED_SWIFT_CODE);
        assertThat(testBroker.getiBANNO()).isEqualTo(UPDATED_I_BANNO);
    }

    @Test
    @Transactional
    void putNonExistingBroker() throws Exception {
        int databaseSizeBeforeUpdate = brokerRepository.findAll().size();
        broker.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBrokerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, broker.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(broker))
            )
            .andExpect(status().isBadRequest());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBroker() throws Exception {
        int databaseSizeBeforeUpdate = brokerRepository.findAll().size();
        broker.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBrokerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(broker))
            )
            .andExpect(status().isBadRequest());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBroker() throws Exception {
        int databaseSizeBeforeUpdate = brokerRepository.findAll().size();
        broker.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBrokerMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(broker)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBrokerWithPatch() throws Exception {
        // Initialize the database
        brokerRepository.saveAndFlush(broker);

        int databaseSizeBeforeUpdate = brokerRepository.findAll().size();

        // Update the broker using partial update
        Broker partialUpdatedBroker = new Broker();
        partialUpdatedBroker.setId(broker.getId());

        partialUpdatedBroker
            .name(UPDATED_NAME)
            .city(UPDATED_CITY)
            .contactName(UPDATED_CONTACT_NAME)
            .contactDial(UPDATED_CONTACT_DIAL)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .taxAuthority(UPDATED_TAX_AUTHORITY)
            .validFrom(UPDATED_VALID_FROM)
            .taxValue(UPDATED_TAX_VALUE)
            .regulatory(UPDATED_REGULATORY)
            .fraCode(UPDATED_FRA_CODE)
            .liscenceValidFrom(UPDATED_LISCENCE_VALID_FROM)
            .paymentMode(UPDATED_PAYMENT_MODE)
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAddress(UPDATED_BANK_ADDRESS)
            .currency(UPDATED_CURRENCY);

        restBrokerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBroker.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBroker))
            )
            .andExpect(status().isOk());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeUpdate);
        Broker testBroker = brokerList.get(brokerList.size() - 1);
        assertThat(testBroker.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBroker.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testBroker.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testBroker.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testBroker.getWhtAcceptance()).isEqualTo(DEFAULT_WHT_ACCEPTANCE);
        assertThat(testBroker.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testBroker.getContactName()).isEqualTo(UPDATED_CONTACT_NAME);
        assertThat(testBroker.getContactDial()).isEqualTo(UPDATED_CONTACT_DIAL);
        assertThat(testBroker.getContactEmail()).isEqualTo(UPDATED_CONTACT_EMAIL);
        assertThat(testBroker.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBroker.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBroker.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBroker.getTaxRegisteryNumber()).isEqualTo(DEFAULT_TAX_REGISTERY_NUMBER);
        assertThat(testBroker.getTaxAuthority()).isEqualTo(UPDATED_TAX_AUTHORITY);
        assertThat(testBroker.getValidFrom()).isEqualTo(UPDATED_VALID_FROM);
        assertThat(testBroker.getValidTo()).isEqualTo(DEFAULT_VALID_TO);
        assertThat(testBroker.getTaxType()).isEqualTo(DEFAULT_TAX_TYPE);
        assertThat(testBroker.getTaxValue()).isEqualTo(UPDATED_TAX_VALUE);
        assertThat(testBroker.getRegulatory()).isEqualTo(UPDATED_REGULATORY);
        assertThat(testBroker.getFraCode()).isEqualTo(UPDATED_FRA_CODE);
        assertThat(testBroker.getLiscenceValidFrom()).isEqualTo(UPDATED_LISCENCE_VALID_FROM);
        assertThat(testBroker.getLiscenceValidTo()).isEqualTo(DEFAULT_LISCENCE_VALID_TO);
        assertThat(testBroker.getBrokerNameAr()).isEqualTo(DEFAULT_BROKER_NAME_AR);
        assertThat(testBroker.getPaymentType()).isEqualTo(DEFAULT_PAYMENT_TYPE);
        assertThat(testBroker.getPaymentMode()).isEqualTo(UPDATED_PAYMENT_MODE);
        assertThat(testBroker.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testBroker.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testBroker.getBranch()).isEqualTo(DEFAULT_BRANCH);
        assertThat(testBroker.getBankAccountNo()).isEqualTo(UPDATED_BANK_ACCOUNT_NO);
        assertThat(testBroker.getBankAddress()).isEqualTo(UPDATED_BANK_ADDRESS);
        assertThat(testBroker.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testBroker.getSwiftCode()).isEqualTo(DEFAULT_SWIFT_CODE);
        assertThat(testBroker.getiBANNO()).isEqualTo(DEFAULT_I_BANNO);
    }

    @Test
    @Transactional
    void fullUpdateBrokerWithPatch() throws Exception {
        // Initialize the database
        brokerRepository.saveAndFlush(broker);

        int databaseSizeBeforeUpdate = brokerRepository.findAll().size();

        // Update the broker using partial update
        Broker partialUpdatedBroker = new Broker();
        partialUpdatedBroker.setId(broker.getId());

        partialUpdatedBroker
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .country(UPDATED_COUNTRY)
            .city(UPDATED_CITY)
            .whtAcceptance(UPDATED_WHT_ACCEPTANCE)
            .region(UPDATED_REGION)
            .contactName(UPDATED_CONTACT_NAME)
            .contactDial(UPDATED_CONTACT_DIAL)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .taxRegisteryNumber(UPDATED_TAX_REGISTERY_NUMBER)
            .taxAuthority(UPDATED_TAX_AUTHORITY)
            .validFrom(UPDATED_VALID_FROM)
            .validTo(UPDATED_VALID_TO)
            .taxType(UPDATED_TAX_TYPE)
            .taxValue(UPDATED_TAX_VALUE)
            .regulatory(UPDATED_REGULATORY)
            .fraCode(UPDATED_FRA_CODE)
            .liscenceValidFrom(UPDATED_LISCENCE_VALID_FROM)
            .liscenceValidTo(UPDATED_LISCENCE_VALID_TO)
            .brokerNameAr(UPDATED_BROKER_NAME_AR)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .paymentMode(UPDATED_PAYMENT_MODE)
            .bankName(UPDATED_BANK_NAME)
            .accountName(UPDATED_ACCOUNT_NAME)
            .branch(UPDATED_BRANCH)
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAddress(UPDATED_BANK_ADDRESS)
            .currency(UPDATED_CURRENCY)
            .swiftCode(UPDATED_SWIFT_CODE)
            .iBANNO(UPDATED_I_BANNO);

        restBrokerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBroker.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBroker))
            )
            .andExpect(status().isOk());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeUpdate);
        Broker testBroker = brokerList.get(brokerList.size() - 1);
        assertThat(testBroker.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBroker.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBroker.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testBroker.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testBroker.getWhtAcceptance()).isEqualTo(UPDATED_WHT_ACCEPTANCE);
        assertThat(testBroker.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testBroker.getContactName()).isEqualTo(UPDATED_CONTACT_NAME);
        assertThat(testBroker.getContactDial()).isEqualTo(UPDATED_CONTACT_DIAL);
        assertThat(testBroker.getContactEmail()).isEqualTo(UPDATED_CONTACT_EMAIL);
        assertThat(testBroker.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBroker.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBroker.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBroker.getTaxRegisteryNumber()).isEqualTo(UPDATED_TAX_REGISTERY_NUMBER);
        assertThat(testBroker.getTaxAuthority()).isEqualTo(UPDATED_TAX_AUTHORITY);
        assertThat(testBroker.getValidFrom()).isEqualTo(UPDATED_VALID_FROM);
        assertThat(testBroker.getValidTo()).isEqualTo(UPDATED_VALID_TO);
        assertThat(testBroker.getTaxType()).isEqualTo(UPDATED_TAX_TYPE);
        assertThat(testBroker.getTaxValue()).isEqualTo(UPDATED_TAX_VALUE);
        assertThat(testBroker.getRegulatory()).isEqualTo(UPDATED_REGULATORY);
        assertThat(testBroker.getFraCode()).isEqualTo(UPDATED_FRA_CODE);
        assertThat(testBroker.getLiscenceValidFrom()).isEqualTo(UPDATED_LISCENCE_VALID_FROM);
        assertThat(testBroker.getLiscenceValidTo()).isEqualTo(UPDATED_LISCENCE_VALID_TO);
        assertThat(testBroker.getBrokerNameAr()).isEqualTo(UPDATED_BROKER_NAME_AR);
        assertThat(testBroker.getPaymentType()).isEqualTo(UPDATED_PAYMENT_TYPE);
        assertThat(testBroker.getPaymentMode()).isEqualTo(UPDATED_PAYMENT_MODE);
        assertThat(testBroker.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testBroker.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testBroker.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testBroker.getBankAccountNo()).isEqualTo(UPDATED_BANK_ACCOUNT_NO);
        assertThat(testBroker.getBankAddress()).isEqualTo(UPDATED_BANK_ADDRESS);
        assertThat(testBroker.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testBroker.getSwiftCode()).isEqualTo(UPDATED_SWIFT_CODE);
        assertThat(testBroker.getiBANNO()).isEqualTo(UPDATED_I_BANNO);
    }

    @Test
    @Transactional
    void patchNonExistingBroker() throws Exception {
        int databaseSizeBeforeUpdate = brokerRepository.findAll().size();
        broker.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBrokerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, broker.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(broker))
            )
            .andExpect(status().isBadRequest());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBroker() throws Exception {
        int databaseSizeBeforeUpdate = brokerRepository.findAll().size();
        broker.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBrokerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(broker))
            )
            .andExpect(status().isBadRequest());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBroker() throws Exception {
        int databaseSizeBeforeUpdate = brokerRepository.findAll().size();
        broker.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBrokerMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(broker)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Broker in the database
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBroker() throws Exception {
        // Initialize the database
        brokerRepository.saveAndFlush(broker);

        int databaseSizeBeforeDelete = brokerRepository.findAll().size();

        // Delete the broker
        restBrokerMockMvc
            .perform(delete(ENTITY_API_URL_ID, broker.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Broker> brokerList = brokerRepository.findAll();
        assertThat(brokerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
