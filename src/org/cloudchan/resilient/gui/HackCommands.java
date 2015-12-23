package org.cloudchan.resilient.gui;

import org.cloudchan.resilient.core.CheatModuleManager;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Utils;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.EnumChatFormatting;

public class HackCommands extends GuiChat {
	
	private static String prompt = EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + ">" + EnumChatFormatting.RESET + "" + EnumChatFormatting.GREEN + " ";
	
	public HackCommands(){
		super(prompt);
	}
	
	@Override
	public void initGui(){
		Keyboard.enableRepeatEvents(true);
		this.inputField = new GuiTextField(0, this.fontRendererObj, 90, 0, this.width - 160, 12);
		this.inputField.setMaxStringLength(1000);
		this.inputField.setEnableBackgroundDrawing(true);
		this.inputField.setFocused(true);
		this.inputField.setText(prompt);
		this.inputField.setCanLoseFocus(false);
	}
	
	@Override
	public void updateScreen(){
		if(this.inputField.getCursorPosition() < 10){
			this.inputField.setCursorPosition(10);
		}
		if(this.inputField.getText().length() < 10){
			this.inputField.setText(prompt);
		}
		
		this.inputField.updateCursorCounter();
	}
	
	@Override // Override key 28 (Enter method)
	public void func_175275_f(String input){
		this.func_175281_b(input, true);
	}
	
	@Override // Override send (Send method)
	public void func_175281_b(String input, boolean send){
		if (input != null && input.length() > 10) {
			input = input.substring(10);
			Wrapper.mc.resilient.consoleOut(this.getClass(), "[CMD] " + input);
			this.mc.ingameGUI.getChatGUI().addToSentMessages("Resilient -> " + input);
			validate(input);
			
		}
	}
	
	public static void validate(String input){
		input = input.trim();
		input = input.replaceAll("\\s+", " ");
		
		
		for (CheatModule cheat : CheatModuleManager.getModules()) {
			String cname = cheat.getClass().getSimpleName();
			if (input.equalsIgnoreCase(cname)) {
				CheatModuleManager.getModule(cheat.getClass()).toggle();
			}
		}
		
		if(input.startsWith("friend")){
			String[] parts = input.split(" ");
			if(parts.length < 1){
				// Create dialog in MC
			}
		}
		
		if(input.startsWith("friend add ")){
			String[] parts = input.split(" ");
			if(parts.length > 1){
				String username = parts[2];
				Wrapper.mc.resilient.addFriend(username);
				// Create dialog in MC
			}
		}
		
		
		if(input.startsWith("friend remove ")){
			String[] parts = input.split(" ");
			if(parts.length > 1){
				String username = parts[2];
				Wrapper.mc.resilient.removeFriend(username);
				// Create dialog in MC
			}
		}
		
		if(input.startsWith("enemy")){
			String[] parts = input.split(" ");
			if(parts.length < 1){
				// Create dialog in MC
			}
		}
		
		if(input.startsWith("enemy add ")){
			String[] parts = input.split(" ");
			if(parts.length > 1){
				String username = parts[2];
				Wrapper.mc.resilient.addEnemy(username);
				// Create dialog in MC
			}
		}
		
		
		if(input.startsWith("enemy remove ")){
			String[] parts = input.split(" ");
			if(parts.length > 1){
				String username = parts[2];
				Wrapper.mc.resilient.removeEnemy(username);
				// Create dialog in MC
			}
		}
		
		
		
		
		
	}
	
	
}
