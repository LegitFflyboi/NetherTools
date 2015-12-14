package org.cloudchan.resilient.cheats;

import java.util.List;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Utils;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class MobAimBot extends CheatModule {

	public MobAimBot() {
		super("MobAimBot", -1, Category.COMBAT);
	}
	
	@Override
	public void onUpdate(){
		if(this.isEnabled()){
			List entities = Wrapper.mc.theWorld.loadedEntityList;
			
			for(int i=0;i<entities.size();i++){
				if (entities.size() > 1) {
					
					Entity entity = (Entity) entities.get(1);
					
					if (Wrapper.mc.thePlayer.getDistanceToEntity(entity) > Wrapper.mc.thePlayer.getDistanceToEntity((Entity) entities.get(i))) {
						entity = (Entity) entities.get(i);
					}
					
					if(!entity.isEntityAlive()){
						continue;
					}
					
					if(entity.isDead){
						continue;
					}
					
					if(!(entity instanceof EntityMob)){
						continue;
					}
					
					if (entity.hasCustomName()){
						continue;
					}
					
					if (!Wrapper.mc.thePlayer.canEntityBeSeen(entity)) {
						continue;
					}
				
					if (Wrapper.mc.thePlayer.getDistanceToEntity(entity) > 10.0F){
						continue;
					}
					
					Utils.faceEntity(entity);
				}
			}
		}
	}
	
	
	
	
}
