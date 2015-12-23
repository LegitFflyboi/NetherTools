package org.cloudchan.resilient.cheats;

import java.util.List;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.ColorVector;
import org.cloudchan.resilient.utils.Utils;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class BowBot extends CheatModule {
	
	private Vector3f ep;

	public BowBot() {
		super("BowBot", Category.COMBAT);
		ep = null;
	}
	
	@Override
	public void onUpdate(){
		if(this.isEnabled()){
	
			List<?> entities = Wrapper.mc.theWorld.playerEntities;
			for(int i=0;i<entities.size();i++){
				if (entities.size() > 1) {
					
					EntityPlayer entityplayer = (EntityPlayer) entities.get(1);
					
					if (entityplayer.getName().equalsIgnoreCase(Wrapper.mc.thePlayer.getName())) {
						continue;
					}
					
					if (Wrapper.mc.thePlayer.getDistanceToEntity(entityplayer) > Wrapper.mc.thePlayer.getDistanceToEntity((Entity) entities.get(i))) {
						entityplayer = (EntityPlayer) entities.get(i);
					}
					
					if (Wrapper.mc.thePlayer.getDistanceToEntity(entityplayer) > 100F){
						continue;
					}
					
					// Get distance of player to calculate height required to hit target, make other player have orange ESP
					
					float[] rotations = Utils.getRotationsNeeded(entityplayer);
					float distance = Wrapper.mc.thePlayer.getDistanceToEntity(entityplayer);
					float delta = Wrapper.mc.thePlayer.moveStrafing;
					
					float yaw = rotations[0] + (delta * 40.0F);
					float pitch = rotations[1] - (distance / 5.8F);
					
					ep = Utils.getRealEntityCoords(entityplayer);
					
					Wrapper.mc.thePlayer.rotationYaw = yaw;
					Wrapper.mc.thePlayer.rotationPitch = pitch;
					
				} else {
					ep = null;
				}
			}
		}
	}
	
	@Override
	public void onRender(){
		if(this.isEnabled()){
			
			Utils.drawEntityESP(ep, 0.5D, 2D, new ColorVector(1.0F,0.6F,0.0F,0.3F), new ColorVector(0.0F,0.0F,0.0F), 1);
			Utils.drawTracerLine(ep, new ColorVector(1.0F,0.6F,0.0F), 3.0F);
			
		}
		super.onRender();
	}
	
	
}
