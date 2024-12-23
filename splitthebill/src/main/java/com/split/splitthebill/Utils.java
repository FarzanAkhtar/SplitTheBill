package com.split.splitthebill;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public abstract class Utils {
    public static String createSha256Hash(String str) throws NoSuchAlgorithmException {
        byte[] strByteArray = str.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA256");
        md.update(strByteArray);
        byte[] encryptedByteArray = md.digest();
        BigInteger bigint = new BigInteger(1, encryptedByteArray);
        return bigint.toString(16);
    }

    public static String generateRandomSalt() {
        byte[] array = new byte[8];
        new SecureRandom().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    public static String generateRandomUuid() {
        return UUID.randomUUID().toString();
    }
}
