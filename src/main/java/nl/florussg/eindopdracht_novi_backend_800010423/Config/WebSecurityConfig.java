package nl.florussg.eindopdracht_novi_backend_800010423.Config;

import nl.florussg.eindopdracht_novi_backend_800010423.Security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        public DataSource dataSource;

        @Autowired
        public JwtRequestFilter jwtRequestFilter;

        @Autowired
        void WebSecurityConfiguration(@Lazy DataSource dataSource, @Lazy JwtRequestFilter jwtRequestFilter) {
                this.dataSource = dataSource;
                this.jwtRequestFilter = jwtRequestFilter;
        }

        //Authentication
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

                auth.jdbcAuthentication().dataSource(dataSource)
                        .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                        .authoritiesByUsernameQuery("SELECT username, authority FROM authorities AS a WHERE username=?");
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder(10);
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
                return super.authenticationManagerBean();
        }

        @Bean
        @Override
        public UserDetailsService userDetailsServiceBean() throws Exception {
                return super.userDetailsServiceBean();
        }

        //Secure endpoints with HTTP authentication
        @Override
        protected void configure(HttpSecurity http) throws Exception {

                http

                        .csrf().disable()
                        .formLogin().disable()
                        .authorizeRequests()

                        .antMatchers("/login").permitAll()

                        .antMatchers(HttpMethod.GET, "/customers/**").hasAnyRole("ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.POST, "/customers/**").hasAnyRole("ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/customers/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "customers/**").hasAnyRole("ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.PATCH, "/customers/**").hasAnyRole("ASSISTANT", "ADMIN")

                        .antMatchers(HttpMethod.GET, "/cars/**").hasAnyRole("ASSISTANT", "ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.POST, "/cars/**").hasAnyRole("ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/cars/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/cars/**").hasAnyRole("ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.PATCH, "/cars/**").hasAnyRole("ASSISTANT", "ADMIN")

                        .antMatchers(HttpMethod.GET, "/appointments/**").hasAnyRole("ASSISTANT", "ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.POST, "/appointments/new").hasAnyRole("ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.POST, "/appointments/**/repair").hasAnyRole("ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.DELETE, "/appointments/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/appointments/**").hasAnyRole("ASSISTANT", "ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.PATCH, "/appointments/**").hasAnyRole("ASSISTANT", "ADMIN", "MECHANIC")

                        .antMatchers(HttpMethod.GET, "/parts/**").hasAnyRole("ASSISTANT", "ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.POST, "/parts/new").hasAnyRole("ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/parts/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/parts/**").hasAnyRole("ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.PATCH, "/parts/**").hasAnyRole("ASSISTANT", "ADMIN")

                        .antMatchers(HttpMethod.GET, "/repairs/**").hasAnyRole("ASSISTANT", "ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.POST, "/repairs/**").hasAnyRole("ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/repairs/**").hasAnyRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/repairs/**").hasAnyRole("ASSISTANT", "ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.PATCH, "/repairs/**").hasAnyRole("ASSISTANT", "ADMIN", "MECHANIC")

                        .antMatchers("/repair/**").hasAnyRole("MECHANIC", "ADMIN")

                        .antMatchers(HttpMethod.POST, "/upload/**").hasAnyRole("ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/download/**").hasAnyRole("MECHANIC", "ADMIN", "MECHANIC")

                        .antMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "users/new").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "users/**/authorities").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PATCH, "users/**/change_password").hasAnyRole("ADMIN", "USER", "ASSISTANT", "MECHANIC")

                        .antMatchers("/**").hasRole("ADMIN")

                        .and()
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        }
}


















