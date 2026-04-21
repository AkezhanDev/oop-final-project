package model.users;

import enums.Language;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int nextId = 1;

    private int id;
    private String login;
    private String password;
    private String name;
    private Language language;

    public User() {
        this.id = nextId++;
        this.language = Language.EN;
    }

    public User(String login, String password, String name, Language language) {
        this.id = nextId++;
        this.login = login;
        this.password = password;
        this.name = name;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public static void synchronizeNextId(List<User> users) {
        int maxId = 0;

        for (User user : users) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }

        nextId = maxId + 1;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public boolean login(String login, String password) {
        return Objects.equals(this.login, login) && Objects.equals(this.password, password);
    }

    @Override
    public String toString() {
        return "User: " +
                "id=" + id +
                ", name=" + name +
                ", login=" + login +
                ", language=" + language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
