package com.agnext.unification.utility;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "agnext.kyf.s3")
public class S3Settings {

    /**
     * environment for which S3 is used.<br>
     * <ul>
     * <li><b>dev-kyf</b> for development.</li>
     * <li><b>prod-kyf</b> for production.</li>
     * </ul>
     */
    private String environment;

    private String bucket;

    private String key;

    private String secret;

    private String url;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
