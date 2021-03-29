package fr.formation.eprint.config;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.services.CustomUserDetailsService;

@Configuration
@EnableAuthorizationServer
@RestController // for "/me" endpoint
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	// Get custom properties from application.properties
	// Could be different between environments
	@Value("${3deprint.keyStore}")
	private String keyStore;

	@Value("${3deprint.keyPass}")
	private String keyPass;

	@Value("${3deprint.keyAlias}")
	private String keyAlias;

	@Value("${3deprint.accessTokenValiditySeconds}")
	private int accessTokenValiditySeconds;

	@Value("${3deprint.refreshTokenValiditySeconds}")
	private int refreshTokenValiditySeconds;

	// Defined as Spring bean in WebSecurity
	/**
	 * 
	 * Attempts to authenticate the passed {@link Authentication} object, returning
	 * a fully populated <code>Authentication</code> object (including granted
	 * authorities) if successful.
	 */
	private final AuthenticationManager authenticationManager;

	// user details service to authenticate users with username and
	// password from the database

	private final CustomUserDetailsService userDetailsService;

	// Custom token converter to store custom info within access token
	private final CustomAccessTokenConverter customAccessTokenConverter;

	protected AuthorizationServerConfig(AuthenticationManager authenticationManagerBean,
			CustomUserDetailsService userDetailsService, CustomAccessTokenConverter customAccessTokenConverter) {
		authenticationManager = authenticationManagerBean;
		this.userDetailsService = userDetailsService;
		this.customAccessTokenConverter = customAccessTokenConverter;
	}

	/**
	 * Token service using random UUID values for the access token and refresh token
	 * values. Specifies the token store and enables the refresh token.
	 */
	@Bean
	protected DefaultTokenServices tokenServices() {
		DefaultTokenServices services = new DefaultTokenServices();
		services.setTokenStore(tokenStore());
		services.setSupportRefreshToken(true);
		return services;
	}

	/**
	 * JwtTokenStore can read and write JWT thanks to the token converter.
	 */
	@Bean
	protected TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

	/**
	 * All in one.
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		endpoints.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain)
				.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
	}

	/**
	 * A token converter for JWT and specifies a signing key (private/public key
	 * pair).
	 */
	@Bean
	protected JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		Resource resource = new ClassPathResource(keyStore);
		char[] password = keyPass.toCharArray();
		KeyStoreKeyFactory factory = new KeyStoreKeyFactory(resource, password);
		converter.setKeyPair(factory.getKeyPair(keyAlias));
		converter.setAccessTokenConverter(customAccessTokenConverter);
		return converter;
	}

	/**
	 * Change authorization server security allowing form auth for clients (vs HTTP
	 * Basic). The client_id is sent as form parameter instead.
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.allowFormAuthenticationForClients();
	}

	/**
	 * In memory client with empty secret, application is a "private" API with a
	 * single client, but Spring forces a client authentication.
	 * <p>
	 * Authorized grant types are <i>password</i> and <i>refresh_token</i>.
	 * <p>
	 * The scope is trusted (convention) and no need to specify it during client
	 * authentication. We do not use scope-based authorization in this application.
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("3D-ePrint-app").secret(passwordEncoder().encode("")).scopes("trusted")
				.authorizedGrantTypes("password", "refresh_token")
				// .redirectUris("http://localhost:4200/oauth/callback")
				.accessTokenValiditySeconds(accessTokenValiditySeconds)
				.refreshTokenValiditySeconds(refreshTokenValiditySeconds);
	}

	/**
	 * The password encoder bean for the application. Used for client and users.
	 *
	 * @return a password encoder
	 */
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Standard enpoint returning a view of the current authenticated user.
	 * <p>
	 * Could be in a "user controller".
	 *
	 * @param authentication injected authentication object
	 * @return a view of the current authenticated user
	 */
	@GetMapping("/userInfo")
	public CustomUserInfoDto userInfo() {
		Long userId = SecurityHelper.getUserId();
		return userDetailsService.getCurrentUserInfo(userId);
	}

	/**
	 * This method will be used to check if the user has a valid token to access the
	 * resource
	 * 
	 * @param user
	 * @return user
	 */
	@GetMapping("/validateUser")
	public Principal user(Principal user) {
		return user;
	}

	/**
	 * This method will be used to check the access to the resource with an admin's
	 * role
	 * 
	 * @return a list of all users
	 */
	@GetMapping("/users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<CustomUserInfoDto> getAll() {
		return userDetailsService.getAll();
	}
}
