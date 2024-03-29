package com.utils.lib.ss.common;

import java.math.BigDecimal;

/**
 * 数学计算类。
 * @author E
 */
public class MathUtil {
	
	/**
	 * 加法。
	 * @param a
	 * @param b
	 * @return
	 */
	public static double add(double a , double b){
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 加法。
	 * @param a
	 * @param b
	 * @return
	 */
	public static float addFloat(float a , float b){
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		return b1.add(b2).floatValue();
	}
	
	/**
	 * 加法。
	 * @param a
	 * @param b
	 * @param scale 保留小数点的位数（采用四舍五入）
	 * @return
	 */
	public static double add(double a , double b , int scale){
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		return b1.add(b2).setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	/**
	 * Returns a new BigDecimal whose value is this / divisor.
	 * As scale of the result the parameter scale is used. If rounding is required to meet the specified scale, 
	 * then the specified rounding mode roundingMode is applied.
	 * @param a 除数
	 * @param divisor 被除数
	 * @return  a new BigDecimal whose value is this / divisor.
	 */
	public static double divide(double a , double divisor){
		if (0 == divisor) {
			return 0;
		}
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(divisor);
		double result =  b1.divide(b2, 2, BigDecimal.ROUND_DOWN).doubleValue();
		return result;
	}
	
	/**
	 * Returns a new BigDecimal whose value is this / divisor.
	 * As scale of the result the parameter scale is used. If rounding is required to meet the specified scale, 
	 * then the specified rounding mode roundingMode is applied.
	 * @param a 除数
	 * @param divisor 被除数
	 * @param scale 保留小数点的位数（采用四舍五入）
	 * @return  a new BigDecimal whose value is this / divisor.
	 */
	public static double divide(double a , double divisor , int scale){
		if (0 == divisor) {
			return 0;
		}
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(divisor);
		double result =  b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
		return result;
	}
	
	/**
	 * 将米单位值转化为公里单位值。
	 * @param meter 米单位值
	 * @return 公里单位值
	 */
	public static double meter2Km(double meter){
		return divide(meter, 1000);
	}
	
	/**
	 * Double值转为Integer类型值。
	 * @param a Double值
	 * @return Integer类型值
	 */
	public static int double2Integer(double a){
		BigDecimal b1 = new BigDecimal(a);
		return b1.intValue();
	}
	
	/**
	 * Returns a new BigDecimal whose value is this * multiplicand. The scale of the result is the sum of the scales of the two arguments.
	 * @param a 
	 * @param b
	 * @return this * multiplicand.
	 */
	public static double multiply(double a , double b){
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		return b1.multiply(b2).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	/**
	 * Returns a new BigDecimal whose value is this - subtrahend. The scale of the result is the maximum of the scales of the two arguments.
	 * @param a
	 * @param b
	 * @return this - subtrahend.
	 */
	public static double subtract(double a , double b){
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		return b1.subtract(b2).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	/**
	 * 将m/s转换为KM/hour。
	 * @param meterPerSecond m/
	 * @return KM/hour
	 */
	public static double meterPerSecond2KmPerHour(double meterPerSecond){
		return multiply(meterPerSecond, 3.6);
	}

	/**
	 * a和val比较大小。
	 * @param a 比较数
	 * @param val 被比较数
	 * @return 1 大于  0等于 -1小于
	 */
	public static int compareTo(double a , double val){
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(val);
		return b1.compareTo(b2);
	}
	
	/**
	 * 对Double数进入保留newScale个小数点的四舍五入的处理。
	 * @param a
	 * @param newScale 要保留的小数点位数
	 * @return 处理后的Double
	 */
	public static double double2Scale(double a , int newScale){
//		BigDecimal bigDecimal = new BigDecimal(a);
//		bigDecimal.setScale(newScale, BigDecimal.ROUND_HALF_UP);
//		return bigDecimal.doubleValue();
		return add(a, 0, newScale);
	}
	
	/**
	 * 判断数值是否在某一范围内。
	 * @param current 数值
	 * @param min 最小值
	 * @param max 最大值
	 * @return boolean
	 */
	public static boolean rangeInDefined(int current, int min, int max) {
        return Math.max(min, current) == Math.min(current, max);
    }
	
}
