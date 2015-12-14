package org.cloudchan.resilient.cheats;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;

public class Fly extends CheatModule {
	
	public Fly() {
		super("Fly", Keyboard.KEY_F, Category.PLAYER);
	}

	@Override
	public void onUpdate() {
		if (this.isEnabled()) {
			Wrapper.mc.thePlayer.capabilities.isFlying = true;
		}
		if(Wrapper.mc.thePlayer.onGround){
			Wrapper.mc.thePlayer.capabilities.isFlying = false;
		}
	}

	@Override
	public void onDisable() {
		Wrapper.mc.thePlayer.capabilities.isFlying = false;
	}

}
