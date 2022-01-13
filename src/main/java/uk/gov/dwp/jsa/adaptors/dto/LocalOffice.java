package uk.gov.dwp.jsa.adaptors.dto;

import uk.gov.dwp.jsa.adaptors.dto.claim.Address;

import java.util.ArrayList;
import java.util.Collection;

public class LocalOffice {
    public static final String WELSH_PHONE_TYPE = "welsh";
    public static final String MAIN_PHONE_TYPE = "main board";

    private String name;
    private String jobCentreId;
    private PhoneNumbers phoneNumbers = new PhoneNumbers(new ArrayList<>());
    private Address address;
    private BenefitCentre benefitCentre;

    public LocalOffice() {
    }

    public String getPhoneNumber() {
        return phoneNumbers.getNumber(MAIN_PHONE_TYPE);
    }

    public String getWelshPhoneNumber() {
        return phoneNumbers.getNumber(WELSH_PHONE_TYPE);
    }

    public LocalOffice(
            final String name,
            final String jobCentreId,
            final PhoneNumbers phoneNumbers,
            final Address address,
            final BenefitCentre benefitCentre) {
        this.name = name;
        this.jobCentreId = jobCentreId;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.benefitCentre = benefitCentre;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setJobCentreId(final String jobCentreId) {
        this.jobCentreId = jobCentreId;
    }

    public String getJobCentreId() {
        return jobCentreId;
    }

    public BenefitCentre getBenefitCentre() {
        return benefitCentre;
    }

    public void setBenefitCentre(final BenefitCentre benefitCentre) {
        this.benefitCentre = benefitCentre;
    }

    public void setPhoneNumbers(final Collection<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = new PhoneNumbers(phoneNumbers);
    }

    public Collection<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers.getNumbers();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }
}
