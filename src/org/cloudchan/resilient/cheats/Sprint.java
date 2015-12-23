package org.cloudchan.resilient.cheats;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Wrapper;
import net.minecraft.util.*;
import org.lwjgl.input.Keyboard;

public class Sprint extends CheatModule {

	public Sprint() {
		super("Sprint", Keyboard.KEY_K, Category.PLAYER);
	}
	
	@Override
	public void onUpdate(){
		if(this.isEnabled()){
			if(!Wrapper.mc.thePlayer.isSprinting()){
				Wrapper.mc.thePlayer.setSprinting(true);
			}
		}
	}
	
	
}
