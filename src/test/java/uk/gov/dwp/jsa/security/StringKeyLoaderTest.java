package uk.gov.dwp.jsa.security;

import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.Assert.assertEquals;

public class StringKeyLoaderTest {

    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnV8yKezS3uQlAsO5UK43" +
            "V4D5IgvHSZYe2KLxARa+myLCvnP6WqUG+GEBlEj4EcTzaS3gZZiEO6xhuylKQjif" +
            "EbhsHPKhNVreIyOwEFCPO0w+boNRrp33DxI+2Q9M2gZrXMUX8et9SyhTyWNsNdSO" +
            "dEJ/KNMDX5obUh9/vkvCP1BTATKt8B2vCodErfs3kxkO3Ok8WcWQK5jQoIUYbBjO" +
            "VzNdCChJzYDWtz3z5CBmtsBRSHnXhLNvt2hkk76tG3hE1AZ3gHsCM2DXNkGhYra9" +
            "vqAdh8sljJ3nBxQuJeNNd4AqyzlBt4FQw/cmYJsPXHBACHm+kFvqJJ20ZB4Y7Gqm" +
            "CwIDAQAB";

    private static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCdXzIp7NLe5CUC" +
            "w7lQrjdXgPkiC8dJlh7YovEBFr6bIsK+c/papQb4YQGUSPgRxPNpLeBlmIQ7rGG7" +
            "KUpCOJ8RuGwc8qE1Wt4jI7AQUI87TD5ug1GunfcPEj7ZD0zaBmtcxRfx631LKFPJ" +
            "Y2w11I50Qn8o0wNfmhtSH3++S8I/UFMBMq3wHa8Kh0St+zeTGQ7c6TxZxZArmNCg" +
            "hRhsGM5XM10IKEnNgNa3PfPkIGa2wFFIedeEs2+3aGSTvq0beETUBneAewIzYNc2" +
            "QaFitr2+oB2HyyWMnecHFC4l4013gCrLOUG3gVDD9yZgmw9ccEAIeb6QW+oknbRk" +
            "HhjsaqYLAgMBAAECggEAEqGln78RDCB4nUYs13d4x9xWIojAdF7Ykg9gdx+JO3a3" +
            "FbNHbZniJF/fvv2QmRH3O3mZ+o0/Ao2bGHx/9S0c8A0kh5zWJyn16LUd8CCJhJy3" +
            "O4BCmGjNSjR/ylT8ZUejSF1xQ0v9Dnvcx5gExK7AmtUQlvW8jYICQAvIpO/1NGeP" +
            "kK0WAvzyMNUn9LmH+NpUg1cIrPRLfPV0niNx9MpeSsMMLBR2y61swj07qoLmya5l" +
            "W5919oaiF8AvpbbRXBF64gzfrblmOuVrOZp/ZohHiK95aI4uB6oSUfrSPeDyLnqI" +
            "g60o3KDqc9hSXdbqLIl087mMmzevLy+/4g9gmXMpUQKBgQDOVanQpqAgCxRrWO/Z" +
            "t09Oml2rdrjRKsjHgGEwKBJw7X4M1NOpr/ZHaqoYZnXU5MEAf+vzJQwMoIP2ga5w" +
            "BiO7n7lLBRqKzxnwuSF3KvHvZdvdDdhEf0+j9SQXHBTNk+Ww28S9Bfus0Fc9G/YQ" +
            "2xB8LDWX/x5anCCKmT2dSlJk2QKBgQDDQHKjBG9/TM7X0YO0nrTSHtxVqcX1of1q" +
            "ke8uCkZWYYO5vnHtnQ45ZNyDX1vvZ6bbhbbqztLiuvZEPM7ofZiriJN1tXGPCeKY" +
            "QmfT3yhYuXi5wtiQSbbacdBGylpkUzs5GGlQzQvbbCBAKzraKgSvh7gdCi/J+D3Y" +
            "d/zNkBaDgwKBgQCVioxaxojC8ITKEFk1R5/Y7hP5U0A7iHCf9rF5f3FuGxICJrWJ" +
            "BBjQRcQljwzTUT94Jz5QqIcFF8jMfcx5MFnxf5+BeZ90/whj6SkR/zP9N6uTmJcn" +
            "fcWwpNiVTyrcm9Yaygzaml1/MDgp9/vs13gpJrlpAeNz9De5e7qmJtl0cQKBgHsc" +
            "qOvHATKoUIfecIgzxdnzYYSWDt8pq+tFZyWFjwIqbG9y+M0VSYHA6hXfy80xcxFZ" +
            "x5NYo7oYnu051lDb2KbX7/q5VIgXeIv+2w7J19vR3qBqjji0chpcxzHLp6E4pdAD" +
            "Z2xuTbql+9PaLjqfYeL31Yxh/u3/m6dhSMKoxrlXAoGBAMs5t4W2m4LnyX0dBtqS" +
            "ZvkAXhGje7F1rpjPvndysLsGHzMUi6wLXhHt6E2tQMgIc0CK32IEr35/rM93pbmJ" +
            "noXo81TeIqPDxXWXOvWZmUVfkVFeBSxLyZQ4Ch8CrarSwd8EDjdo9Y9vDRTlvPrm" +
            "CqAJus7w8CFQcPBb0FeGJuI5";

    private static final String BAD_KEY = "eyJzaWQiOiI0NzY0ZDY3MS1jMDRiLTU4NzgtYjY5ZC1jMGFmMjlkNDMzMjgiLCJpYXQiOjE1NDk5NjY3ODgsImNpcyI6eyJnaXZlbm5hbWUiOiJKb2huIERvZSJ9LCJzdWIiOiJndWVzdDBAVGVjaExhYi5EV1AuZ292LnVrIiwiaXNzIjoiZHdwLWFkZnMiLCJhdWQiOiIiLCJ1c2VybmFtZSI6IkpvaG4gRG9lIiwiZXhwIjoxNTQ5OTY3Mzg3fQ==";

    private KeyLoader<String> testSubject = new StringKeyLoader();

    @Test
    public void test_loadPublicKey_ShouldReturnKeyIfInputIsValid() {
        PublicKey result = testSubject.loadPublicKey(PUBLIC_KEY);
        assertEquals("The loaded key should be in format X.509", "X.509", result.getFormat());
    }

    @Test
    public void test_loadPrivateKey_ShouldReturnKeyIfInputIsValid() {
        PrivateKey result = testSubject.loadPrivateKey(PRIVATE_KEY);
        assertEquals("The loaded key should be in format PKCS#8", "PKCS#8", result.getFormat());
    }

    @Test(expected = StringKeyLoader.KeyLoaderException.class)
    public void test_loadPublicKey_ShouldThrowExceptionIfInputInvalid() {
        testSubject.loadPublicKey(BAD_KEY);
    }

    @Test(expected = StringKeyLoader.KeyLoaderException.class)
    public void test_loadPrivateKey_ShouldThrowExceptionIfInputInvalid() {
        testSubject.loadPrivateKey(BAD_KEY);
    }

}
