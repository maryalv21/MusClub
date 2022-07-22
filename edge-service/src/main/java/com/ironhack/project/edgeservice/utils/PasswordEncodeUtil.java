package com.ironhack.project.edgeservice.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncodeUtil {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("67890"));
    }
    //$2a$10$.w21SnYs3LwpEa6Fr5mbKu.ipm8zBqWaJbksBN69tbPLAsBcA9aau - 12345 - Mafalda
    //$2a$10$od0BwTQhHPx6KU.glRGY1emwOkgUPueQprPfgkq2Ifv/doMmqyfC6 - 11111 - Susanita
    //$2a$10$o29OyhW8lgh2uAEBvi6XleTupxEQ58Y7eGw2OvXf6LbY4wIue3sHW - 67890 - Manolito
}


