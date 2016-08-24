package tuitanews;

import java.io.UnsupportedEncodingException;

public class TestEncode {

	public static void main(String[] args) {
		String aa = "aaa";
		
		try {
			new String(aa.getBytes(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
