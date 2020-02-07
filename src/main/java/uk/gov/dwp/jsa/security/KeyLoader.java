package uk.gov.dwp.jsa.security;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface KeyLoader<Input> {

    PublicKey loadPublicKey(Input keyToLoad);

    PrivateKey loadPrivateKey(Input keyToLoad);

}
