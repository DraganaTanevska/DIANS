package mk.ukim.finki.dians.service.impl;

import mk.ukim.finki.dians.model.Role;
import mk.ukim.finki.dians.model.User;
import mk.ukim.finki.dians.repository.UserRepository;
import mk.ukim.finki.dians.service.UserService;
import mk.ukim.finki.dians.service.UserService2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl2 implements UserService2 {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;

    public UserServiceImpl2(UserRepository userRepository, PasswordEncoder passwordEncoder, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.restTemplate = restTemplate;
    }

    @Override
    public User Register(String username, String name, String surname, String email, String password, Role role) {
        String send;
        send=username+'+'+name+'+'+surname+'+'+email+'+'+password+'+'+role.name();
        User user=restTemplate.getForObject("http://localhost:9001/user/register/register/"+send,User.class);
        return user;
    }

    /*/
    @Override
    public boolean CheckIfExistLogin(String username, String password) {
        String send;
        send=username+'/'+password;
        User user=restTemplate.getForObject("http://localhost:9191/user/login/check/"+send,User.class);
        return user != null;
    }
/*/
    public User Login(String username, String password) {
        String send;
        send=username+'+'+password;
        User user=restTemplate.getForObject("http://localhost:9001/user/login/login/"+send,User.class);
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserDetails user=restTemplate.getForObject("http://localhost:9001/user/login/load/"+s,UserDetails.class);
        return user;
    }

}
