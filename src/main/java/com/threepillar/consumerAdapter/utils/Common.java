package com.threepillar.consumerAdapter.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@PropertySource("classpath:keycloak-credentials.properties")
public class Common {

    @Value("${keycloak.client_id}")
    private String keycloakClientId;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.username}")
    private String keycloakUsername;

    @Value("${keycloak.password}")
    private String keycloakPassword;

    @Value("${keycloak.url}")
    private String keycloakUrl;
}
