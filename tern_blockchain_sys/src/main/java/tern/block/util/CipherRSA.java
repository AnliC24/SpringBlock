package tern.block.util;



import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 基于Cipher的RSA公私钥生成工具
 * */
public class CipherRSA {
	public static final Provider provider = new BouncyCastleProvider();

    private static final String PUBLIC_KEY = "RSAPublicKey";

    private static final String PRIVATE_KEY = "RSAPrivateKey";

    private static final String charSet = "UTF-8";

    public static final String KEY_ALGORITHM = "RSA";

    // 种子,改变后,生成的密钥对会发生变化
    private static final String seedKey = "seedKey";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 生成密钥对(公钥和私钥)
     * @return
     * @throws Exception
     */
    public static synchronized Map<String, Object> generateKeyPair() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(KEY_ALGORITHM, provider);
        kpg.initialize(1024, new SecureRandom());
        KeyPair keyPair = kpg.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static PublicKey getPublicRSAKey(String modulus, String exponent)
            throws Exception {
        RSAPublicKeySpec spec = new RSAPublicKeySpec(
                new BigInteger(modulus, 16), new BigInteger(exponent, 16));
        KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM, provider);
        return kf.generatePublic(spec);
    }

    /**
     * 获取公钥
     * @param key base64加密后的公钥
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicRSAKey(String key) throws Exception {
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decryptBase64(key));
        KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM, provider);
        return kf.generatePublic(x509);
    }

    /**
     * 获取私钥
     * @param key base64加密后的私钥
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateRSAKey(String key) throws Exception {
        PKCS8EncodedKeySpec pkgs8 = new PKCS8EncodedKeySpec(decryptBase64(key));
        KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM, provider);
        return kf.generatePrivate(pkgs8);
    }

    /**
     * 加密
     * @param input 明文
     * @param publicKey 公钥
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String input, PublicKey publicKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", provider);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] re = cipher.doFinal(input.getBytes(charSet));
        return re;
    }

    /**
     * 解密
     * @param encrypted
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[]  decrypt(byte[] encrypted,  PrivateKey privateKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] re = cipher.doFinal(encrypted);

        return re;
    }


    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
            if(datas.length-offSet > maxBlock){
                buff = cipher.doFinal(datas, offSet, maxBlock);
            }else{
                buff = cipher.doFinal(datas, offSet, datas.length-offSet);
            }
            out.write(buff, 0, buff.length);
            i++;
            offSet = i * maxBlock; }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }

    /**
     * base64加密
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBase64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * base64解密
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBase64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBase64(key.getEncoded());
    }

    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBase64(key.getEncoded());
    }

    /**
     * 分段解密
     * @param jsonEncryptStr 密文 格式 base64(rsa(明文)),base64(rsa(明文)),base64(rsa(明文))
     * @param privateKey base64加密后的秘钥
     * @return
     * @throws Exception
     */
    public static String segmentdecrypt(String jsonEncryptStr, String privateKey) throws Exception {
        String jsonStr = "";
        String[] str = jsonEncryptStr.split(",");
        //System.out.println("数据长度："+str.length);
        if(str !=null && str.length > 0) {
            int inputLen = str.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - 1 >= 0) {
                byte[] bt = CipherRSA.decryptBase64(str[i]);
                System.out.println("Base64解密数据："+bt.toString());
                //cache = JsEncryptRSAUtil.decrypt(bt, JsEncryptRSAUtil.getPrivateKey(privateKey));
                cache = CipherRSA.decrypt(bt,getPrivateRSAKey(privateKey));
                String a = new String(cache);
                System.out.println("解密数据："+a);
                out.write(cache, 0, cache.length);
                i++;
                inputLen--;
            }


            byte[] decryptedData = out.toByteArray();
            out.close();
            jsonStr = new String(decryptedData);
        }
        return jsonStr;
    }


    public static void main (String[] args) throws Exception {
        try {

            Map<String, Object> map = CipherRSA.generateKeyPair();
            String publicKey = CipherRSA.getPublicKey(map);
            //生成一个区块
           // testBlock a = new testBlock(1,"123");
            //byte[] preStr = a.getBytes();
            byte[] preStr = "123456".getBytes();
            Cipher cipher=Cipher.getInstance("RSA");
            //加密
            cipher.init(Cipher.ENCRYPT_MODE, CipherRSA.getPublicRSAKey(publicKey));
            byte[] enBytes = cipher.doFinal(preStr);

            String privateKey = CipherRSA.getPrivateKey(map);
            System.out.println("公钥: \n\r" + publicKey);
            System.out.println("私钥： \n\r" + privateKey);

            Cipher cipher1 =Cipher.getInstance("RSA");
            cipher1.init(Cipher.DECRYPT_MODE, CipherRSA.getPrivateRSAKey(privateKey));
            byte[] dcStr = cipher1.doFinal(enBytes);
            String result = new String(dcStr);
            System.out.println(result);
        } catch (Exception e) {
//            log.error(DataTypeConstant.MOD_CODE_SUBTYPE16, "getTokenServlet", "用户生成公钥接口异常.", e);
//            comRes.setErrMsg("用户TOKEN生成失败！");
        }

    }

}
