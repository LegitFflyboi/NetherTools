package org.cloudchan.resilient.cheats;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Wrapper;

public class Rage extends CheatModule {
	
	public Rage(){
		super("Rage", -1, Category.PLAYER);
	}
	
	@Override
	public void onUpdate(){
		if(this.isEnabled()){
			Wrapper.mc.thePlayer.performHurtAnimation();
		}
	}
	
	
}
