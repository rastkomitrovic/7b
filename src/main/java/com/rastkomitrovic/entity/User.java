package com.rastkomitrovic.entity;

import javax.persistence.*;

@Entity
@Table(name = "tweets_user")
public class User {

    @Id
    @Column(name = "username")
    private String username;

    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username == user.username;
    }

}
