package com.myapp.portalnordsyspb.auth.controller;

import com.myapp.portalnordsyspb.auth.dto.UserResponseDto;
import com.myapp.portalnordsyspb.auth.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserInfo(@AuthenticationPrincipal User user){
        if (user != null){
            return ResponseEntity.ok(new UserResponseDto(user.getName(), user.getEmail(), true));
        } else {
            return ResponseEntity.ok(new UserResponseDto("", "", false));
        }
    }

//    public boolean isUserAuthenticated() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication != null && authentication.isAuthenticated();
//    }


//    boolean answer;
//
//    @GetMapping("/api/user-role")
//    public String getUserRoles2(@AuthenticationPrincipal User user) {
//        if (user != null) {
//            // Извлекаем роли пользователя
//            StringBuilder roles = new StringBuilder();
//
//            user.getAuthorities().forEach(this::checkIsAdmin);
//            user.getAuthorities().forEach(authority -> roles.append(authority.getAuthority()).append(" "));
//            System.out.println("user.getAuthorities(): " + user.getAuthorities().toString());
//            System.out.println("Roles: " + roles.toString());
//            System.out.println("ANSWER: " + answer);
//            return "Roles: " + roles.toString();
//        }
//        System.out.println("User is not authenticated");
//        return "User is not authenticated";
//    }
//
//    private void checkIsAdmin(GrantedAuthority grantedAuthority) {
//        if (grantedAuthority.getAuthority().equals("USER")) answer = true;
//    }
}