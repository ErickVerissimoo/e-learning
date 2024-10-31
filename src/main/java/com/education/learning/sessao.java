package com.education.learning;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.jdbc.config.annotation.web.http.JdbcHttpSessionConfiguration;

@Configuration
@EnableJdbcHttpSession
public class sessao extends JdbcHttpSessionConfiguration {

}
