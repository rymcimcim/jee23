package pl.jeeweb.zadanie23.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "privilege")
    private String privilege;

    public User() {
    }

    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivilege() {
        return privilege;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}
