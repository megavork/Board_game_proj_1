package com.example.Board_game_proj_1.services.classes;

import com.example.Board_game_proj_1.dao.interfaces.OrderDao;
import com.example.Board_game_proj_1.dao.interfaces.UserDao;
import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.interfaces.OrderService;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import com.example.Board_game_proj_1.util.JsonConverter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("customerService")
@NoArgsConstructor
public class UserServiceImpl implements UserService, OrderService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserDao userDao;

    @Autowired
    OrderDao orderDao;

    /**
     * Return User by using Login
     * @param login
     * @return
     */
    @Override
    @Transactional
    public User findByLogin(String login) {
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
    public ResponseEntity save(User user) {
        if(userDao.findByLogin(user.getUsername()) != null || userDao.findByEmail(user.getEmail()) != null ) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new UserDto());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setToken();
        User userFromBase = userDao.save(user);

        if(!userFromBase.getToken().isEmpty())
            return ResponseEntity.status(HttpStatus.OK)
                .body(JsonConverter.toJson("token",userFromBase.getToken()));
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(userFromBase);
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
    public List<UserDto> findAllUsers() {
        return userDao.findAllUsers();
    }

    /**
     *
     * @param userDto
     * @return token or empty string if username or password is wrong
     */
    @Override
    @Transactional
    public String isAuthorized(UserDto userDto) {

        User user = findByLogin(userDto.getUsername());

        if (user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            user.enable();
            user.setToken();
            userDao.update(user);
            return user.getToken();
        } else {
            return "";
        }
    }

    @Override
    @Transactional
    public boolean disableUser(String token) {
        return userDao.disableUser(token);
    }

    @Override
    @Transactional
    public boolean create(String username, List<String> gameIds) {
        return orderDao.create(username,gameIds);
    }

    @Override
    @Transactional
    public boolean delete(String username, String orderId) {
        return orderDao.delete(username, orderId);
    }

    @Override
    @Transactional
    public List<Game> getOrders(String username) {
        return orderDao.getOrders(username);
    }
}
