package org.cloudchan.resilient.gui;

import java.util.ArrayList;
import java.util.List;

import org.cloudchan.resilient.core.CheatModuleManager;
import org.cloudchan.resilient.core.Resilient;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Utils;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.EnumChatFormatting;

public class GuiInGameHook extends GuiIngame {
	
	private int color;
	private List<CheatModule> modules;
	
	public GuiInGameHook(Minecraft mcIn) {
		super(mcIn);
		color = -1;
		modules = CheatModuleManager.getModules();
	}
	
	@Override
	public void func_175180_a(float p_175180_1_) {
		super.func_175180_a(p_175180_1_);
		
		if(Wrapper.mc.resilient.isEnabled()){
			color = 0xFFFFFFFF;
		} else {
			color = 0xFFFF0000;
		}
		Wrapper.fr.drawString(EnumChatFormatting.BOLD + Resilient.NAME + " " + Resilient.VERSION, 2, 2, color);
		Utils.drawRect(-2, -2, 76, 11, 0x80000000);
		
		
		if(Wrapper.mc.resilient.isEnabled()){
			activeCheats();
			friendEnemyList();
		}
	}
	
	private void activeCheats() {
		int ycount = 25;
		String prefix = "";
		for (CheatModule m : modules) {
			GL11.glPushMatrix();
			GL11.glScalef(0.5f, 0.5f, 0.5f);
			if(m.getKeyBind() != -1){
				prefix = EnumChatFormatting.GOLD + "[" + m.getKeyBind() + "]" + EnumChatFormatting.RESET + " ";
			}
			
			if (m.isEnabled()) {
				Wrapper.fr.drawString(prefix + EnumChatFormatting.DARK_PURPLE + "(" + m.getCategory().toString().toUpperCase().substring(0, 2) + ") " + EnumChatFormatting.GREEN + m.getCheatName().toString() + EnumChatFormatting.RESET, 5, ycount, -1);
			} else {
				Wrapper.fr.drawString(prefix + EnumChatFormatting.RED + m.getCheatName(), 5, ycount, -1);
			}
			GL11.glPopMatrix();
			ycount += 10;
			prefix = "";
		}
	}
	
	private void friendEnemyList(){
		int ycount = 25;
		Wrapper.fr.drawString(EnumChatFormatting.AQUA + "" + EnumChatFormatting.UNDERLINE + "Targets", Wrapper.mc.displayWidth-740, 2, color);
		for (String friend : Wrapper.mc.resilient.getFriendList()) {
			GL11.glPushMatrix();
			GL11.glScalef(0.76f, 0.76f, 0.76f);
			
			Wrapper.fr.drawString(EnumChatFormatting.DARK_AQUA + "[+]" + EnumChatFormatting.DARK_GREEN + " " + friend, Wrapper.mc.displayWidth-570, ycount, -1);
			
			GL11.glPopMatrix();
			ycount += 10;
		}
		
		for(String enemy : Wrapper.mc.resilient.getEnemyList()){
			GL11.glPushMatrix();
			GL11.glScalef(0.76f, 0.76f, 0.76f);
			
			Wrapper.fr.drawString(EnumChatFormatting.DARK_AQUA + "[-]" + EnumChatFormatting.DARK_RED + " " + enemy, Wrapper.mc.displayWidth-570, ycount, -1);
			
			GL11.glPopMatrix();
			ycount += 10;
		}
		
	}
	
	
	
	
	

}
