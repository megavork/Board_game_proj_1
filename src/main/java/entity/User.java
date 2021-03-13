package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(schema = "board_game_sch", name = "users")
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "idUsers")
    int idUsers;
    @Column(name = "user_role")
    int user_role;
    @Column(name = "login", length = 45)
    String login;
    @Column(name = "password", length = 45)
    String password;
    @Column(name = "email")
    String email;

    /**
     * Constructor int user_role, String login, String password, String email
     * @param user_role
     * @param login
     * @param password
     * @param email
     */
    public User(int user_role, String login, String password, String email) {
        this.user_role = user_role;
        this.login = login;
        this.password = password;
        this.email = email;
    }
}
