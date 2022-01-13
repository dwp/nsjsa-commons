package uk.gov.dwp.jsa.adaptors.dto.claim;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class BankDetailsIsEmptyTest {

    private String accountHolder;

    private String sortCode;

    private String accountNumber;
    private boolean expectedResult;

    public BankDetailsIsEmptyTest(String accountHolder, String sortCode, String accountNumber, boolean expectedResult) {
        this.accountHolder = accountHolder;
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters(name = "{index} isEmpty({0}, {1}, {2})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", "", "", true},
                {"", "", "nonempty", true},
                {"", "nonempty", "", true},
                {"", "nonempty", "nonempty", true},
                {"nonempty", "", "", true},
                {"nonempty", "", "nonempty", true},
                {"nonempty", "nonempty", "", true},
                {"nonempty", "nonempty", "nonempty", false},
                {null, null, null, true},
                {null, null, "nonempty", true},
                {null, "nonempty", null, true},
                {null, "nonempty", "nonempty", true},
                {"nonempty", null, null, true},
                {"nonempty", null, "nonempty", true},
                {"nonempty", "nonempty", null, true},
                {"nonempty", "nonempty", "nonempty", false},
        });
    }

    @Test
    public void isEmpty() {
        final BankDetails bankDetails = new BankDetails();
        bankDetails.setAccountNumber(accountNumber);
        bankDetails.setAccountHolder(accountHolder);
        bankDetails.setSortCode(sortCode);
        assertThat(bankDetails.isEmpty(), is(expectedResult));
    }

}
