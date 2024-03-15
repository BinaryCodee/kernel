package it.binarycodee.queue.data;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserManager {
    private Set<User> users = new HashSet<User>();

    public Set<User> getUsers() {
        return this.users;
    }

    public User getUser(Player player) {
        return this.users.stream().filter(user -> user.getUUID().equals(player.getUniqueId())).findAny().orElse(null);
    }

    public User getUser(UUID uuid) {
        return this.users.stream().filter(user -> user.getUUID().equals(uuid)).findAny().orElse(null);
    }

    public User getUser(String name) {
        return this.users.stream().filter(user -> user.getName().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    public void addUser(Player player) {
        this.users.add(new User(player));
    }

    public void removeUser(Player player) {
        this.users.remove(this.getUser(player));
    }
}