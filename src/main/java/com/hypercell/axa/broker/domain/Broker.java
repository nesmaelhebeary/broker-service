package com.hypercell.axa.broker.domain;

import com.hypercell.axa.broker.domain.enumeration.BrokerStatus;
import com.hypercell.axa.broker.domain.enumeration.PaymentMode;
import com.hypercell.axa.broker.domain.enumeration.PaymentType;
import com.hypercell.axa.broker.domain.enumeration.TaxType;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Broker.
 */
@Entity
@Table(name = "broker")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Broker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "wht_acceptance")
    private Boolean whtAcceptance;

    @Column(name = "region")
    private String region;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_dial")
    private String contactDial;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BrokerStatus status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "tax_registery_number")
    private String taxRegisteryNumber;

    @Column(name = "tax_authority")
    private String taxAuthority;

    @Column(name = "valid_from")
    private LocalDate validFrom;

    @Column(name = "valid_to")
    private LocalDate validTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tax_type")
    private TaxType taxType;

    @Column(name = "tax_value")
    private Double taxValue;

    @Column(name = "regulatory")
    private String regulatory;

    @Column(name = "fra_code")
    private String fraCode;

    @Column(name = "liscence_valid_from")
    private LocalDate liscenceValidFrom;

    @Column(name = "liscence_valid_to")
    private LocalDate liscenceValidTo;

    @Column(name = "broker_name_ar")
    private String brokerNameAr;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode")
    private PaymentMode paymentMode;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "branch")
    private String branch;

    @Column(name = "bank_account_no")
    private String bankAccountNo;

    @Column(name = "bank_address")
    private String bankAddress;

    @Column(name = "currency")
    private String currency;

    @Column(name = "swift_code")
    private String swiftCode;

    @Column(name = "i_banno")
    private String iBANNO;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Broker id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Broker name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public Broker address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return this.country;
    }

    public Broker country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public Broker city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getWhtAcceptance() {
        return this.whtAcceptance;
    }

    public Broker whtAcceptance(Boolean whtAcceptance) {
        this.whtAcceptance = whtAcceptance;
        return this;
    }

    public void setWhtAcceptance(Boolean whtAcceptance) {
        this.whtAcceptance = whtAcceptance;
    }

    public String getRegion() {
        return this.region;
    }

    public Broker region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getContactName() {
        return this.contactName;
    }

    public Broker contactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactDial() {
        return this.contactDial;
    }

    public Broker contactDial(String contactDial) {
        this.contactDial = contactDial;
        return this;
    }

    public void setContactDial(String contactDial) {
        this.contactDial = contactDial;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public Broker contactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Broker createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public BrokerStatus getStatus() {
        return this.status;
    }

    public Broker status(BrokerStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(BrokerStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Broker createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getTaxRegisteryNumber() {
        return this.taxRegisteryNumber;
    }

    public Broker taxRegisteryNumber(String taxRegisteryNumber) {
        this.taxRegisteryNumber = taxRegisteryNumber;
        return this;
    }

    public void setTaxRegisteryNumber(String taxRegisteryNumber) {
        this.taxRegisteryNumber = taxRegisteryNumber;
    }

    public String getTaxAuthority() {
        return this.taxAuthority;
    }

    public Broker taxAuthority(String taxAuthority) {
        this.taxAuthority = taxAuthority;
        return this;
    }

    public void setTaxAuthority(String taxAuthority) {
        this.taxAuthority = taxAuthority;
    }

    public LocalDate getValidFrom() {
        return this.validFrom;
    }

    public Broker validFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return this.validTo;
    }

    public Broker validTo(LocalDate validTo) {
        this.validTo = validTo;
        return this;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public TaxType getTaxType() {
        return this.taxType;
    }

    public Broker taxType(TaxType taxType) {
        this.taxType = taxType;
        return this;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }

    public Double getTaxValue() {
        return this.taxValue;
    }

    public Broker taxValue(Double taxValue) {
        this.taxValue = taxValue;
        return this;
    }

    public void setTaxValue(Double taxValue) {
        this.taxValue = taxValue;
    }

    public String getRegulatory() {
        return this.regulatory;
    }

    public Broker regulatory(String regulatory) {
        this.regulatory = regulatory;
        return this;
    }

    public void setRegulatory(String regulatory) {
        this.regulatory = regulatory;
    }

    public String getFraCode() {
        return this.fraCode;
    }

    public Broker fraCode(String fraCode) {
        this.fraCode = fraCode;
        return this;
    }

    public void setFraCode(String fraCode) {
        this.fraCode = fraCode;
    }

    public LocalDate getLiscenceValidFrom() {
        return this.liscenceValidFrom;
    }

    public Broker liscenceValidFrom(LocalDate liscenceValidFrom) {
        this.liscenceValidFrom = liscenceValidFrom;
        return this;
    }

    public void setLiscenceValidFrom(LocalDate liscenceValidFrom) {
        this.liscenceValidFrom = liscenceValidFrom;
    }

    public LocalDate getLiscenceValidTo() {
        return this.liscenceValidTo;
    }

    public Broker liscenceValidTo(LocalDate liscenceValidTo) {
        this.liscenceValidTo = liscenceValidTo;
        return this;
    }

    public void setLiscenceValidTo(LocalDate liscenceValidTo) {
        this.liscenceValidTo = liscenceValidTo;
    }

    public String getBrokerNameAr() {
        return this.brokerNameAr;
    }

    public Broker brokerNameAr(String brokerNameAr) {
        this.brokerNameAr = brokerNameAr;
        return this;
    }

    public void setBrokerNameAr(String brokerNameAr) {
        this.brokerNameAr = brokerNameAr;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public Broker paymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentMode getPaymentMode() {
        return this.paymentMode;
    }

    public Broker paymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
        return this;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getBankName() {
        return this.bankName;
    }

    public Broker bankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public Broker accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBranch() {
        return this.branch;
    }

    public Broker branch(String branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBankAccountNo() {
        return this.bankAccountNo;
    }

    public Broker bankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
        return this;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankAddress() {
        return this.bankAddress;
    }

    public Broker bankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
        return this;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Broker currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSwiftCode() {
        return this.swiftCode;
    }

    public Broker swiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
        return this;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getiBANNO() {
        return this.iBANNO;
    }

    public Broker iBANNO(String iBANNO) {
        this.iBANNO = iBANNO;
        return this;
    }

    public void setiBANNO(String iBANNO) {
        this.iBANNO = iBANNO;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Broker)) {
            return false;
        }
        return id != null && id.equals(((Broker) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Broker{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", country='" + getCountry() + "'" +
            ", city='" + getCity() + "'" +
            ", whtAcceptance='" + getWhtAcceptance() + "'" +
            ", region='" + getRegion() + "'" +
            ", contactName='" + getContactName() + "'" +
            ", contactDial='" + getContactDial() + "'" +
            ", contactEmail='" + getContactEmail() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", taxRegisteryNumber='" + getTaxRegisteryNumber() + "'" +
            ", taxAuthority='" + getTaxAuthority() + "'" +
            ", validFrom='" + getValidFrom() + "'" +
            ", validTo='" + getValidTo() + "'" +
            ", taxType='" + getTaxType() + "'" +
            ", taxValue=" + getTaxValue() +
            ", regulatory='" + getRegulatory() + "'" +
            ", fraCode='" + getFraCode() + "'" +
            ", liscenceValidFrom='" + getLiscenceValidFrom() + "'" +
            ", liscenceValidTo='" + getLiscenceValidTo() + "'" +
            ", brokerNameAr='" + getBrokerNameAr() + "'" +
            ", paymentType='" + getPaymentType() + "'" +
            ", paymentMode='" + getPaymentMode() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", branch='" + getBranch() + "'" +
            ", bankAccountNo='" + getBankAccountNo() + "'" +
            ", bankAddress='" + getBankAddress() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", swiftCode='" + getSwiftCode() + "'" +
            ", iBANNO='" + getiBANNO() + "'" +
            "}";
    }
}
