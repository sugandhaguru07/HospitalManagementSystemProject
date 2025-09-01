package com.hospital.management.serviceClasses;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hospital.management.entityClasses.UserAccount;
import com.hospital.management.repository.UserAccountRepository;
import com.hospital.management.security.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountService implements UserDetailsService {
  private final UserAccountRepository repo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserAccount u = repo.findByUserName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new org.springframework.security.core.userdetails.User(
        u.getUserName(),
        u.getEncodedPassword(),
        authorities(u.getRole()));
  }

  private Collection<SimpleGrantedAuthority> authorities(Set<Role> roles) {
    if (roles == null) return List.of();
    return roles.stream().map(r -> new SimpleGrantedAuthority(r.name())).collect(Collectors.toList());
  }
}
