package uk.gov.dwp.jsa.adaptors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import uk.gov.dwp.jsa.adaptors.http.api.ApiResponse;
import uk.gov.dwp.jsa.security.JWTFilter;
import uk.gov.dwp.jsa.security.roles.Role;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
public class RestfulExecutor {
    private static final int HTTP_REQ_TIMEOUT_MS = 60_000;

    private RestTemplate restTemplate;
    private HttpEntityFactory httpEntityFactory;

    public RestfulExecutor(
            final RestTemplate restTemplate,
            final HttpEntityFactory httpEntityFactory) {

        this.restTemplate = restTemplate;
        this.httpEntityFactory = httpEntityFactory;

        //TODO to be done in a proper way.
        this.restTemplate.getInterceptors().add((request, body, execution) -> {

            if (!request.getHeaders().containsKey(JWTFilter.AUTHORIZATION_HEADER)) {
                Role.getJWT().ifPresent((jwt) -> {
                    request.getHeaders().add(JWTFilter.AUTHORIZATION_HEADER, JWTFilter.AUTH_PREFIX + jwt);
                });
            }
            if (!request.getHeaders().containsKey(JWTFilter.TOKEN_PAYLOAD_HEADER)) {
                Role.getPayload().ifPresent((tokenPayload) -> {
                    request.getHeaders().add(JWTFilter.TOKEN_PAYLOAD_HEADER, tokenPayload);
                });
            }
            return execution.execute(request, body);
        });
    }


    @PostConstruct
    public void init() {
        HttpComponentsClientHttpRequestWithBodyFactory hrf = new HttpComponentsClientHttpRequestWithBodyFactory();
        hrf.setConnectTimeout(HTTP_REQ_TIMEOUT_MS);
        hrf.setReadTimeout(HTTP_REQ_TIMEOUT_MS);
        this.restTemplate.setRequestFactory(hrf);
    }


    public static <T extends ApiResponse<K>, K> Optional<K> okOrNotFound(final ResponseEntity<T> responseEntity) {
        Stream<HttpStatus> statusStream = Stream.of(HttpStatus.OK, HttpStatus.NOT_FOUND);
        return getValue(responseEntity, statusStream);
    }


    private static <T extends ApiResponse<K>, K> Optional<K> getValue(final ResponseEntity<T> responseEntity,
                                                                      final Stream<HttpStatus> statusStream) {
        if (isAccepted(statusStream, responseEntity)) {
            final T body = responseEntity.getBody();
            if (body == null || body.getSuccess() == null || body.getSuccess().isEmpty()) {
                return Optional.empty();
            }
            return ObjectUtils.resolve(() -> responseEntity.getBody().getSuccess().get(0).getData());
        }
        throw new RestClientException("Status code is not acceptable: " + responseEntity.getStatusCode());
    }


    private static <T> boolean isAccepted(final Stream<HttpStatus> stream, final ResponseEntity<T> responseEntity) {
        return stream.anyMatch(c -> c.equals(responseEntity.getStatusCode()));
    }


    public <T extends ApiResponse<K>, K> Optional<K> get(final String url, final Class<T> responseType,
                                                         final Function<ResponseEntity<T>, Optional<K>> callback) {
        ResponseEntity<T> response = restTemplate
                .getForEntity(
                        url,
                        responseType,
                        httpEntityFactory.createWithJsonHeaders());

        return callback.apply(response);
    }

}
