package uk.gov.dwp.jsa.security.roles;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import uk.gov.dwp.jsa.adaptors.enums.UserType;
import uk.gov.dwp.jsa.security.User;

import java.util.Optional;
import java.util.stream.Stream;

import static uk.gov.dwp.jsa.adaptors.enums.UserType.AGENT;

/**
 * CCA Contact Centre agent.
 * CCM Contact Centre manager.
 * WC Work Coach.
 * SCA Service Centre agent.
 */
public enum Role {

    CITIZEN(""),
    SYSTEM("system"),
    CCA("nsjsa_assistance_user"),
    CCM("nsjsa_admin"),
    WC("nsjsa_verification_user"),
    SCA("nsjsa_decision_user");

    private final String group;
    private static final int MAX_USERNAME_LENGTH = 32;

    Role(final String group) {
        this.group = group;
    }

    public static boolean hasRole(final Role role) {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream().anyMatch(a -> a.getAuthority().equals(role.name()));
    }

    public static boolean isWorkCoach() {
        return hasRole(WC);
    }

    public static boolean isContactCentreAgent() {
        return hasRole(CCA);
    }

    public static boolean isContactCentreManager() {
        return hasRole(CCM);
    }

    public static boolean isServiceCentreAgent() {
        return hasRole(SCA);
    }

    public static Optional<String> getJWT() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getCredentials() instanceof String)
                .map(authentication -> (String) authentication.getCredentials());
    }

    public static boolean isAgent() {
        return hasRole(CCA) || hasRole(CCM) || hasRole(SCA) || hasRole(WC);
    }

    public static boolean isCitizen() {
        return hasRole(CITIZEN);
    }

    public static UserType userType() {
        return isAgent() ? AGENT : UserType.CITIZEN;
    }

    public static Optional<String> getFullName() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Optional<String> fullName = Optional.ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getPrincipal() instanceof User)
                .map(authentication -> ((User) authentication.getPrincipal()).getFullName());

        if (fullName.isPresent() && fullName.get().length() > MAX_USERNAME_LENGTH) {
            fullName = Optional.of(fullName.get().substring(0, MAX_USERNAME_LENGTH));
        }

        return fullName;
    }

    public static Optional<String> getStaffNumber() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getPrincipal() instanceof User)
                .map(authentication -> ((User) authentication.getPrincipal()).getStaffNumber());
    }

    public static Optional<String> getPayload() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getPrincipal() instanceof User)
                .map(authentication -> ((User) authentication.getPrincipal()).getPayload());
    }

    public static Optional<String> fromGroup(final String group) {
        return Stream
                .of(Role.values())
                .filter(role -> role.getGroup().equals(group))
                .map(Role::name)
                .findFirst();
    }

    public String getGroup() {
        return group;
    }
}
