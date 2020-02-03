package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.Role;
import com.sise.familyEducation.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 登录
 * @author: wxy
 * @create: 2020-02-02 11:34
 **/

@Service
public class LoginService implements UserDetailsService {

    @Resource
    LoginRepository loginRepository;

    public com.sise.familyEducation.entity.User saveRole(int id){
        return loginRepository.findById(id).get();
    }

    public UserDetails loadUserByUsername(String phone) throws
            UsernameNotFoundException {
        com.sise.familyEducation.entity.User user = loginRepository.findByPhone(phone);
        if(user == null)
            throw new UsernameNotFoundException("用户不存在");
        List<GrantedAuthority> authorities = new ArrayList<>();
        Role role = user.getRole();
        authorities.add(new SimpleGrantedAuthority(role.getRole()));
        return new User(user.getPhone(),user.getPassword(),authorities);
    }

    public com.sise.familyEducation.entity.User findByPhone(String phone){
        return loginRepository.findByPhone(phone);
    }

}
