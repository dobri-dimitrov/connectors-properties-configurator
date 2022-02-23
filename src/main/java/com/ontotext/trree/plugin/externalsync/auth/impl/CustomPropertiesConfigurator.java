package com.ontotext.trree.plugin.externalsync.auth.impl;

import com.ontotext.trree.plugin.externalsync.auth.PropertiesConfigurator;

import java.util.Properties;

public class CustomPropertiesConfigurator implements PropertiesConfigurator {

    public static final String SECURITY_PROTOCOL = "security.protocol";
    public static final String SASL_JAAS_CONFIG = "sasl.jaas.config";
    public static final String SASL_MECHANISM = "sasl.mechanism";

    public enum SECURITY_PROTOCOLS {
        PLAINTEXT, SSL, SASL_PLAINTEXT, SASL_SSL
    }

    /**
     * Returns a {@link Properties} instance whose values can be used to provide authentication information.
     * <p>
     * Take care not to set properties unrelated to authentication as that may prevent the connector from functioning.
     *
     * @param url        the URL of the remote server as provided in the connector definition
     * @param instanceId the ID of the connector instance
     * @return an {@link Properties} instance or NULL if no custom properties are available.
     */
    @Override
    public Properties getProperties(String url, String instanceId) {
        Properties properties = new Properties();

        // set the necessary properties as required
        // In this example we are providing the necessary properties for SASL PLAIN authentication
        properties.setProperty(SECURITY_PROTOCOL, SECURITY_PROTOCOLS.PLAINTEXT.name());
        properties.setProperty(SASL_JAAS_CONFIG, createPlaintextJaas(url, instanceId));
        properties.setProperty(SASL_MECHANISM, "PLAIN");

        return properties;
    }

    private String createPlaintextJaas(String url, String instanceId) {
        String username = getUsername(url, instanceId);
        String password = getPassword(url, instanceId);

        return String.format("org.apache.kafka.common.security.plain.PlainLoginModule required " +
                        "username=\"%s\" password=\"%s\";",
                username.replace("\"", "\\\""),
                password.replace("\"", "\\\""));
    }

    private String getUsername(String url, String instanceId) {

        //todo implement
        throw new RuntimeException("not implemented");

    }

    private String getPassword(String url, String instanceId) {

        //todo implement
        throw new RuntimeException("not implemented");

    }
}
