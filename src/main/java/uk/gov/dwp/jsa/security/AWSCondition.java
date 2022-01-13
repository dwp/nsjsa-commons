package uk.gov.dwp.jsa.security;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.stream.Stream;

import static uk.gov.dwp.jsa.security.Constants.LOCAL_PROFILE;
import static uk.gov.dwp.jsa.security.Constants.TEST_PROFILE;
import static uk.gov.dwp.jsa.security.Constants.KMS_PROFILE;

public class AWSCondition implements Condition {
    @Override
    public boolean matches(final ConditionContext conditionContext, final AnnotatedTypeMetadata annotatedTypeMetadata) {
        final String[] activeProfiles = conditionContext.getEnvironment().getActiveProfiles();
        return check(activeProfiles);
    }

    static boolean check(final String[] activeProfiles) {
        return Stream.of(activeProfiles)
                .noneMatch(p -> TEST_PROFILE.equals(p) || LOCAL_PROFILE.equals(p))
                || Stream.of(activeProfiles).anyMatch(pr -> KMS_PROFILE.equals(pr));
    }
}
