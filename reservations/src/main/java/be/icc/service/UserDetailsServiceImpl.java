package be.icc.service;

import be.icc.entity.UsersEntity;
import be.icc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;

 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UsersEntity appUser = this.userRepository.findByLogin(userName);
 
        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
 
        System.out.println("Found User: " + appUser);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        GrantedAuthority authority = new SimpleGrantedAuthority(appUser.getRolesByRoleId().getRole());
         grantList.add(authority);

 
        UserDetails userDetails = new User(appUser.getLogin(), appUser.getPassword(), grantList);
 
        return userDetails;
    }
 
}