package tern.block.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

/**
 * @program: blockChain
 * @Date: 2019/2/11 0:27
 * @Author: windC~
 * @Description:SHA256 数字签名
 */
public class Encrypt {
    //计算hash
    private static  String SHA(final String strText,final String strType)
    {
        String strResult=null;

        //加密字符串
        if(strText !=null && strText.length()>0)
        {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                //传入要加密的字符串
                messageDigest.update(strText.getBytes());
                //得到byte数组
                byte byteBuffer[] = messageDigest.digest();
                //将byte数组转换string类型
                StringBuffer strHexString = new StringBuffer();
                for(int i=0;i<byteBuffer.length;i++)
                {
                    //转换成16进制并存储在字符串中
                    String hex=Integer.toHexString(0xff & byteBuffer[i]);
                    if(hex.length() == 1)
                    {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                strResult = strHexString.toString();
            }catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
        }
        return strResult;
    }
    /**
     * 传入字符串，返回SHA-256加密字符串
     * */
    public  String getSHA256(final String strText) {
        return SHA(strText, "SHA-256");
    }
    
    /**
     * 传入字符串，返回SHA-256加密字符串
     * */
    public static  String getSHA256Simple(final String strText) {
        return SHA(strText, "SHA-256");
    }
}
