/*
 * Copyright (c) 2002-2021 meteo@m4j.ru
 */
package ru.m4j.meteo.share.misc;

import java.security.Key;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chipper {

    private static final String algorithm = "AES";
    private static final String keygenspec = "PBKDF2WithHmacSHA1";
    private static final String cipherSpec = "AES/CBC/PKCS5Padding";
    final Logger log = LoggerFactory.getLogger(Chipper.class);
    private final String password;
    private final String salt;

    public Chipper(final String password, final String salt) {
        super();
        this.password = password;
        this.salt = salt;
    }

    public static Chipper getFromEnv(final String key) {
        final String pswd = System.getenv(key + "_PASSWORD");
        final String salt = System.getenv(key + "_SALT");
        return new Chipper(pswd, salt);
    }

    public String encrypt(final String data) throws Exception {
        final Key key = getKey();
        final Cipher cipher = Cipher.getInstance(cipherSpec);
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        final byte[] encVal = cipher.doFinal(data.getBytes());
        final String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        log.info("Encrypted value of " + data + ": " + encryptedValue);
        return encryptedValue;
    }

    public String decrypt(final String encryptedData) throws Exception {
        final Key key = getKey();
        final Cipher cipher = Cipher.getInstance(cipherSpec);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        final byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        final byte[] decValue = cipher.doFinal(decodedValue);
        final String decryptedValue = new String(decValue);
        log.info("Decrypted value of " + encryptedData + ": " + decryptedValue);
        return decryptedValue;
    }

    private Key getKey() throws Exception {
        final SecretKeyFactory factory = SecretKeyFactory.getInstance(keygenspec);
        final KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
        final SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), algorithm);
    }

}
