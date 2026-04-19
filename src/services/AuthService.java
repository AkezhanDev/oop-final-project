package services;

import exceptions.AuthenticationFailedException;
import model.users.User;
import storage.DataStore;

public class AuthService {
    private DataStore dataStore;

    public AuthService() {
        this.dataStore = DataStore.getInstance();
    }

    public User authenticate(String login, String password) throws AuthenticationFailedException {
        for (User user : dataStore.getUsers()) {
            if (user.login(login, password)) {
                return user;
            }
        }
        throw new AuthenticationFailedException("Invalid login or password.");
    }
}