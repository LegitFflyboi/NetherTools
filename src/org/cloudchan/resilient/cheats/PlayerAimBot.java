package org.cloudchan.resilient.cheats;

import java.util.List;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Utils;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class PlayerAimBot extends CheatModule {

	public PlayerAimBot() {
		super("PlayerAimBot", -1, Category.COMBAT);
	}
	
	@Override
	public void onUpdate(){
		if(this.isEnabled()){
			List entities = Wrapper.mc.theWorld.playerEntities;
			
			for(int i=0;i<entities.size();i++){
				if (entities.size() > 1) {
					
					EntityPlayer entityplayer = (EntityPlayer) entities.get(1);
					
					if (!Wrapper.mc.thePlayer.canEntityBeSeen(entityplayer)) {
						continue;
					}
					
					if (entityplayer.getName().equalsIgnoreCase(Wrapper.mc.thePlayer.getName())) {
						continue;
					}
					
					if (Wrapper.mc.thePlayer.getDistanceToEntity(entityplayer) > Wrapper.mc.thePlayer.getDistanceToEntity((Entity) entities.get(i))) {
						entityplayer = (EntityPlayer) entities.get(i);
					}
					
					if (Wrapper.mc.thePlayer.getDistanceToEntity(entityplayer) > 15){
						continue;
					}

					Utils.faceEntity(entityplayer);
				}
			}
		}
	}
	
	
	
	
}
