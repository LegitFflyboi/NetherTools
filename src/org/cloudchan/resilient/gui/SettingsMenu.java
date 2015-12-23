package org.cloudchan.resilient.gui;

import java.io.IOException;

import org.cloudchan.resilient.core.CheatModuleManager;
import org.cloudchan.resilient.utils.CheatModule;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;

public class SettingsMenu extends GuiScreen implements GuiYesNoCallback {
	
	private GuiScreen guiScreen;
    private GuiButton guiButton;
	
    public SettingsMenu(GuiScreen guiscreen)
    {
        this.guiScreen = guiscreen;
    }
    
	@SuppressWarnings("unchecked")
	public void initGui() {	
		this.buttonList.add(new GuiButton(10000, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, "Cheats: " + this.mc.resilient.getColoredStatus().toUpperCase()));
		this.buttonList.add(new GuiButton(10001, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, ""));
		this.buttonList.add(new GuiButton(10002, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, ""));
		this.buttonList.add(new GuiButton(10003, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, ""));
		this.buttonList.add(new GuiButton(10004, this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, ""));
		this.buttonList.add(new GuiButton(10005, this.width / 2 + 5, this.height / 6 + 120 - 6, 150, 20, ""));
		this.buttonList.add(new GuiButton(10006, this.width / 2 - 155, this.height / 6 + 144 - 6, 150, 20, ""));
		this.buttonList.add(new GuiButton(10007, this.width / 2 + 5, this.height / 6 + 144 - 6, 150, 20, ""));
		this.buttonList.add(new GuiButton(10008, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.enabled) {
			
			if (button.id == 10000) {
				// Toggle Cheats
				this.mc.resilient.setEnabled(!this.mc.resilient.isEnabled());
				if(!this.mc.resilient.isEnabled()){
					for(CheatModule m : CheatModuleManager.getModules()){
						m.setEnabled(false);
					}
				}
				this.mc.gameSettings.saveOptions();
				this.buttonList.clear();
				this.initGui();
			}
			
			if (button.id == 10001) {
				// Do something
				this.mc.gameSettings.saveOptions();
				this.buttonList.clear();
				this.initGui();
			}
			
			if (button.id == 10002) {
				// Do something
				this.mc.gameSettings.saveOptions();
				this.buttonList.clear();
				this.initGui();
			}
			
			if (button.id == 10003) {
				// Do something
				this.mc.gameSettings.saveOptions();
				this.buttonList.clear();
				this.initGui();
			}
			
			if (button.id == 10004) {
				// Do something
				this.mc.gameSettings.saveOptions();
				this.buttonList.clear();
				this.initGui();
			}
			
			if (button.id == 10005) {
				// Do something
				this.mc.gameSettings.saveOptions();
				this.buttonList.clear();
				this.initGui();
			}
			
			if (button.id == 10006) {
				// Do something
				this.mc.gameSettings.saveOptions();
				this.buttonList.clear();
				this.initGui();
			}
			
			if (button.id == 10007) {
				// Do something
				this.mc.gameSettings.saveOptions();
				this.buttonList.clear();
				this.initGui();
			}
			
			if (button.id == 10008) {
				this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.guiScreen);
			}
			
		}
	}
	
	/**
	 * Draws the screen and all the components in it. Args : mouseX, mouseY,
	 * renderPartialTicks
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, "§6§lResilient§r §b§lOptions§r", this.width / 2, 15, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

}
