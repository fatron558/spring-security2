package web.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LoginAndPassword_login")
    private LoginAndPassword loginAndPassword;

    public User() {

    }

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(String firstName, String lastName, int age, LoginAndPassword loginAndPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.loginAndPassword = loginAndPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LoginAndPassword getLoginAndPassword() {
        return loginAndPassword;
    }

    public void setLoginAndPassword(LoginAndPassword loginAndPassword) {
        this.loginAndPassword = loginAndPassword;
    }
}
