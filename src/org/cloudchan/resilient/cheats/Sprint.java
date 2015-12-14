package org.cloudchan.resilient.cheats;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;

import net.minecraft.util.EnumChatFormatting;

public class Sprint extends CheatModule {

	public Sprint() {
		super("Sprint", Keyboard.KEY_K, Category.PLAYER);
	}
	
	@Override
	public void onUpdate(){
		if(this.isEnabled()){
			Wrapper.mc.thePlayer.setSprinting(true);
		}
	}
	
	
}
