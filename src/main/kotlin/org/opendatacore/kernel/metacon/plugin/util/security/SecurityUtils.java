package org.opendatacore.kernel.metacon.plugin.util.security;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

//JSON 

//logging
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.plugin.util.security.conf.KEYS;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.UUID;

public class SecurityUtils {
    private static final Logger _logger = Logger.getLogger(SecurityUtils.class);

    private static final String ALGORITHM = "AES";

    public static String encrypt(String valueToEnc, String customKey) throws Exception {
        _logger.info("encrypt →>> "+ customKey);

//        if (customKey.getBytes(StandardCharsets.UTF_8).length > 16) {
//            throw new IllegalArgumentException("Key length must not be greater than 16 bytes");
//        }

        Key key = new SecretKeySpec(customKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        return Base64.getEncoder().encodeToString(encValue);
    }


    public static String generateKey(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] keyBytes = skf.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    public static String generateRandomKey() {
        long mostSigBits = UUID.randomUUID().getMostSignificantBits();
        mostSigBits = Math.abs(mostSigBits);
        String key = String.valueOf(mostSigBits);

        while (key.length() < 16) {
            key += "0";
        }

        if (key.length() > 16) {
            key = key.substring(0, 16);
        }

        return key;
    }

    public static String decrypt(String encryptedValue, String customKey) throws Exception {
        Key key = new SecretKeySpec(customKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedValue);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    public static String decryptPassword(String password){
        _logger.info("Getting decrypted password");
        try {
            return SecurityUtils.decrypt(password, KEYS.SIGN_KEY);
        } catch (Exception e) {
            _logger.error("Error decrypting password : " + e.getMessage());
            return "error : " + e.getMessage() ;

        }


    }

}
