package com.example.Security.config;//package com.example.kicktheworld_test.Security.config;
//
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.Optional;
//
//public class AuditorAwareImpl implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String memId = "";
//        if(authentication != null) {
//            memId = authentication.getName();
//        }
//        return Optional.of(memId);
//    }
//}
