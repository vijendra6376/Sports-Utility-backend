//package com.sports.Sports.Service;
//
//import com.sports.Sports.model.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//public class CustomUserDetail implements UserDetails {
//
//    private final User user;
//
//    public CustomUserDetail(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // Map roles to SimpleGrantedAuthority. Role.getName() must be "ROLE_ADMIN" or "ROLE_USER"
//        List<SimpleGrantedAuthority> list = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .toList();
//        return list;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword(); // encoded password
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getEmail(); // email as username
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true; // or implement logic if you add a field
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true; // or implement logic if you add a field
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true; // or implement logic if you add a field
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true; // or user.isEnabled() if you add a field
//    }
//
//    /** Optional: expose the wrapped domain User */
//    public User getUser() {
//        return this.user;
//    }
//}
