package br.com.clusterlab.kafkaconfluentreplicatororchestrator;

import br.com.clusterlab.kafkaconfluentreplicatororchestrator.config.Credentials;
import br.com.clusterlab.kafkaconfluentreplicatororchestrator.dto.Credential;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

public class CredentialsTest {

    @Test
    void decodeCredentialsFromBase64() throws Exception {
        String json = "[{" +
                "\"username\":\"user\"," +
                "\"password\":\"pass\"," +
                "\"encoder\":\"none\"," +
                "\"role\":\"ADMIN\"," +
                "\"sourceIp\":\"1.1.1.1\"}]";
        String encoded = Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));

        Credential[] result = Credentials.getCredentials(encoded);

        assertEquals(1, result.length);
        Credential cred = result[0];
        assertEquals("user", cred.getUsername());
        assertEquals("pass", cred.getPassword());
        assertEquals("none", cred.getEncoder());
        assertEquals("ADMIN", cred.getRole());
        assertEquals("1.1.1.1", cred.getSourceIp());
    }
}
