package com.baidu.ai.dishinform;

public class Dishinformation {
	static String name1;
	static String name2;
	static float calorie;
	static float probability;
	static String describtion;
	
	public String getDescribtion() {
		return describtion;
	}
	public void setDescribtion(String describtion) {
		Dishinformation.describtion = describtion;
	}
	public Dishinformation() {
	}
	public static void Dishinform(String name11,float caluli,float kenengxing,String name22,String describe) {
		name1=name11;
		calorie=caluli;
		probability=kenengxing;
		name2=name22;
		describtion=describe;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name22) {
		Dishinformation.name2 = name22;
	}
	public String getName1() {
		return name1;
	}
	public void setName(String name11) {
		Dishinformation.name1 = name11;
	}
	public float getCalorie() {
		return calorie;
	}
	public void setCalorie(float calorie) {
		Dishinformation.calorie = calorie;
	}
	public float getProbability() {
		return probability;
	}
	public void setProbability(float probability) {
		Dishinformation.probability = probability;
	}
	
}
