package uk.gov.dwp.jsa.security;

import org.springframework.stereotype.Component;
import uk.gov.dwp.jsa.adaptors.ServicesProperties;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PublicKeyRepo implements KeyRepository {

    private final Map<Object, PublicKey> keyById = new HashMap<>();
    private final PublicKey defaultKey;

    public PublicKeyRepo(final ServicesProperties servicesProperties,
                  final KeyLoader<String> keyLoader) {

        if (servicesProperties.getPublicKeyMap() != null) {
            keyById.putAll(servicesProperties.getPublicKeyMap()
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> keyLoader.loadPublicKey(e.getValue()))));
        }
        defaultKey = keyLoader.loadPublicKey(servicesProperties.getPublicKey());
    }

    @Override
    public PublicKey getDefaultKey() {
        return defaultKey;
    }

    @Override
    public PublicKey getKeyById(final String keyId) {
        return keyById.get(keyId);
    }
}
