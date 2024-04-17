package org.example.healthcare;

import java.io.Serial;
import java.io.Serializable;

public class Password implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return password;
    }
}
