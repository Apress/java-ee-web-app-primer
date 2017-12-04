//MD5Util.java
package service.util;
/*
 * The following class will provide you with a static method
 * that returns the hex format md5 of an input string
 * See: http://en.gravatar.com/site/implement/images/java/
 * Call this as shown below:
 * String email = "someone@somewhere.com";
 * String hash = MD5Util.md5Hex(email);
 */
import java.io.*;
import java.security.*;
public class MD5Util {
  public static String hex(byte[] array) {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
      sb.append(Integer.toHexString((array[i]
          & 0xFF) | 0x100).substring(1,3));        
      }
      return sb.toString();
  }
  public static String md5Hex (String message) {
      try {
      MessageDigest md = 
          MessageDigest.getInstance("MD5");
      return hex (md.digest(message.getBytes("CP1252")));
      } catch (NoSuchAlgorithmException e) {
      } catch (UnsupportedEncodingException e) {
      }
      return null;
  } 
}
//End of MD5Util.java
