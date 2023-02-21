package domain;

import java.util.Objects;

public class User {
    private static final int USER_NAME_MIN_LENGTH = 1;
    private static final int USER_NAME_MAX_LENGTH = 5;
    private static final String USER_INVALID_NAME = "all";
    private static final String INVALID_NAME_LENGTH_MESSAGE = "이름은 1~5 글자만 가능합니다.";
    private static final String INVALID_NAME_BLANK_MESSAGE = "이름은 공백으로만 이루어지면 안됩니다.";
    private static final String INVALID_NAME_INCLUDE_MESSAGE = "이름에 all 이 포함되어서는 안됩니다.";
    private final String name;

    public User(String name) {
        validateNameLength(name);
        validateBlankName(name);
        validateInvalidName(name);

        this.name = name;
    }

    private void validateNameLength(String name) {
        if (USER_NAME_MIN_LENGTH > name.length() || USER_NAME_MAX_LENGTH < name.length()) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_MESSAGE);
        }
    }

    private void validateBlankName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(INVALID_NAME_BLANK_MESSAGE);
        }
    }

    private void validateInvalidName(String name) {
        if (name.equals(USER_INVALID_NAME)) {
            throw new IllegalArgumentException(INVALID_NAME_INCLUDE_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
