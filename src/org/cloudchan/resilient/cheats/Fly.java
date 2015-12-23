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
			Wrapper.mc.thePlayer.fallDistance = 0.0f;
			Wrapper.mc.thePlayer.setJumping(true);
			Wrapper.mc.thePlayer.setAIMoveSpeed(6.0f);
			Wrapper.mc.thePlayer.fall(0, 0);
		}
		if(Wrapper.mc.thePlayer.onGround){
			Wrapper.mc.thePlayer.capabilities.isFlying = false;
		}
	}

	@Override
	public void onDisable() {
		Wrapper.mc.thePlayer.capabilities.isFlying = false;
		Wrapper.mc.thePlayer.fallDistance = 0.0f;
		Wrapper.mc.thePlayer.setJumping(false);
		Wrapper.mc.thePlayer.fall(1, 0);
	}

}
