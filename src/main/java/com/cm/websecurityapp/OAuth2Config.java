package com.cm.websecurityapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    private String clientid = "tutorialspoint";
    private String clientSecret = "my-secret-key";
    private String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIWyex4dFyPSVNoqshl40JqsY0b/8gqDDBvEGpee5sJgAXqkpEX/R6s3BYxS1iW1rvNWBIGz1NTRC+KYflcwXe8VzPtMhxx2on0Q4tSAcAGyBIFvzdAWAgqarmIt4C7E8GPMWdJCxcWHmNFMTnscd+TLVsQchbKvMGUiIZrwJZjAgMBAAECgYAC26roHOUWvfNUap8+Digfdln09Zm+Ackresi6vwnd0+GUNprQaEGm9lknA8pSKg0gZJFaGHcPSZzAblQdFwqa/umjmH/R0q8lkHfAMRM8fXWZizIaccDbtd4vjQmWkW8r5HVeW+Gfa7JQvk1oZfIdV6OAO6D1mVYyXK9YhQ8FCQJBAL4YgQzwjhW/gf0ijSXrzLx/td+uUD/O+9u3IDO9X3BVlaeAvBq+x34BEW1cDJzvbQ5aB2AKFAA9nhvN2yKDPz8CQQC0DHV442zY8Y2xwIYPhTMv+Y6R1QM+TF8yGkWeUP9M+7DQnm3r+L/pqKjkDDt/p9qJDo3FwAskb3qnYLrtOsPdAkAdhDCFkdR+C/5jD7pa6QIhQrM6yBYt0jXJJ+YYIo9vIdra52JNxNG0/vV1xZUvyP1l/RSoFOiGIQsrxgsAkOCBAkBLvs4Q3ISnQ//bXuZHUz80mKV8/oNNyy1lU54kO2kcRuqc6XnPAyghsb3Tqr3BN6l9bW0BW4iUfH26OryJHubVAkAxW5dhibGb2wBPWtakv2H1ONt55zygcMfKPN96HHkqEDTUt0qzlkpJYxuWo4USjyq9bO2VD1Xg7rkstZtQHrTb";
    private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFsnseHRcj0lTaKrIZeNCarGNG//IKgwwbxBqXnubCYAF6pKRF/0erNwWMUtYlta7zVgSBs9TU0QvimH5XMF3vFcz7TIccdqJ9EOLUgHABsgSBb83QFgIKmq5kCLeAuxPBjzFnSQsXFh5jRTE57HHfky1bEHIWyrzBlIiGa8CWYwIDAQAB\n";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientid).secret(clientSecret).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }
}
