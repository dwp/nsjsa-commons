package uk.gov.dwp.jsa.adaptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import uk.gov.dwp.jsa.adaptors.exception.PushJsapsException;
import uk.gov.dwp.jsa.adaptors.http.api.ApiMultiErrorResponse;
import uk.gov.dwp.jsa.adaptors.http.api.ApiSuccess;
import uk.gov.dwp.jsa.adaptors.http.api.ApiResponse;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Optional.of;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Component
public class RestfulExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestfulExecutor.class);
    private static final int HTTP_REQ_TIMEOUT_MS = 60_000;

    private RestTemplate restTemplate;
    private HttpEntityFactory httpEntityFactory;

    public RestfulExecutor(
            final RestTemplate restTemplate,
            final HttpEntityFactory httpEntityFactory) {

        this.restTemplate = restTemplate;
        this.httpEntityFactory = httpEntityFactory;

    }


    @PostConstruct
    public void init() {
        HttpComponentsClientHttpRequestWithBodyFactory hrf = new HttpComponentsClientHttpRequestWithBodyFactory();
        hrf.setConnectTimeout(HTTP_REQ_TIMEOUT_MS);
        hrf.setReadTimeout(HTTP_REQ_TIMEOUT_MS);
        this.restTemplate.setRequestFactory(hrf);
    }

    public static <T> Optional<T> claimantExceptionally(final Throwable ex, final String id) {
        return logAndReturnEmpty(ex, "Claimant async action failed, claimantId: {}", id);
    }

    public static <T> Optional<T> circumstancesExceptionally(final Throwable ex, final String id) {
        return logAndReturnEmpty(ex, "Circumstances async action failed, claimantId: {}", id);
    }

    public static <T> Optional<T> claimStatusExceptionally(final Throwable ex, final String id) {
        return logAndReturnEmpty(ex, "Claim Status async action failed, claimantId: {}", id);
    }

    public static <T> Optional<T> bankDetailsExceptionally(final Throwable ex, final String id) {
        return logAndReturnEmpty(ex, "Bank details async action failed, claimantId: {}", id);
    }


    public static <T> Optional<T> logAndReturnEmpty(final Throwable ex, final String message, final String id) {
        LOGGER.warn(message, id, ex);
        return Optional.empty();
    }

    public static <T extends ApiResponse<K>, K> Optional<List<K>> okOrNotFoundList(
            final ResponseEntity<T> responseEntity) {
        Stream<HttpStatus> statusStream = Stream.of(HttpStatus.OK, HttpStatus.NOT_FOUND);
        return getList(responseEntity, statusStream);
    }

    public static <T extends ApiResponse<K>, K> Optional<K> okOrNotFound(final ResponseEntity<T> responseEntity) {
        Stream<HttpStatus> statusStream = Stream.of(HttpStatus.OK, HttpStatus.NOT_FOUND);
        return getValue(responseEntity, statusStream);
    }

    public static <T extends ApiResponse<K>, K> Optional<K> accepted(final ResponseEntity<T> responseEntity) {
        Stream<HttpStatus> statusStream = Stream.of(HttpStatus.ACCEPTED);
        return getValue(responseEntity, statusStream);
    }

    public static <T extends ApiResponse<K>, K> Optional<K> created(final ResponseEntity<T> responseEntity) {
        Stream<HttpStatus> statusStream = Stream.of(HttpStatus.CREATED);
        return getValue(responseEntity, statusStream);
    }

    public static Optional<Boolean> deleted(final ResponseEntity<String> responseEntity) {
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return of(TRUE);
        } else if (HttpStatus.NOT_FOUND.equals(responseEntity.getStatusCode())) {
            return of(FALSE);
        }
        throw new RestClientException("Status code is not acceptable: " + responseEntity.getStatusCode());
    }

    public static <T extends ApiMultiErrorResponse<K>, K> Optional<K> createdMultiError(
            final ResponseEntity<T> responseEntity) {
        Stream<HttpStatus> statusStream = Stream.of(HttpStatus.CREATED);
        return getValueMulti(responseEntity, statusStream);
    }

    public static <T extends ApiResponse<K>, K> K updatedWithoutOptional(final ResponseEntity<T> responseEntity) {
        Stream<HttpStatus> statusStream = Stream.of(HttpStatus.OK, HttpStatus.CREATED);

        if (isAccepted(statusStream, responseEntity)) {
            final T body = responseEntity.getBody();
            if (body == null || body.getSuccess() == null || body.getSuccess().isEmpty()) {
                return null;
            }
            Optional<K> data = ObjectUtils.resolve(() -> responseEntity.getBody().getSuccess().get(0).getData());
            return data.orElse(null);
        }
        throw new RestClientException("Status code is not acceptable: " + responseEntity.getStatusCode());
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

    private static <T extends ApiResponse<K>, K> Optional<List<K>> getList(final ResponseEntity<T> responseEntity,
                                                                           final Stream<HttpStatus> statusStream) {
        if (isAccepted(statusStream, responseEntity)) {
            final T body = responseEntity.getBody();
            if (body == null || body.getSuccess() == null || body.getSuccess().isEmpty()) {
                return Optional.empty();
            }
            List<K> dataList = body.getSuccess()
                    .stream().map(ApiSuccess::getData)
                    .collect(Collectors.toList());
            return Optional.of(dataList);
        }
        throw new RestClientException("Status code is not acceptable: " + responseEntity.getStatusCode());
    }

    private static <T extends ApiMultiErrorResponse<K>, K> Optional<K> getValueMulti(
            final ResponseEntity<T> responseEntity,
            final Stream<HttpStatus> statusStream) {
        if (isAccepted(statusStream, responseEntity)) {
            final T body = responseEntity.getBody();
            if (body == null || body.getSuccess() == null || body.getSuccess().isEmpty()) {
                return Optional.empty();
            }
            StringBuilder sb = new StringBuilder();
            for (ApiSuccess apiSuccess : body.getSuccess()) {
                sb.append(apiSuccess.getData());
            }
            return (Optional<K>) Optional.of(sb.toString());
        }
        final T body = responseEntity.getBody();
        if (body == null || body.getSuccess() == null || body.getSuccess().isEmpty()) {
            return Optional.empty();
        }
        throw new PushJsapsException(body.getError());
    }

    private static <T> boolean isAccepted(final Stream<HttpStatus> stream, final ResponseEntity<T> responseEntity) {
        return stream.anyMatch(c -> c.equals(responseEntity.getStatusCode()));
    }

    public <T extends ApiResponse<K>, K> Optional<List<K>> getList(final String url, final Class<T> responseType,
                                                                   final Function<ResponseEntity<T>,
                                                                           Optional<List<K>>> callback) {
        ResponseEntity<T> response = restTemplate
                .getForEntity(
                        url,
                        responseType,
                        httpEntityFactory.createWithJsonHeaders());

        return callback.apply(response);
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

    public <T extends ApiResponse<K>, K> Optional<K> getWithBody(final String url,
                                                                 final Object payload,
                                                                 final Class<T> responseType,
                                                         final Function<ResponseEntity<T>, Optional<K>> callback) {
        ResponseEntity<T> response = restTemplate
                .exchange(
                        url,
                        GET,
                        httpEntityFactory.createWithJsonHeaders(payload),
                        responseType);

        return callback.apply(response);
    }

    public <T extends ApiResponse<K>, K> Optional<List<K>> getListWithPayload(
            final String url,
            final Object payload,
            final Class<T> responseType,
            final Function<ResponseEntity<T>, Optional<List<K>>> callback) {
        ResponseEntity<T> response = restTemplate
                .exchange(
                        url,
                        GET,
                        httpEntityFactory.createWithJsonHeaders(payload),
                        responseType);

        return callback.apply(response);
    }

    public Optional<Boolean> delete(final String url,
                                    final Function<ResponseEntity<String>, Optional<Boolean>> callback) {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE,
                httpEntityFactory.createWithJsonHeaders(),
                String.class);

        return callback.apply(response);
    }

    public <T extends ApiResponse<K>, K> Optional<K> post(final String url,
                                                          final Object payload,
                                                          final Class<T> responseType,
                                                          final Function<ResponseEntity<T>, Optional<K>> callback) {
        return exchange(url, POST, payload, responseType, callback);
    }

    public <T extends ApiResponse<K>, K> Optional<K> patch(final String url,
                                                           final Object payload,
                                                           final Class<T> responseType,
                                                           final Function<ResponseEntity<T>, Optional<K>> callback) {

        return exchange(url, PATCH, payload, responseType, callback);
    }

    public <T extends ApiResponse<K>, K> Optional<K> put(final String url,
                                                         final Object payload,
                                                         final Class<T> responseType,
                                                         final Function<ResponseEntity<T>, Optional<K>> callback) {

        return exchange(url, PUT, payload, responseType, callback);
    }

    public <T extends ApiMultiErrorResponse<K>, K> Optional<K> updateMultiError(final String url,
                                                                                final Object payload,
                                                                                final Class<T> responseType,
                                                                                final Function<ResponseEntity<T>,
                                                                                        Optional<K>> callback) {
        HttpEntity<Object> requestUpdate = httpEntityFactory.createWithJsonHeaders(payload);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, requestUpdate, responseType);
        return callback.apply(response);
    }

    public <T extends ApiResponse<K>, K> K updateWithoutOptional(final String url,
                                                                 final Object payload,
                                                                 final Class<T> responseType,
                                                                 final Function<ResponseEntity<T>, K> callback) {

        HttpEntity<Object> requestUpdate = httpEntityFactory.createWithJsonHeaders(payload);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, responseType);
        return callback.apply(response);
    }

    private <T extends ApiResponse<K>, K> Optional<K> exchange(final String url,
                                                               final HttpMethod method,
                                                               final Object payload,
                                                               final Class<T> responseType,
                                                               final Function<ResponseEntity<T>,
                                                                       Optional<K>> callback) {

        HttpEntity<Object> requestUpdate = httpEntityFactory.createWithJsonHeaders(payload);
        ResponseEntity<T> response = restTemplate.exchange(url, method, requestUpdate, responseType);
        return callback.apply(response);
    }
}
