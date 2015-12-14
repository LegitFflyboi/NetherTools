package org.cloudchan.resilient.cheats;

import java.util.List;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class MobAura extends CheatModule {

	private int delay;

	public MobAura() {
		super("MobAura", -1, Category.COMBAT);
	}

	@Override
	public void onUpdate() {
		if (this.isEnabled()) {
			this.killmobs();
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void killmobs() {
		List list = Wrapper.mc.theWorld.getLoadedEntityList();
		delay++;
		for (int k = 0; k < list.size(); k++) {
			Entity entity = (Entity) list.get(k);
			if(entity instanceof EntityPlayer){
				continue;
			}
			if(!entity.isEntityAlive()){
				continue;
			}
			if(entity.isDead){
				continue;
			}
			
			if(!(entity instanceof EntityMob) && !(entity instanceof EntityAnimal)){
				continue;
			}
			
			if (entity.hasCustomName()){
				continue;
			}
			
			if (Wrapper.mc.thePlayer.getDistanceToEntity(entity) < 4F && delay > 2) {
				Wrapper.mc.playerController.attackEntity(Wrapper.mc.thePlayer, entity);
				Wrapper.mc.thePlayer.swingItem();
				delay = 0;
				continue;
			}
			
		}
	}

}
