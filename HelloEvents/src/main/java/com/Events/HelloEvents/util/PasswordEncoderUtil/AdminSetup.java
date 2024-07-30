package com.Events.HelloEvents.util.PasswordEncoderUtil;


import com.Events.HelloEvents.util.PasswordEncoder;

public class AdminSetup {
    public static void main(String[] args) {
        String rawPassword = "azer1234";
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        System.out.println(encodedPassword);
        // SQL command example
        String sql = "INSERT INTO users (username, password, role, email) VALUES ('admin', '" + encodedPassword + "', 'ADMIN', 'admin@example.com')";

        // Execute SQL command using your preferred method
    }
}

