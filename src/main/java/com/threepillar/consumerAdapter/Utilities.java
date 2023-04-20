package com.threepillar.consumerAdapter;

import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.threepillar.consumerAdapter.utils.Common;

import java.util.Collections;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Utilities {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Common utils;

    @GetMapping("/consumeSingleUser")
    public ResponseEntity<String> consumeSingleUser() {

        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(utils.getKeycloakUrl())
                .realm(utils.getKeycloakRealm())
                .username(utils.getKeycloakUsername())
                .password(utils.getKeycloakPassword())
                .clientId(utils.getKeycloakClientId())
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();
        AccessTokenResponse token = keycloak.tokenManager().getAccessToken();

        var headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "bearer " + token.getToken());
        var entity = new HttpEntity<>(headers);

        ResponseEntity<String> response;
        try {
            response = restTemplate
                    .exchange("http://localhost:8081/producer/api/operationSingleUser", HttpMethod.GET, entity, String.class);

            return ResponseEntity.ok("Message retrieved:" + response.getBody());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
