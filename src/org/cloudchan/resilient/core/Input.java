package org.cloudchan.resilient.core;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;


public class Input {
	
	private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
	private static ArrayList<Integer> downKeys = new ArrayList<Integer>();
	private static ArrayList<Integer> upKeys = new ArrayList<Integer>();
	
	private static ArrayList<Integer> currentMouse = new ArrayList<Integer>();
	private static ArrayList<Integer> downMouse = new ArrayList<Integer>();
	private static ArrayList<Integer> upMouse = new ArrayList<Integer>();
	
	private static final int NUM_KEYCODES = 256;
	private static final int NUM_MOUSEBUTTONS = 3;
	
	public static void update(){
		
		downMouse.clear();
		
		for(int i = 0; i < NUM_MOUSEBUTTONS; i++){
			if(getMouse(i) && !currentMouse.contains(i)){
				downMouse.add(i);
			}
		}
		
		upMouse.clear();
		
		for(int i = 0; i < NUM_MOUSEBUTTONS; i++){
			if(!getMouse(i) && currentMouse.contains(i)){
				upMouse.add(i);
			}
		}
		
		upKeys.clear();
		
		for(int i = 0; i < NUM_KEYCODES; i++){
			if(!getKey(i) && currentKeys.contains(i)){
				upKeys.add(i);
			}
		}
		
		
		downKeys.clear();
		
		for(int i = 0; i < NUM_KEYCODES; i++){
			if(getKey(i) && !currentKeys.contains(i)){
				downKeys.add(i);
			}
		}
		
		
		currentKeys.clear();
		
		for(int i = 0; i < NUM_KEYCODES; i++){
			if(getKey(i)){
				currentKeys.add(i);
			}
		}
		
		currentMouse.clear();
		
		for(int i = 0; i < NUM_MOUSEBUTTONS; i++) {
			if(getMouse(i)) {
				currentMouse.add(i);
			}
		}
		
	}
	
	private static boolean getKey(int keyCode){
		return Keyboard.isKeyDown(keyCode);
	}
	
	private static boolean getMouse(int mouseButton){
		return Mouse.isButtonDown(mouseButton);
	}
	
	/////////////////////////////////////////////////////
	
	/**
	 *  Gets if the keyboard button was pressed only for
	 *  a certain time period and will return TRUE, then FALSE.
	 * @param keyCode Keyboard.KEY_*
	 */
	public static boolean getKeyDown(int keyCode){
		return downKeys.contains(keyCode);
	}
	
	/**
	 *  Gets if the mouse button was pressed only for a
	 *  certain time period and then will return TRUE, then FALSE.
	 * @param mouseButton 0 is Left Click, 1 is Right Click, 2 is Middle Click.
	 */
	public static boolean getMouseDown(int mouseButton){
		return downMouse.contains(mouseButton);
	}
	
	/**
	 *  Gets if the keyboard button is currently being pressed,
	 *  will always return TRUE while the button is being pressed.
	 * @param keyCode Keyboard.KEY_*
	 */
	public static boolean isKeyDown(int keyCode){
		return Keyboard.isKeyDown(keyCode);
	}
	
	/**
	 *  Gets if the keyboard button is currently being pressed,
	 *  will always return TRUE while the button is being pressed.
	 *  @param mouseButton 0 is Left Click, 1 is Right Click, 2 is Middle Click.
	 */
	public static boolean isMouseDown(int mouseButton){
		return Mouse.isButtonDown(mouseButton);
	}
	
}
