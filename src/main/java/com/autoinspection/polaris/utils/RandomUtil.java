package com.autoinspection.polaris.utils;


import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 此功能的使用说明：
 * 此功能可供其它项目产生随机数提供服务或参考。
 * 使用java.util.Random每次调用会产生相同的结果，而使用java.security.SecureRandom初始化速度又较慢，
 * 所以，首次产生随机数时会较慢。
 */
public class RandomUtil {
	
	
	private static final String ORDER_HEAD = "No";
	private static final String ORDER_DATE_FORMAT = "yyyyMMddhhmmss";
	private static final int ORDER_RANDOM_NUM = 3;

	public static String generateRandomDigitString(int aLength) {
		SecureRandom tRandom = new SecureRandom();
		long tLong;
		String aString = "";

		tRandom.nextLong();
		tLong = Math.abs(tRandom.nextLong());
		aString = (String.valueOf(tLong)).trim();
		while (aString.length() < aLength) {
			tLong = Math.abs(tRandom.nextLong());
			aString += (String.valueOf(tLong)).trim();
		}
		aString = aString.substring(0, aLength);

		return aString;
	}

	
	public static String getOrderNo(){
		StringBuffer id = new StringBuffer();
		id.append(ORDER_HEAD);
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(ORDER_DATE_FORMAT);  
		String str=sdf.format(date); 
		id.append(str);
		id.append(RandomUtil.generateRandomDigitString(ORDER_RANDOM_NUM));
		return id.toString();
		
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++)
			System.out.println(RandomUtil.generateRandomDigitString(16));
	}
}
