package uk.gov.dwp.jsa.security;

import java.nio.charset.Charset;

public final class Constants {
    private Constants() {

    }

    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static final String TEST_PROFILE = "local_test";
    public static final String DEV_PROFILE = "dev";
    public static final String LOCAL_PROFILE = "local";
    public static final String KMS_PROFILE = "kms";

    public static final class NoSecure {
        private NoSecure() {

        }
        public static final String NO_SECURE_PROFILE = "nosecure";
        public static final String CCA = "CCA";
        public static final String CCM = "CCM";
        public static final String WC = "WC";
        public static final String SCA = "SCA";
    }
}
