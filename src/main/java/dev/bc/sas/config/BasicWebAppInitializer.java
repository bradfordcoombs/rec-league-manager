package dev.bc.sas.config;

import org.h2.server.web.JakartaWebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import dev.bc.sas.ApplicationConfig;
import jakarta.servlet.ServletContext;

class BasicWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

	private static final Logger logger = LoggerFactory.getLogger(BasicWebAppInitializer.class);

	public BasicWebAppInitializer() {
		super(ApplicationConfig.class);
	}

	@Override
	public void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		logger.info("Registering h2 servlet");
		var h2ServletRegistration = servletContext.addServlet("h2-console", new JakartaWebServlet());
		h2ServletRegistration.setLoadOnStartup(1);
		h2ServletRegistration.addMapping("/console/*");
	}

}