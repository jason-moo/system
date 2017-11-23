package me.gacl.utils;

import java.util.*;

public class NumberUtils {
	
	private static final  String base = "abcdefghijklmnopqrstuvwxyz0123456789"; 

	private static int[] numbers = {3,2,44,24,44,1,45,35,25,64,32,5,63};

	public static String getRandomNum(int num){
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < num; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	}

	/**
	 * 冒泡排序
	 * 比较相邻的数据，把小的放到左边，交换位置。
	 * 最外层遍历length -1 次
	 * 内层遍历剩余元素的长度-1次
	 */

	public static void bubbleSort(){
		Integer temp = 0;
		for (int i = 0; i < numbers.length -1; i++) {
			for (int j = 0; j < numbers.length -i-1; j++) {
				if (numbers[j] < numbers[j+1]){
					temp = numbers[j+1];
					numbers[j+1] = numbers[j];
					numbers[j] = temp;
				}
			}
		}

		showNumber();
	}

	/**
	 * 选择排序
	 * 每次拿出最小的那个放在最左边
	 */

	public static void selectSort(){

		for (int i = 0; i < numbers.length; i++) {
			int minTemp = i ;
			for (int j = i + 1; j < numbers.length-1; j++) {
				if (numbers[j] <= numbers[minTemp]){
					minTemp = j;
				}
			}
			int swap = numbers[i];
			numbers[i] = numbers[minTemp];
			numbers[minTemp] = swap;
		}
		showNumber();
	}

	/**
	 * 插入排序
	 * 以左边的第一个元素作为基准，然后用后面的元素做比较，
	 * 如果比它小，则用前面元素的覆盖后面的元素，相当于向后移动一位，
	 * 如果比它大，说明此元素处于比左边大，比右边小位置。插入完成
	 *
	 */
	public static void insertSort() {
		int size = numbers.length, temp, j;
		for(int i=1; i<size; i++) {
			temp = numbers[i];
			for(j = i; j > 0; j--){
				if (temp < numbers[j-1]){
					numbers[j] = numbers[j-1];
				}else {
					break;
				}
			}
			numbers[j] = temp;
		}
		showNumber();
	}

	public static void main(String[] args)
	{
		int[] arr = new int[]{2,5,8,10,16,22,24,27,30};
		int index = halfSearch(arr,24);
		System.out.println(index);
	}

	/**
	 *@ author    Soul_Fighter
	 *@ 功   能        折半查找
	 *@ param        array
	 *@ param        key
	 *@ return        int
	 */
    /*
    思路：折半查找
    1、定义三个角标变量，最大角标max，最小角标min，中间角标mid。
    2、定义一个循环，因为不确定第一次就能得出结果。这里用while循环比较好，因为只需判断一个条件arr[mid]是否等于key的值。即：arr[mid] != key。
    3、然后判断arr[mid]的值和key的值之间的关系。如果arr[mid]>key，则max = mid - 1；如果arr[mid] < key，则min = mid +1。中间值重新去值mid = (max+min)/2。
    4、因为min不能大于max的值，所以还要加一个判断。如果min<max程序继续，否则返回-1。
    */
	public static int halfSearch(int[] arr,int key)
	{
		int max,min,mid;
		max = arr.length - 1;
		min = 0;
		mid = (max+min)/2;

		while(arr[mid] != key)
		{
			if(arr[mid] > key)
				max = mid - 1;
			else if(arr[mid] < key)
				min = mid +1;

			if(min > max)
				return -1;
			mid = (max+min)/2;
		}
		return mid;
	}

	public static void showNumber(){
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < numbers.length; i++) {
			stringBuffer.append(numbers[i]);
			stringBuffer.append(",");
		}
		stringBuffer.deleteCharAt(stringBuffer.length()-1);
		System.out.println(stringBuffer.toString());
	}

//	public static void main(String[] args) {
//		bubbleSort();
//		selectSort();
//		insertSort();

//	}

}
