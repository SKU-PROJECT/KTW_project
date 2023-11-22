package com.example.Security.config;


import com.example.Security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    MemberService memberService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .csrf().and() //토큰 활성화
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //authorization
        http
                .authorizeHttpRequests()
//                .requestMatchers("/members/login").anonymous()
//                .requestMatchers("/members/logout").authenticated()
                .requestMatchers("/admin/**").hasAnyRole( "ADMIN")
  //              .requestMatchers("/**").permitAll() //해당 경로에 대한 권한 설정

//                .requestMatchers("/stays/**").hasAnyRole("USER", "GURU", "ADMIN", "MANAGER")
//                .requestMatchers("/api/v1/post/**").hasAnyRole("USER", "GURU", "ADMIN", "MANAGER")
//                .requestMatchers("/api/v1/reply/**").hasAnyRole("USER", "GURU", "ADMIN", "MANAGER")
//                .requestMatchers("/api/v1/message/**").hasAnyRole("USER", "GURU", "ADMIN", "MANAGER")
//                .requestMatchers("/api/v1/review/**").hasAnyRole("USER", "GURU", "ADMIN", "MANAGER")
//                .requestMatchers("/api/v1/report/**").hasAnyRole("USER", "GURU", "ADMIN", "MANAGER")
//                .requestMatchers("/api/v1/admin/**").hasAnyRole("ADMIN")

                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated();




        http.formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email") //로그인시 사용할 파라미터 이름을 email 로 설정
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) //멤버 로그아웃
                .logoutSuccessUrl("/");


        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }

}