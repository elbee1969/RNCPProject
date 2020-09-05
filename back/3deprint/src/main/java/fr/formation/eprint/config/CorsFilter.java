package fr.formation.eprint.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	@Value("${3deprint.allowedOrigin}")
	private String allowedOrigin;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setHeader("Access-Control-Allow-Origin", allowedOrigin);
		response.setHeader("Access-Control-Allow-Credentials", "true");	
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Authorization, content-type");
		response.setHeader("Access-Control-Allow-Methods",
		        "GET, POST, OPTIONS, PUT, DELETE");
		chain.doFilter(servletRequest, servletResponse);
		
	}
	
	
}