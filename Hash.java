package model;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Hash {
	public String setHash(User user) {
		String value=user.getName()+user.getPass();
		String sha256 = "";
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest(value.getBytes());
            sha256 = String.format("%040x", new BigInteger(1, result));
        } catch (Exception e){
            e.printStackTrace();
        }
		return sha256;
	}
	public static void main(String[]args) {
		User user =new User("yamada","pass");
		String str="";
		Hash hash=new Hash();
		str=hash.setHash(user);
		System.out.print(str);
	}

}

