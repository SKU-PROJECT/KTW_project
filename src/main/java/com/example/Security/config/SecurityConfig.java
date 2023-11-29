package com.example.Security.config;

import com.example.Security.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Autowired
    MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //authorization
        http
                .authorizeHttpRequests()
//                .requestMatchers("/members/login").anonymous()
//                .requestMatchers("/members/signup").anonymous()
//                .requestMatchers("/members/logout").authenticated()
//                .requestMatchers("/admin/**").hasAnyRole( "ADMIN")
                .requestMatchers("/**").permitAll() //해당 경로에 대한 권한 설정

                .anyRequest().authenticated();

        http
                //로그인
                .formLogin()
                .loginPage("/members/login")            // 로그인 페이지 URL
                .defaultSuccessUrl("/")                 // 로그인 성공 시 이동할 URL
                .usernameParameter("memId")             // 로그인 시 사용할 파라미터 이름
                .failureUrl("/members/login/error")     // 로그인 실패 시 이동할 URL
                .and()

                //로그아웃
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))    // 로그아웃 URL
                .logoutSuccessUrl("/")                  // 로그아웃- 이동할 URL
        ;
//        http.exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        // 인증되지 않은 사용자가 리소스에 접근했을 때 수행되는 핸들러 등록
        return http.build();
    }

//    //사용자 인증
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(memberService)
//                .passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

}
