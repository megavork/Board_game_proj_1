package com.example.Board_game_proj_1.services.classes;

import com.example.Board_game_proj_1.dao.interfaces.UserDao;
import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("customerService")
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /**
     * Return User by using Login
     * @param login
     * @return
     */
    @Override
    @Transactional
    public User findByLogin(String login) {
        //если принимает ответ от JSON

        return userDao.findByLogin(login);
    }

    @Override
    @Transactional
    public Optional findByToken(String token) {
        return Optional.of(userDao.findByToken(token));
    }

    /**
     * Save Object in User base
     * @param user
     */
    @Override
    @Transactional
    public User save(User user) {
        //passwordEncoder.matches(принятый, изБазы);        //для проверки пароля.
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return userDao.findByLogin(user.getUsername());
    }

    /**
     * Update one User in base
     * @param user
     */
    @Override
    @Transactional
    public boolean update(User user) {
        return userDao.update(user);
    }

    /**
     * Delete one User from base
     * @param user
     */
    @Override
    @Transactional
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    /**
     * Return all User from base
     * @return
     */
    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    @Transactional
    public String isAuthorized(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = findByLogin(userDto.getUsername());
        if(passwordEncoder.matches(user.getUsername(), user.getPassword())) {
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            System.out.println(token);
            return user.getToken();
        } else {
            return null;
        }
    }
}
