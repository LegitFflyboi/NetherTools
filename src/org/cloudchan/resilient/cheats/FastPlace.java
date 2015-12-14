package org.cloudchan.resilient.cheats;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;

public class FastPlace extends CheatModule {
	
	public FastPlace() {
		super("FastPlace", Keyboard.KEY_O, Category.PLAYER);
	}

	@Override
	public void onUpdate() {
		if (this.isEnabled()) {
			Wrapper.mc.rightClickDelayTimer = 0;
		}
	}

	@Override
	public void onDisable() {
		Wrapper.mc.rightClickDelayTimer = 6;
	}

}
