package uk.gov.dwp.jsa.security;

import java.security.Key;

public interface KeyRepository {

    Key getDefaultKey();

    Key getKeyById(String keyId);

}
