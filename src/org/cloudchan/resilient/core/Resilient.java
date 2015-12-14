package org.cloudchan.resilient.core;
import java.util.ArrayList;
import java.util.List;

import org.cloudchan.resilient.gui.HackCommands;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Resilient {
	public static final String NAME = "Resilient";
	public static final double VERSION = 0.3;
	private boolean enabled;
	private ArrayList<String> friendlist;
	public CheatModuleManager cheatManager;
	
	public Resilient() {
		enabled = true;
		friendlist = new ArrayList<String>();
		friendlist.clear();
		friendlist.add("PSCA120");
		Display.setTitle(NAME + " " + VERSION + " [MC 1.8]");
		this.consoleOut(this.getClass(), "System Enabled");
		
		cheatManager = new CheatModuleManager();
		
		
		
		
		
		
	}
	
	public void consoleOut(Class source, String text) {
		String clazz = "Anonymous(?)";
		
		if(source == null || source.isAnonymousClass()){
			source = this.getClass();
		}
		
		if(!source.equals(this.getClass())){
			clazz = source.getName() + ".class"; 
		}
		
		System.out.println("[" + NAME + " -> " + clazz + "] " + text);
	}
	
	public void update(){
		
		if(Input.getKeyDown(Keyboard.KEY_RCONTROL)){
			Wrapper.mc.displayGuiScreen(new HackCommands());
		}
		
		// Update alternate input class (LAST)
		Input.update();
	}
	
	public String getColoredStatus(){
		if(this.isEnabled()){
			return "§aEnabled§r";
		}
		return "§cDisabled§r";
	}
	
	public boolean isEnabled(){
		return enabled;
	}
	
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}
	
	public List friendList(){
		return friendlist;
	}
	
	public void addFriend(String friend){
		this.friendlist.add(friend);
	}
	
	public void removeFriend(String friend){
		
	}
	
}
