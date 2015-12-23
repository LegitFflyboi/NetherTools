package org.cloudchan.resilient.cheats;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;

public class FullBright extends CheatModule {

	public FullBright() {
		super("FullBright", Keyboard.KEY_M, Category.PLAYER);
	}
	
	@Override
	public void onUpdate(){
		if(this.isEnabled()){
			Wrapper.mc.gameSettings.gammaSetting = 100F;
		} else {
			Wrapper.mc.gameSettings.gammaSetting = 1F;
		}
	}
	
	@Override
	public void onEnable(){
		
	}
	
	@Override
	public void onDisable(){
		Wrapper.mc.gameSettings.gammaSetting = 1F;
	}
	
	
	
	
	
}
