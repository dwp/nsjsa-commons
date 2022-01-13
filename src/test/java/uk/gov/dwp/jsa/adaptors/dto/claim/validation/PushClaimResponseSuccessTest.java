package uk.gov.dwp.jsa.adaptors.dto.claim.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class PushClaimResponseSuccessTest {

    private boolean nameUpdateSuccess;
    private boolean correspondanceUpdateSuccess;
    private boolean postalAddressUpdateSuccess;
    private boolean contactUpdateASuccess;
    private boolean expectedResult;

    public PushClaimResponseSuccessTest(
            boolean nameUpdateSuccess,
            boolean correspondanceUpdateSuccess,
            boolean postalAddressUpdateSuccess,
            boolean contactUpdateASuccess,
            boolean expectedResult) {
        this.nameUpdateSuccess = nameUpdateSuccess;
        this.correspondanceUpdateSuccess = correspondanceUpdateSuccess;
        this.postalAddressUpdateSuccess = postalAddressUpdateSuccess;
        this.contactUpdateASuccess = contactUpdateASuccess;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters(name = "{index} isSuccessful({0}, {1}, {2}, {3})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {false, false, false, false, false},
                {false, false, false, true, false},
                {false, false, true, false, false},
                {false, false, true, true, false},
                {false, true, false, false, false},
                {false, true, false, true, false},
                {false, true, true, false, false},
                {false, true, true, true, false},
                {true, false, false, false, false},
                {true, false, false, true, false},
                {true, false, true, false, false},
                {true, false, true, true, false},
                {true, true, false, false, false},
                {true, true, false, true, false},
                {true, true, true, false, false},
                {true, true, true, true, true},
        });
    }

    @Test
    public void isSuccessful() {
        final PushClaimResponse response = new PushClaimResponse();
        response.setNameUpdateSuccess(nameUpdateSuccess);
        response.setCorrespondanceAddressUpdateSuccess(correspondanceUpdateSuccess);
        response.setPostalAddressUpdateSuccess(postalAddressUpdateSuccess);
        response.setContactUpdateSuccess(contactUpdateASuccess);
        assertThat(response.isSuccessful(), is(expectedResult));
    }

}
