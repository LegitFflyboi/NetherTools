package org.cloudchan.resilient.core;
import java.util.ArrayList;
import java.util.List;

import org.cloudchan.resilient.gui.HackCommands;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import net.minecraft.client.gui.GuiChat;

public class Resilient {
	public static final String NAME = "Resilient";
	public static final double VERSION = 0.4;
	private boolean enabled;
	private ArrayList<String> friendlist;
	private ArrayList<String> enemylist;
	public CheatModuleManager cheatManager;
	
	public Resilient() {
		enabled = true;
		friendlist = new ArrayList<String>();
		enemylist = new ArrayList<String>();
		friendlist.clear();
		enemylist.clear();
		friendlist.add("Notch");
		enemylist.add("Dinnerbone");
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
		if(Input.getKeyDown(Keyboard.KEY_RCONTROL)) {
			Wrapper.mc.displayGuiScreen(new HackCommands());
		}
		
		if(Wrapper.mc.currentScreen == null && Input.getKeyDown(Keyboard.KEY_PERIOD)) {
			Wrapper.mc.displayGuiScreen(new GuiChat("."));
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
	
	public ArrayList<String> getFriendList(){
		return this.friendlist;
	}
	
	public void addFriend(String friend){
		if(this.enemylist.contains(friend)){
			this.enemylist.remove(friend);
		}
		this.friendlist.add(friend);
	}
	
	public void removeFriend(String friend){
		this.friendlist.remove(friend);
	}
	
	public ArrayList<String> getEnemyList(){
		return this.enemylist;
	}
	
	public void addEnemy(String enemy){
		if(this.friendlist.contains(enemy)){
			this.friendlist.remove(enemy);
		}
		this.enemylist.add(enemy);
	}
	
	public void removeEnemy(String enemy){
		this.enemylist.remove(enemy);
	}
	
}
