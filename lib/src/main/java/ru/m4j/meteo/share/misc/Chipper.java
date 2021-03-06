/*
 * Copyright (c) 2002-2022 meteo@m4j.ru
 */
package ru.m4j.meteo.share.misc;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Chipper {

    private static final String ALGORITHM = "AES";
    private static final String KEYGENSPEC = "PBKDF2WithHmacSHA1";
    private static final String CIPHERSPEC = "AES/CBC/PKCS5Padding";

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

    public String encrypt(final String data) throws GeneralSecurityException {
        final Key key = getKey();
        final Cipher cipher = Cipher.getInstance(CIPHERSPEC);
        byte[] bytesIV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytesIV);
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(bytesIV));
        final byte[] encVal = cipher.doFinal(data.getBytes());
        final String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        log.info("Encrypted value of " + data + ": " + encryptedValue);
        return encryptedValue;
    }

    public String decrypt(final String encryptedData) throws GeneralSecurityException {
        final Key key = getKey();
        final Cipher cipher = Cipher.getInstance(CIPHERSPEC);
        byte[] bytesIV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytesIV);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(bytesIV));
        final byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        final byte[] decValue = cipher.doFinal(decodedValue);
        final String decryptedValue = new String(decValue);
        log.info("Decrypted value of " + encryptedData + ": " + decryptedValue);
        return decryptedValue;
    }

    private Key getKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        final SecretKeyFactory factory = SecretKeyFactory.getInstance(KEYGENSPEC);
        final KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
        final SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), ALGORITHM);
    }

}
