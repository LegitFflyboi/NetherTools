package org.cloudchan.resilient.utils;

import java.awt.Color;
import java.util.regex.Pattern;

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
	
	public ColorVector(String hex){
		if(!hex.startsWith(Pattern.quote("#"))){
			hex = "#" + hex;
		}
		Color c = Color.decode(hex);
		this.r = (float)c.getRed();
		this.g = (float)c.getGreen();
		this.b = (float)c.getBlue();
		this.a = 1;
	}
	
	public void setHex(String hex){
		if(!hex.startsWith(Pattern.quote("#"))){
			hex = "#" + hex;
		}
		Color c = Color.decode(hex);
		this.r = (float)c.getRed();
		this.g = (float)c.getGreen();
		this.b = (float)c.getBlue();
		this.a = 1;
	}
	
	public void setRed(float r){
		this.r = r;
	}
	
	public void setGreen(float g){
		this.r = g;
	}
	
	public void setBlue(float b){
		this.r = b;
	}
	
	public void setAlpha(float a){
		this.r = a;
	}
	
	public void setIntRed(int ri){
		if(ri>255){ri=255;}
		if(ri<0){ri=0;}
		this.r = (float) (ri / 255f);
	}
	
	public void setIntGreen(int gi){
		if(gi>255){gi=255;}
		if(gi<0){gi=0;}
		this.g = (float) (gi / 255f);
	}
	
	public void setIntBlue(int bi){
		if(bi>255){bi=255;}
		if(bi<0){bi=0;}
		this.b = (float) (bi / 255f);
	}
	
	public void setIntAlpha(int ai){
		if(ai>255){ai=255;}
		if(ai<0){ai=0;}
		this.a = (float) (ai / 255f);
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
	
	public int getIntRed(){
		int i = (int) (this.r * 255);
		return i;
	}
	
	public int getIntGreen(){
		int i = (int) (this.g * 255);
		return i;
	}
	
	public int getIntBlue(){
		int i = (int) (this.b * 255);
		return i;
	}
	
	public int getIntAlpha(){
		int i = (int) (this.a * 255);
		return i;
	}
	
	public int getInt() {
		int rgb = (int) this.r;
		rgb = (rgb << 8) + (int) this.g;
		rgb = (rgb << 8) + (int) this.b;
		return rgb;
	}
	
	public String getHex(){
		return String.format("#%02x%02x%02x", r, g, b);
	}
	
	
}
