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

        // Authentication

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

//                auth.inMemoryAuthentication() //TODO: Verwijderen als auth.jdbc werkt
//                        .withUser("glenn").password("password").roles("ASSISTANT");
//
//        }

                auth.jdbcAuthentication().dataSource(dataSource)
                        .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                        .authoritiesByUsernameQuery("SELECT username, authority FROM authorities AS a WHERE username=?");

        }

        // Encrypt the password
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

                //JWT token authentication met de users 'ADMIN' en 'MONTEUR'
                http

                        .httpBasic() //TODO: Is deze nodig?
                        .and()       //TODO: Is deze nodig?
                        .csrf().disable()
                        .formLogin().disable() //TODO: Is deze nodig?
                        .authorizeRequests()
                        .antMatchers("/**").hasRole("ADMIN")

                        .antMatchers("/authenticate").permitAll() //TODO: Wat is dit?

                        .antMatchers(HttpMethod.GET,"/customers/**").hasAnyRole("ASSISTANT","ADMIN")
                        .antMatchers(HttpMethod.POST,"/customers/**").hasAnyRole( "ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/customers/**").hasAnyRole( "ADMIN")
                        .antMatchers(HttpMethod.PUT, "customers/**").hasAnyRole( "ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.PATCH, "/customers/**").hasAnyRole( "ASSISTANT", "ADMIN")

                        .antMatchers(HttpMethod.GET,"/cars/**").hasAnyRole(  "ASSISTANT", "ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.POST,"/cars/**").hasAnyRole( "ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/cars/**").hasAnyRole( "ADMIN")
                        .antMatchers(HttpMethod.PUT, "/cars/**").hasAnyRole(  "ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.PATCH, "/cars/**").hasAnyRole( "ASSISTANT", "ADMIN")

                        .antMatchers(HttpMethod.GET,"/appointments/**").hasAnyRole(   "ASSISTANT","ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.POST,"/appointments/new").hasAnyRole( "ASSISTANT", "ADMIN") //add appointment
                        .antMatchers(HttpMethod.POST,"/appointments/**/repair").hasAnyRole( "ADMIN", "MECHANIC") //add repair to appointment

                        .antMatchers(HttpMethod.DELETE, "/appointments/**").hasAnyRole( "ADMIN")
                        .antMatchers(HttpMethod.PUT, "/appointments/**").hasAnyRole( "ASSISTANT","ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.PATCH, "/appointments/**").hasAnyRole( "ASSISTANT", "ADMIN", "MECHANIC")

                        .antMatchers(HttpMethod.GET,"/parts/**").hasAnyRole(   "ASSISTANT","ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.POST,"/parts/new").hasAnyRole( "ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/parts/**").hasAnyRole( "ADMIN")
                        .antMatchers(HttpMethod.PUT, "/parts/**").hasAnyRole( "ASSISTANT","ADMIN")
                        .antMatchers(HttpMethod.PATCH, "/parts/**").hasAnyRole( "ASSISTANT", "ADMIN")

                        .antMatchers(HttpMethod.GET,"/repairs/**").hasAnyRole(   "ASSISTANT","ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.POST,"/repairs/**").hasAnyRole( "ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/repairs/**").hasAnyRole( "ADMIN")
                        .antMatchers(HttpMethod.PUT, "/repairs/**").hasAnyRole( "ASSISTANT","ADMIN", "MECHANIC")
                        .antMatchers(HttpMethod.PATCH, "/repairs/**").hasAnyRole( "ASSISTANT", "ADMIN", "MECHANIC")

                        .antMatchers("/repair/**").hasAnyRole("MECHANIC", "ADMIN")

                        .antMatchers(HttpMethod.POST,"/upload/**").hasAnyRole("ASSISTANT", "ADMIN")
                        .antMatchers(HttpMethod.GET,"/download/**").hasAnyRole("MECHANIC", "ADMIN", "MECHANIC")

                        .antMatchers(HttpMethod.POST,"/users/**").hasAnyRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "users/new").hasAnyRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "users/**/authorities").hasAnyRole("ADMIN")

                        .anyRequest().denyAll()
                        .and()
                        .sessionManagement() //TODO: Wat is dit?
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //TODO: Wat is dit?

                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        }




















        //JOHAN

//        //deze heet anders
//        @Autowired
//        public CustomUserDetailsService customUserDetailsService;
//
//        //deze heet hetzelfde
//        @Autowired
//        private JwtRequestFilter jwtRequestFilter;
//
//        //hetzelfde
//        @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(customUserDetailsService);
//        }
//
//        //hetzelfde
//        @Override
//        @Bean
//        public AuthenticationManager authenticationManagerBean() throws Exception {
//            return super.authenticationManagerBean();
//        }
//
//        //hetzelfde
//        @Bean
//        public PasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//
//        //hetzelfde
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//
//                //JWT token authentication
//                http
//                        .csrf().disable()
//                        .authorizeRequests()
//                        .antMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
//                        .antMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
//                        .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
//                        .antMatchers("/authenticated").authenticated()
//                        .antMatchers("/authenticate").permitAll()
//                        .anyRequest().permitAll()
//                        .and()
//                        .sessionManagement()
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        }


}
