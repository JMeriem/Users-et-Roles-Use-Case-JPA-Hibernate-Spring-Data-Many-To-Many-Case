package ma.emsi.jpaemsi.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.emsi.jpaemsi.entities.Role;
import ma.emsi.jpaemsi.entities.User;
import ma.emsi.jpaemsi.repositories.RoleRepository;
import ma.emsi.jpaemsi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findUserByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = this.findUserByUserName(username);
        Role role = this.findUserByRoleName(roleName);
        if (user.getRoles() != null) {
            user.getRoles().add(role);
            role.getUsers().add(user);

        }
        //userRepository.save(user);
    }

    @Override
    public User authenticate(String userName, String password) {
        User user=userRepository.findByUsername(userName);
        if(user==null) throw new RuntimeException("Bad credentials");
        if (user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("Bad credentials");
    }
}

 