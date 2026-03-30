package fi.metropolia.assignment12.user;

/**
 * User class representing a user in the system.
 * For simplicity, users are created with just a username.
 */
public class User {
    
    private final String username;
    
    public User(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
    
    @Override
    public String toString() {
        return "User[" + username + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return username.equals(user.username);
    }
    
    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
