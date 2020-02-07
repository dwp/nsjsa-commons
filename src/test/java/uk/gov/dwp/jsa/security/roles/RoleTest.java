package uk.gov.dwp.jsa.security.roles;

import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import uk.gov.dwp.jsa.security.AuthenticationToken;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoleTest {

    @Test
    public void givenCCARoleIsAgentReturnsTrue() {
        authenticate(getAuthenticationToken(Role.CCA));
        boolean result = Role.isAgent();
        assertTrue("Given CCA Role isAgent should return true", result);
    }

    @Test
    public void givenCCMRoleIsAgentReturnsTrue() {
        authenticate(getAuthenticationToken(Role.CCM));
        boolean result = Role.isAgent();
        assertTrue("Given CCM Role isAgent should return true", result);
    }

    @Test
    public void givenWCRoleIsAgentReturnsTrue() {
        authenticate(getAuthenticationToken(Role.WC));
        boolean result = Role.isAgent();
        assertTrue("Given WC Role isAgent should return true", result);
    }

    @Test
    public void givenSCARoleIsAgentReturnsTrue() {
        //When authenticated as CCA
        AuthenticationToken authenticationToken = getAuthenticationToken(Role.SCA);
        authenticate(authenticationToken);

        boolean result = Role.isAgent();

        assertTrue("Given SCA Role isAgent should return true", result);
    }

    @Test
    public void givenNoRoleIsAgentReturnsFalse() {
        authenticate(getAuthenticationToken(null));
        boolean result = Role.isAgent();
        assertFalse("Given no Role isAgent should return false", result);
    }

    @Test
    public void givenACitizenRoleIsCitizenReturnsTrue() {
        authenticate(getAuthenticationToken(Role.CITIZEN));
        boolean result = Role.isCitizen();
        assertTrue("Given a CITIZEN Role isCitizen should return true", result);
    }

    @Test
    public void givenACitizenRoleIsAgentReturnsFalse(){
        authenticate(getAuthenticationToken(Role.CITIZEN));
        boolean result = Role.isAgent();
        assertFalse("Given CITIZEN Role isAgent should return false", result);
    }

    @Test
    public void givenASystemRoleIsAgentReturnsFalse(){
        authenticate(getAuthenticationToken(Role.SYSTEM));
        boolean result = Role.isAgent();
        assertFalse("Given SYSTEM Role isAgent should return false", result);
    }

    @Test
    public void givenASystemRoleIsCitizenReturnsFalse(){
        authenticate(getAuthenticationToken(Role.SYSTEM));
        boolean result = Role.isCitizen();
        assertFalse("Given SYSTEM Role isCitizen should return false", result);
    }

    @Test
    public void givenALongFullNameTruncates() {
        String expectedName = "Batson Bill Digital Group Peel P";
        AuthenticationToken token = new AuthenticationToken(
                "Batson Bill Digital Group Peel Park Control Centre", "", "", EMPTY_LIST
        );
        authenticate(token);
        String actualName = Role.getFullName().get();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void givenAshortFullName() {
        String expectedName = "Batson Bill";
        AuthenticationToken token = new AuthenticationToken(
                "Batson Bill", "", "", EMPTY_LIST
        );
        authenticate(token);
        String actualName = Role.getFullName().get();
        assertEquals(expectedName, actualName);
    }

    private void authenticate(final AuthenticationToken authenticationToken) {
        final SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authenticationToken);
        SecurityContextHolder.setContext(securityContext);
    }

    /**
     *
     * @param role - pass null if you want no role
     * @return auth token for provided role
     */
    private AuthenticationToken getAuthenticationToken(Role role) {
        if(role == null){
            return new AuthenticationToken("", "", "", EMPTY_LIST);
        }
        return new AuthenticationToken("", "", "",
                singletonList(new SimpleGrantedAuthority(role.name())));
    }
}
