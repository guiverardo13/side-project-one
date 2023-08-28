package com.guilherme.travelguide.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guilherme.travelguide.security.model.Authority;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

    public class User {

        private int userId;
        private String username;
        private String firstName;
        private String lastName;
        @JsonIgnore
        private String password;
        private String email;
        private boolean activated;
        private Set<Authority> authorities = new HashSet<>();

        public User() { }

        public User(int userId, String username, String password, String authorities, String firstName, String lastName, String email) {
            this.userId = userId;
            this.username = username;
            this.password = password;
            if (authorities != null) this.setAuthorities(authorities);
            this.activated = true;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int id) {
            this.userId = id;
        }

        public String getUsername() {
            return username;
        }

        public void getFullName(String firstName, String lastName) {
            String fullName = firstName + " " + lastName;
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

        public boolean isActivated() {
            return activated;
        }

        public void setActivated(boolean activated) {
            this.activated = activated;
        }

        public Set<Authority> getAuthorities() {
            return authorities;
        }

        public void setAuthorities(Set<Authority> authorities) {
            this.authorities = authorities;
        }

        public void setAuthorities(String authorities) {
            String[] roles = authorities.split(",");
            for (String role : roles) {
                String authority = role.contains("ROLE_") ? role : "ROLE_" + role;
                this.authorities.add(new Authority(authority));
            }
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return userId == user.userId &&
                    activated == user.activated &&
                    Objects.equals(username, user.username) &&
                    Objects.equals(password, user.password) &&
                    Objects.equals(authorities, user.authorities);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, username, password, activated, authorities, email);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + userId +
                    ", username='" + username + '\'' +
                    ", activated=" + activated +
                    ", authorities=" + authorities +
                    '}';
        }
    }
