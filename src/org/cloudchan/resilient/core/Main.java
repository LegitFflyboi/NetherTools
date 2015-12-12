package org.cloudchan.resilient.core;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;

public class Main {
	public static final String NAME = "Resilient";
	public static final double VERSION = 0.1;
	private boolean enabled;
	
	public Main(Minecraft mc) {
		enabled = true;
		GuiNewChat nc = new GuiNewChat(mc);
		nc.clearChatMessages();
		nc.addToSentMessages("-> Implementation added");
		
		
		
		
		this.consoleOut(this.getClass(), "System Enabled");
	}
	
	public void consoleOut(Class source, String text) {
		String clazz = "Anonymous(?)";
		
		if(source == null || source.isAnonymousClass()){
			source = this.getClass();
		}
		
		if(!source.equals(this.getClass())){
			clazz = source.getName() + ".class"; 
		}
		
		System.out.println("[INFO/Hack Thread -> " + clazz + "] " + text);
	}
	
	public boolean getEnabled(){
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}
	
	
	
}
