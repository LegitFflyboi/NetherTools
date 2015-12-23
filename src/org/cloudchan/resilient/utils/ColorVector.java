package org.cloudchan.resilient.utils;

public class ColorVector {
	
	private float r;
	private float g;
	private float b;
	private float a;
	
	public ColorVector(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 1;
	}
	
	public ColorVector(float r, float g, float b, float a){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public void setRed(float r){
		this.r = r;
	}
	
	public void setGreen(float g){
		this.g = g;
	}
	
	public void setBlue(float b){
		this.b = b;
	}
	
	public void setAlpha(float a){
		this.a = a;
	}
	
	public float getRed(){
		return this.r;
	}
	
	public float getGreen(){
		return this.g;
	}
	
	public float getBlue(){
		return this.b;
	}
	
	public float getAlpha(){
		return this.a;
	}
	
}
