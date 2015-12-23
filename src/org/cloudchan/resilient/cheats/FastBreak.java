package org.cloudchan.resilient.cheats;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Wrapper;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class FastBreak extends CheatModule {
	
	
	public FastBreak(){
		super("FastBreak", Category.PLAYER);
	}
	
	@Override
	public void onUpdate(){
		if(this.isEnabled()){
			Wrapper.mc.thePlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 1, 10, false, false));
			Wrapper.mc.thePlayer.swingItem();
		}
	}
	
	@Override
	public void onDisable(){
		Wrapper.mc.thePlayer.removePotionEffect(Potion.digSpeed.id);
		Wrapper.mc.thePlayer.swingItem();
	}
	
	
}
