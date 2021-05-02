package by.bsu.tp.lab2.model.dto;

import by.bsu.tp.lab2.model.User;
import lombok.Data;

public enum UserDto {
    ;

    private interface Id {
        Long getId();
    }

    private interface Username {
        String getUsername();
    }

    private interface Password {
        String getPassword();
    }

    private interface FirstName {
        String getFirstName();
    }

    private interface LastName {
        String getLastName();
    }

    public enum Request {
        ;

        @Data
        public static class SignUp implements Username, Password, FirstName, LastName {
            private String username;
            private String password;
            private String passwordControl;
            private String firstName;
            private String lastName;

            public User convert() {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                return user;
            }
        }
    }
}
