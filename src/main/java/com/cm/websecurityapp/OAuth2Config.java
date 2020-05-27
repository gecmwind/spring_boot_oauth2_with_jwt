package com.cm.websecurityapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//    private String clientid = "tutorialspoint";
//    private String clientSecret = "my-secret-key";

    private String clientid = "clientapp";
    private String clientSecret = "112233";

    private String privateKey ="-----BEGIN RSA PRIVATE KEY-----\n" + "MIIEogIBAAKCAQEAziowl+vUneJeNDFqXpQ/K+wVPh8aJ4TzaPodxpJv1qD+zkWA\n" + "7NGmPqPd1gJB2FYwyx3QZtT0o1GY3T7l0TSjGzWPG+6gOmBiZhmnBnahmS7esUNG\n" + "ZajYqFSneX610HasKcBh/Y/E6FHDSMLYv19yNhmh9ur82DRE5fLhOUs7NAvpxPDI\n" + "JlOJfws0gPcHFXIRc7kwEqnktasEwtMrBXh1K0Z4B8W8vYn2c9l7jgsYGaZWUM64\n" + "CFw6eqKaOSiDleYmNP7eUd15AallbCsX5P9pXfXwofONgXMCDVVEaz+we57ZLgvs\n" + "ElYDz6LCI/wlBO3fYEw29rYXMtjfRs9eiSWjbQIDAQABAoIBADxe4GHsXhNpsTpJ\n" + "aiON972t4T6Srv75kKmh7Ew0n2lTNapDchBnB+5538EGYi+udZVVAqzKGWUlxMSs\n" + "4XFUn5qdIW4Ff4f4p3fMdTuQS5vmvLFdyOSGAAOy3mPWdfk4dwu22Tr9+aQqJSxO\n" + "WlgX5ALxmJsMjsPk8nP/nnI/lHOVrr4TCqLf0F3lI51dOBRvU/fK5WuU/hpueVQ3\n" + "21QH93GkBk7oXyBqn3sksIf9lPEJ++KSOlGzGvs9NAy31JpONyruoXv6KKOeSN2b\n" + "Dsrv+lsUon6S9rn1WrNbNPlXG2aNSeV4ru33ER7fRPu5Q5VzY4T5+Kvn82sHFLPq\n" + "/fOHkOUCgYEA8mbWhRY16bQg1UvX7aACQi19c0U5dhaJz16V7XkntxJF3aigyu1Y\n" + "FFsjrJyG0MRrAxMc4NY9xqvcCU6eftBDEnkfr0cTnBRI004ua2tkUqudGWXsJ3sl\n" + "QUhDWzMyL8/xd3lvMgKQ22ZcooDGdUGdTS3/R54+Vze5PG52z3tan1sCgYEA2bry\n" + "8NNVpbFPYsMwtxIQkq8w5yY40tYUxBNs73yBCfOomjnPozFMCyHYdKOBW7G3UVAM\n" + "Z47IjLiyOCfWDMz2fx2WQ5cgMEalkesjNNvohWNFJNP8w0vJ3nsd1QrNxQAiY/n4\n" + "Z4jGCYEUcuqqpNrfcD4L0NbunVoHMDD91oikytcCgYAQMHAZWV9maWJb/IHCXBJq\n" + "ixE61p+81EqF8AJBZvsRDgZehHmgs/NTALdEOvwCC2w+O1ONbKGZmDbM5FRPkec4\n" + "36APG8cigHRQsIMHJ5f+sXl/jcAU7vORBZuCBsxORywQV6rmgydbj92V+NXIVQaF\n" + "dhH7nYT6XD1h3NrQhqOJywKBgD/bYacaBFONNkA8CQyYQr4SnaAdiKrlWL/NBrUS\n" + "afIWQUvCStCNA8V8DrTew3C2JZK2jHzO1qjW/oYTwOaOMnlDny+BolImVib+tJur\n" + "J33/iGbtuYM5tiIHL8+s1H7DtFJoHN+YsKRW9D1j79pbyw5rVCHS97a0kckjhT8n\n" + "YlWPAoGACWjNOHtH8EBrNZd2hlNIaKYDwuMi9cDdFfpXnTyZKshZ7nOrjz4ef/Zc\n" + "O1gzf7KayhXZ/oDBQs9yPqEIS6EtJNkURiC4vJ4mmlcWUnBM+QCcZnxRWCaZ/UFC\n" + "Q0kvcdkKzAyV6f6HUD6mUDLSqSoMpqPjo5ij64jZpTSZzLkfATg=\n" + "-----END RSA PRIVATE KEY-----";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAziowl+vUneJeNDFqXpQ/\n" + "K+wVPh8aJ4TzaPodxpJv1qD+zkWA7NGmPqPd1gJB2FYwyx3QZtT0o1GY3T7l0TSj\n" + "GzWPG+6gOmBiZhmnBnahmS7esUNGZajYqFSneX610HasKcBh/Y/E6FHDSMLYv19y\n" + "Nhmh9ur82DRE5fLhOUs7NAvpxPDIJlOJfws0gPcHFXIRc7kwEqnktasEwtMrBXh1\n" + "K0Z4B8W8vYn2c9l7jgsYGaZWUM64CFw6eqKaOSiDleYmNP7eUd15AallbCsX5P9p\n" + "XfXwofONgXMCDVVEaz+we57ZLgvsElYDz6LCI/wlBO3fYEw29rYXMtjfRs9eiSWj\n" + "bQIDAQAB\n" + "-----END PUBLIC KEY-----";

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

    /**
     * 用于根据client查询 ClientDetails 对象，目前默认的 ClientDetails 实现 BaseClientDetails
     * 此处的方法：在系统加载时将测试使用的client对象set 至内存中，方便下一步测试
     * InMemoryClientDetailsService 提供了将 ClientDetails对象set至内存的操作，
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory().withClient(clientid).secret(new BCryptPasswordEncoder().encode(clientSecret)).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }
}
