package com.irecrutements.spring.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("1234").roles("ADMIN", "USER");
		
		auth.inMemoryAuthentication()
			.withUser("user").password("1234").roles("USER").accountLocked(false);
		
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
//			.usersByUsernameQuery("select idUtilisateur as principal, pwdUtilisateur as credentials, active from Utilisateur where emailUtilisateur=?")
//			.authoritiesByUsernameQuery("select ur.idUtilisateur as principal, ro.role as role from Utilisateur_Role as ur, role as ro where ro.idRole=ur.idRole and ur.idUtilisateur=?")
			.usersByUsernameQuery(getUserQuery())/*"select emailUtilisateur as principal, pwdUtilisateur as credentials, active from Utilisateur where emailUtilisateur=?"*/
			.authoritiesByUsernameQuery(getAuthoritiesQuery())/*"select u.emailUtilisateur as principal, ro.role as role from Utilisateur_Role as ur, role as ro, Utilisateur as u where ro.idRole=ur.idRole and ur.idUtilisateur=u.idUtilisateur and u.emailUtilisateur=?"*/
			.rolePrefix("ROLE_")
//			.passwordEncoder(new Md5PasswordEncoder());
			.passwordEncoder(new BCryptPasswordEncoder());
		
		
	}
	private String getUserQuery() {
		/*"select emailUtilisateur as principal, pwdUtilisateur as credentials, active from Utilisateur where emailUtilisateur="a@a""*/
        return "select emailUtilisateur as principal, pwdUtilisateur as credentials, true "
        		+ "from Utilisateur "
        		+ "where active=true and emailUtilisateur = ?";
    }

    private String getAuthoritiesQuery() {
    	/*"select emailUtilisateur as principal, role as role from Utilisateur_Role where emailUtilisateur = "a@a""*/
        return "select emailUtilisateur as principal, role as role "
        		+ "from Utilisateur_Role "
        		+ "where emailUtilisateur = ?";
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/villes", "/index").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/villes").hasRole("USER");
		http.exceptionHandling().accessDeniedPage("/403");
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/index").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/villes").hasAnyRole("ADMIN", "USER");
		http.exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().antMatchers("/").permitAll();*/
		
//		http.formLogin().defaultSuccessUrl("/");
//		http.logout().logoutUrl("/login?logout").permitAll().logoutSuccessUrl("/login?logout");
//		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
//		http.authorizeRequests().antMatchers("/").hasRole("ADMIN");
//		http.authorizeRequests().antMatchers("/").permitAll();
//		http.exceptionHandling().accessDeniedPage("/403");
		
		http.formLogin().loginPage("/login").permitAll();
		http.logout().permitAll();
		http.authorizeRequests().antMatchers("/admin/").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/profil").authenticated();
		http.authorizeRequests().antMatchers("/saveProfilImg").authenticated();
		http.authorizeRequests().antMatchers("/").permitAll();
		http.exceptionHandling().accessDeniedPage("/403");

		http.authorizeRequests().antMatchers("/ajouterCandidature").authenticated();
		http.authorizeRequests().antMatchers("/candidatProfil").authenticated();
		http.authorizeRequests().antMatchers("/employeurProfil").authenticated();
		http.authorizeRequests().antMatchers("/offreAuFavoris").hasRole("CANDIDAT");
		http.authorizeRequests().antMatchers("/candidatureAuFavoris").hasRole("EMPLOYEUR");
	}
	
}
