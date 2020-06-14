package com.example.SpringBanking.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;

public class ShaPasswordEncoder implements PasswordEncoder {

    public String encode(CharSequence charSequence) {
        String algorithm = "SHA";
        String password = (String) charSequence;
        byte[] plainText = password.getBytes();

        try {
        MessageDigest md = MessageDigest.getInstance(algorithm);

        md.reset();
        md.update(plainText);
        byte[] encodedPassword = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encodedPassword.length; i++) {
        if ((encodedPassword[i] & 0xff) < 0x10) {
        sb.append("0");
        }
        sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }
        return sb.toString();
        } catch (Throwable e) {
        e.printStackTrace();
        }
        return null;
        }

        public boolean matches(CharSequence charSequence, String s) {
            return s.equals(encode(charSequence));
        }
}
