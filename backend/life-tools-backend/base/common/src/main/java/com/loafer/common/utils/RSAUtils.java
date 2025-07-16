package com.loafer.common.utils;

import jakarta.annotation.PostConstruct;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.io.File;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA加解密工具类
 * @author loafer
 * @since 2025-03-08 20:13:25
 **/
@Component
public class RSAUtils {

    private static final String RSA_ALGORITHM = "RSA";
    @Value("${rsa.public-key-path:/apps/life-tools/rsa_public.key}")
    private String defaultPublicKeyPath;
    @Value("${rsa.private-key-path:/apps/life-tools/rsa_private.key}")
    private String defaultPrivateKeyPath;
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static PublicKey DEFAULT_PUBLIC_KEY;
    private static PrivateKey DEFAULT_PRIVATE_KEY;

    @PostConstruct
    public void init() throws Exception {
        // 1.从文件读取公私钥字符串
        String defaultPublicKeyStr = null;
        String defaultPrivateKeyStr = null;
        File publicKeyFile = new File(defaultPublicKeyPath);
        File privateKeyFile = new File(defaultPrivateKeyPath);

        if (publicKeyFile.exists() && privateKeyFile.exists()) {
            defaultPublicKeyStr = FileUtils.readFileToString(publicKeyFile, DEFAULT_CHARSET);
            defaultPrivateKeyStr = FileUtils.readFileToString(privateKeyFile, DEFAULT_CHARSET);
        }

        // 2.如果文件不存在或文件内容为空，则生成密钥对并保存到文件
        if (StringUtils.isAnyBlank(defaultPublicKeyStr, defaultPrivateKeyStr)) {
            KeyPair keyPair = generateKeyPair();
            defaultPublicKeyStr = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            defaultPrivateKeyStr = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
            FileUtils.writeStringToFile(publicKeyFile, defaultPublicKeyStr, DEFAULT_CHARSET);
            FileUtils.writeStringToFile(privateKeyFile, defaultPrivateKeyStr, DEFAULT_CHARSET);
        }

        DEFAULT_PUBLIC_KEY = getPublicKey(defaultPublicKeyStr);
        DEFAULT_PRIVATE_KEY = getPrivateKey(defaultPrivateKeyStr);
    }

    /**
     * 生成RSA密钥对
     * @return 包含公钥和私钥的KeyPair对象
     * @throws Exception 如果生成密钥对失败
     */
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 使用公钥加密数据
     * @param data 待加密的数据
     * @return 加密后的数据
     * @throws Exception 如果加密失败
     */
    public static String encrypt(String data) throws Exception {
        return encrypt(data, DEFAULT_PUBLIC_KEY);
    }

    /**
     * 使用公钥加密数据
     * @param data 待加密的数据
     * @param publicKey 公钥
     * @return 加密后的数据
     * @throws Exception 如果加密失败
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 使用私钥解密数据
     * @param encryptedData 加密的数据
     * @return 解密后的数据
     * @throws Exception 如果解密失败
     */
    public static String decrypt(String encryptedData) throws Exception {
        return decrypt(encryptedData, DEFAULT_PRIVATE_KEY);
    }

    /**
     * 使用私钥解密数据
     * @param encryptedData 加密的数据
     * @param privateKey 私钥
     * @return 解密后的数据
     * @throws Exception 如果解密失败
     */
    public static String decrypt(String encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

    /**
     * 将Base64编码的公钥字符串转换为PublicKey对象
     * @param publicKeyStr Base64编码的公钥字符串
     * @return PublicKey对象
     * @throws Exception 如果转换失败
     */
    public static PublicKey getPublicKey(String publicKeyStr) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 将Base64编码的私钥字符串转换为PrivateKey对象
     * @param privateKeyStr Base64编码的私钥字符串
     * @return PrivateKey对象
     * @throws Exception 如果转换失败
     */
    public static PrivateKey getPrivateKey(String privateKeyStr) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }
}