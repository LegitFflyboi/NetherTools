package org.cloudchan.resilient.gui;

import org.cloudchan.resilient.core.CheatModuleManager;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Utils;
import org.cloudchan.resilient.utils.Wrapper;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.util.EnumChatFormatting;

public class HackCommands extends GuiChat {
	
	public HackCommands(){
		super(EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + ">" + EnumChatFormatting.RESET + "" + EnumChatFormatting.DARK_GREEN + " ");
	}
	
	@Override
	public void updateScreen(){
		this.drawBackground(0xFF000000);
		this.drawCenteredString(fontRendererObj, EnumChatFormatting.GREEN + "Terminal" + EnumChatFormatting.RED, this.width/2, 100, -1);
		if(this.inputField.getCursorPosition() < 10){
			this.inputField.setCursorPosition(10);
		}
		if(this.inputField.getText().length() < 10){
			this.inputField.setText(EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + ">" + EnumChatFormatting.RESET + "" + EnumChatFormatting.DARK_GREEN + " ");
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
			input = input.trim();
			input = input.replaceAll("\\s+", " ");
			Wrapper.mc.resilient.consoleOut(this.getClass(), "[CMD] " + input);
			
			for (String cheat : CheatModuleManager.cheats.keySet()) {
				if (input.equalsIgnoreCase(cheat)) {
					CheatModuleManager.getModule(cheat).toggle();
				}
			}
			
			
			
			
		}
	}
	
	
	
}
