package uk.gov.dwp.jsa.adaptors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import uk.gov.dwp.jsa.adaptors.dto.claim.ClaimStatistics;
import uk.gov.dwp.jsa.adaptors.http.api.ApiResponse;
import uk.gov.dwp.jsa.adaptors.http.api.ClaimStats;
import uk.gov.dwp.jsa.adaptors.http.api.NotificationRequest;
import uk.gov.dwp.jsa.adaptors.http.api.SubmittedClaimsTally;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RestfulNotificationServiceAdaptorTest {

    private static final String NOTIFICATION_SERVICE_VERSION = "1";
    private static final String NOTIFICATION_SERVER = "http:///hello";
    private static final Integer PREVIOUS_DAY_COUNT = 1;

    private static final String SEND_MAIL_URL = String.format("%s/nsjsa/v%s/notification/mail/claim-confirmation", NOTIFICATION_SERVER, NOTIFICATION_SERVICE_VERSION);
    private static final String SEND_SMS_URL = String.format("%s/nsjsa/v%s/notification/sms/claim-confirmation", NOTIFICATION_SERVER, NOTIFICATION_SERVICE_VERSION);
    private static final String SEND_MAIL_COUNT_URL = String.format("%s/nsjsa/v%s/notification/mail/mi-claims-count", NOTIFICATION_SERVER, NOTIFICATION_SERVICE_VERSION);
    private static final String SEND_MAIL_STATS_URL = String.format("%s/nsjsa/v%s/notification/mail/mi-claim-stats", NOTIFICATION_SERVER, NOTIFICATION_SERVICE_VERSION);
    private static final String SEND_MAIL_DAILY_REPORT = String.format("%s/nsjsa/v%s/notification/mail/daily-claim-stats-summary?previousDayCount=%s", NOTIFICATION_SERVER, NOTIFICATION_SERVICE_VERSION, PREVIOUS_DAY_COUNT);

    private String SEND_MAIL_PROGRESS_URL = String.format("/nsjsa/v%s/notification/mail/claim-progress", NOTIFICATION_SERVER, NOTIFICATION_SERVICE_VERSION);
    private String SEND_SMS_PROGRESS_URL = String.format("/nsjsa/v%s/notification/sms/claim-progress", NOTIFICATION_SERVER, NOTIFICATION_SERVICE_VERSION);
    private String SEND_SMS_SUCCESS_URL = String.format("/nsjsa/v%s/notification/sms/claim-success", NOTIFICATION_SERVER, NOTIFICATION_SERVICE_VERSION);
    private String SEND_MAIL_SUCCESS_URL = String.format("/nsjsa/v%s/notification/mail/claim-success", NOTIFICATION_SERVER, NOTIFICATION_SERVICE_VERSION);

    private static final RestClientException REST_CLIENT_EXCEPTION = new RestClientException("");
    private static final UUID CLAIMANT_ID = UUID.randomUUID();
    private static final SubmittedClaimsTally SUBMITTED_CLAIMS_TALLY = new SubmittedClaimsTally();
    private static final ClaimStats CLAIM_STATS = new ClaimStats(new ClaimStatistics());

    @Mock
    private ServicesProperties servicesProperties;
    @Mock
    private RestfulExecutor restfulExecutor;

    private RestfulNotificationServiceAdaptor adaptor;

    private CompletableFuture<String> response;

    @Before
    public void beforeEachTest() {
        initMocks(this);

        adaptor = new RestfulNotificationServiceAdaptor(
                servicesProperties,
                restfulExecutor);

        when(servicesProperties.getNotificationServer()).thenReturn(NOTIFICATION_SERVER);
        when(servicesProperties.getNotificationVersion()).thenReturn(NOTIFICATION_SERVICE_VERSION);
    }

    @Test
    public void sendSMS() throws ExecutionException, InterruptedException {
        givenAnAdaptorWhichRespondsWithOkFromASendSms();
        whenWeSendTheSms();
        thenAnOkIsReturned();
    }

    @Test
    public void sendEmail() throws ExecutionException, InterruptedException {
        givenAnAdaptorWhichRespondsWithOkFromASendEmail();
        whenWeSendTheEmail();
        thenAnOkIsReturned();
    }

    @Test
    public void sendSubmittedClaimsCountEmail() throws ExecutionException, InterruptedException {
        givenAnAdaptorWhichRespondsWithOkFromASendMailCount();
        whenWeSendSubmittedClaimsCountEmail();
        thenAnOkIsReturned();
    }

    @Test(expected = RestClientException.class)
    public void sendSubmittedClaimsCountEmailThrowsException() throws ExecutionException, InterruptedException {
        givenAnAdaptorWhichThrowsAnExceptionFromASendMailCount();
        whenWeSendSubmittedClaimsCountEmail();
    }

    @Test
    public void sendClaimStatsEmail() throws ExecutionException, InterruptedException {
        givenAnAdaptorWhichRespondsWithOkFromASendMailStats();
        whenWeSendClaimStatsEmail();
        thenAnOkIsReturned();
    }

    @Test(expected = RestClientException.class)
    public void sendClaimStatsEmailThrowsException() throws ExecutionException, InterruptedException {
        givenAnAdaptorWhichThrowsAnExceptionFromASendMailStats();
        whenWeSendClaimStatsEmail();
    }

    @Test
    public void sendProgressEmail() throws ExecutionException, InterruptedException {
        givenAnAdaptorWhichResponseWithOkFromASendProgressMail();
        whenSendClaimProgressEmail();
        thenAnOkIsReturned();
    }

    @Test
    public void sendSuccessSms() throws ExecutionException, InterruptedException {
        givenAnAdaptorWhichResponseWithOkFromASendSuccessSms();
        whenSendClaimSuccessSms();
        thenAnOkIsReturned();
    }

    @Test
    public void sendSuccessMail() throws ExecutionException, InterruptedException {
        givenAnAdaptorWhichResponseWithOkFromASendSuccessMail();
        whenSendClaimSuccessMail();
        thenAnOkIsReturned();
    }

    @Test
    public void sendProgressSms() throws ExecutionException, InterruptedException {
        givenAnAdaptorWhichResponseWithOkFromASendProgressSms();
        whenSendClaimProgressSms();
        thenAnOkIsReturned();
    }

    @Test
    public void testSendDailyClaimStatsMail() throws ExecutionException, InterruptedException {
        givenAnAdaptorWhichRespondsWithOkFromSendDailyClaimStatsMail();
        whenSendDailyClaimStatsMail();
        thenAnOkIsReturned();
    }

    private void givenAnAdaptorWhichRespondsWithOkFromSendDailyClaimStatsMail() {
        when(restfulExecutor.post(
                eq(SEND_MAIL_DAILY_REPORT),
                isNull(),
                eq(ApiResponse.class),
                any()))
                .thenReturn(Optional.of(HttpStatus.OK.name()));
    }

    private void givenAnAdaptorWhichRespondsWithOkFromASendSms() {
        when(restfulExecutor.post(
                eq(SEND_MAIL_URL),
                any(NotificationRequest.class),
                eq(ApiResponse.class),
                any()))
                .thenReturn(Optional.of(HttpStatus.OK.name()));
    }

    private void givenAnAdaptorWhichRespondsWithOkFromASendEmail() {
        when(restfulExecutor.post(
                eq(SEND_SMS_URL),
                any(NotificationRequest.class),
                eq(ApiResponse.class),
                any()))
                .thenReturn(Optional.of(HttpStatus.OK.name()));
    }

    private void givenAnAdaptorWhichRespondsWithOkFromASendMailCount() {
        when(restfulExecutor.post(
                eq(SEND_MAIL_COUNT_URL),
                any(SubmittedClaimsTally.class),
                eq(ApiResponse.class),
                any()))
                .thenReturn(Optional.of(HttpStatus.OK.name()));
    }

    private void givenAnAdaptorWhichThrowsAnExceptionFromASendMailCount() {
        when(restfulExecutor.post(
                eq(SEND_MAIL_COUNT_URL),
                any(SubmittedClaimsTally.class),
                eq(ApiResponse.class),
                any()))
                .thenThrow(REST_CLIENT_EXCEPTION);
    }
    private void givenAnAdaptorWhichRespondsWithOkFromASendMailStats() {
        when(restfulExecutor.post(
                eq(SEND_MAIL_STATS_URL),
                any(ClaimStats.class),
                eq(ApiResponse.class),
                any()))
                .thenReturn(Optional.of(HttpStatus.OK.name()));
    }

    private void givenAnAdaptorWhichThrowsAnExceptionFromASendMailStats() {
        when(restfulExecutor.post(
                eq(SEND_MAIL_STATS_URL),
                any(ClaimStats.class),
                eq(ApiResponse.class),
                any()))
                .thenThrow(REST_CLIENT_EXCEPTION);
    }

    private void givenAnAdaptorWhichResponseWithOkFromASendProgressMail() {
        when(restfulExecutor.post(
                eq(SEND_MAIL_PROGRESS_URL),
                any(NotificationRequest.class),
                eq(ApiResponse.class),
                any()))
                .thenReturn(Optional.of(HttpStatus.OK.name()));
    }

    private void givenAnAdaptorWhichThrowsAnExceptionWithOkFromASendProgressMail() {
        when(restfulExecutor.post(
                eq(SEND_MAIL_PROGRESS_URL),
                any(NotificationRequest.class),
                eq(ApiResponse.class),
                any()))
                .thenThrow(REST_CLIENT_EXCEPTION);
    }

    private void givenAnAdaptorWhichResponseWithOkFromASendProgressSms() {
        when(restfulExecutor.post(
                eq(SEND_SMS_PROGRESS_URL),
                any(NotificationRequest.class),
                eq(ApiResponse.class),
                any()))
                .thenReturn(Optional.of(HttpStatus.OK.name()));
    }

    private void givenAnAdaptorWhichResponseWithOkFromASendSuccessSms() {
        when(restfulExecutor.post(
                eq(SEND_SMS_SUCCESS_URL),
                any(NotificationRequest.class),
                eq(ApiResponse.class),
                any()))
                .thenReturn(Optional.of(HttpStatus.OK.name()));
    }

    private void givenAnAdaptorWhichResponseWithOkFromASendSuccessMail() {
        when(restfulExecutor.post(
                eq(SEND_MAIL_SUCCESS_URL),
                any(NotificationRequest.class),
                eq(ApiResponse.class),
                any()))
                .thenReturn(Optional.of(HttpStatus.OK.name()));
    }

    private void givenAnAdaptorWhichThrowsAnExceptionWithOkFromASendProgressSms() {
        when(restfulExecutor.post(
                eq(SEND_SMS_PROGRESS_URL),
                any(NotificationRequest.class),
                eq(ApiResponse.class),
                any()))
                .thenThrow(REST_CLIENT_EXCEPTION);
    }


    private void whenWeSendTheSms() {
        response = adaptor.sendSMS(CLAIMANT_ID);
    }

    private void whenWeSendTheEmail() {
        response = adaptor.sendEmail(CLAIMANT_ID);
    }

    private void whenWeSendSubmittedClaimsCountEmail() {
        response = adaptor.sendSubmittedClaimsCountEmail(SUBMITTED_CLAIMS_TALLY);
    }

    private void whenWeSendClaimStatsEmail() {
        response = adaptor.sendClaimStatsEmail(CLAIM_STATS);
    }

    private void whenSendClaimProgressEmail() {
        response = adaptor.sendClaimProgressEmail(CLAIMANT_ID);
    }

    private void whenSendClaimProgressSms() {
        response = adaptor.sendClaimProgressSMS(CLAIMANT_ID);
    }

    private void whenSendDailyClaimStatsMail() {
        response = adaptor.sendDailyClaimStatsMail(PREVIOUS_DAY_COUNT);
    }

    private void whenSendClaimSuccessSms() {
        response = adaptor.sendClaimSuccessSms(CLAIMANT_ID);
    }

    private void whenSendClaimSuccessMail() {
        response = adaptor.sendClaimSuccessEmail(CLAIMANT_ID);
    }

    private void thenAnOkIsReturned() throws ExecutionException, InterruptedException {
        assertThat(response.get(), is(HttpStatus.OK.name()));
    }
}
