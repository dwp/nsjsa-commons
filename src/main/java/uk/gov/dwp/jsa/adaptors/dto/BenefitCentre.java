package uk.gov.dwp.jsa.adaptors.dto;

public class BenefitCentre {
    private String benefitCentreId;
    private String serviceId;
    private String name;

    public BenefitCentre() {
    }

    public String getBenefitCentreId() {
        return benefitCentreId;
    }

    public void setBenefitCentreId(final String benefitCentreId) {
        this.benefitCentreId = benefitCentreId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(final String serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
