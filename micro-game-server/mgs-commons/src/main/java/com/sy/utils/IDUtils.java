package com.sy.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 各种id生成策略
 * 
 * @version 1.0
 */
public class IDUtils {

	private static final Random random = new Random();

	/**
	 * 自动生成32位的UUid，对应数据库的主键id进行插入用。
	 * 
	 * @return
	 */
	public static String getUUID() {
		/*
		 * UUID uuid = UUID.randomUUID(); String str = uuid.toString(); // 去掉"-"符号
		 * String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14,
		 * 18) + str.substring(19, 23) + str.substring(24); return temp;
		 */
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 生成随机id
	 * @param length 最大的位数(当头部为0时,位数不足)
	 * @return random.nextInt(10的length次方)
	 */
	public static int getRandom(int length) {
		return random.nextInt((int) Math.pow(10, length));
	}

	/**
	 * 生成随机id
	 * @param length 位数(位数不足时,在头部补0)
	 * @return random.nextInt(10的length次方)
	 */
	public static String getRandomStr(int length) {
		if (length < 1 || length > 99) {
			throw new RuntimeException();
		}
		if (length >= 10) {
			return String.format("%" + length + "d", getRandom(length));
		} else {
			return String.format("%0" + length + "d", getRandom(length));
		}
	}

	/**
	 * 数字异或
	 * @param n
	 * @param xor
	 * @return
	 */
	public static int xor(int n, int xor) {
		return n ^ xor;
	}

	/**
	 * 字符串数字异或
	 */
	public static String getXOR(String n, int xor) {
		return String.valueOf(Long.parseLong(n) ^ xor);
	}

	/**
	 * 图片名生成
	 */
	public static String getImageName() {
		return getTimeId(3);
	}

	/**
	 * id生成
	 */
	public static String getTimeId(int length) {
		// 取当前时间的长整形值包含毫秒
		long time = System.currentTimeMillis();
		// long time = System.nanoTime();
		// 加上两位随机数
		//Random random = new Random();
		// 如果不足前面补0
		String end = getRandomStr(length);
		return time + end;
	}

	/**
	 * 自增长id生成6为用户编号
	 */
	public static String getXorId6(int id) {
		id = id ^ 524288;
		return String.valueOf(id);
	}

	/**
	 * 6位以内的数字异或一个数后刚好6位
	 * xor异或码
	 * cap容量
	 *  	实验结果:
	 * 	异或码
	 * 	524288
	 * 	容量
	 * 	475711
	 * 
	 * 
	 * */
//	public static void main(String[] args) {
//		int xor = 0;//xor异或码
//		int cap = 0;//cap容量
//		for (int x = 0; x < 1000000; x++) {
//			int tcap = 0;
//			in: for (int i = 0; i < 1000000; i++) {
//				int value = i ^ x;
//				if (value < 100000 || value > 999999) {
//					break in;
//				} else {
//					tcap = i;
//				}
//			}
//			if (tcap > cap) {
//				cap = tcap;
//				xor = x;
//			}
//
//		}
//		System.out.println("异或码");
//		System.out.println(xor);//524288
//		System.out.println("容量");
//		System.out.println(cap);//475711
//	}

	/*public static void main(String[] args) {
		for (int i = 0; i < 5000; i++) {
			System.out.println(i^524288);
		}
	}*/
}
