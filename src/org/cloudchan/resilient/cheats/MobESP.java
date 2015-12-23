package org.cloudchan.resilient.cheats;

import java.util.List;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.ColorVector;
import org.cloudchan.resilient.utils.Utils;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.util.vector.Vector3f;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;

public class MobESP extends CheatModule {

	public MobESP() {
		super("MobESP", Category.RENDER);
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public void onRender(){
		if(this.isEnabled()){
			List list = Wrapper.mc.theWorld.loadedEntityList;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityLivingBase) {
					EntityLivingBase entity = (EntityLivingBase) list.get(i);

					if (Wrapper.mc.thePlayer.getDistanceToEntity(entity) <= 60.0F) {
						if (entity != Wrapper.mc.thePlayer) {
							Vector3f vec = Utils.getRealEntityCoords(entity);
							
							
							//Passive
							if(entity instanceof EntityAnimal){
								if(entity instanceof EntityHorse){
									Utils.drawEntityESP(vec, 1.0D, 1.5D, new ColorVector(0.4F,1.0F,0.4F,0.2F), new ColorVector(0.0F,0.0F,0.0F), 1F);
								} else 
								
								if(entity instanceof EntitySheep){
									Utils.drawEntityESP(vec, 0.8D, 1.5D, new ColorVector(0.4F,1.0F,0.4F,0.2F), new ColorVector(0.0F,0.0F,0.0F), 1F);
								} else 
								
								if(entity instanceof EntityCow){
									Utils.drawEntityESP(vec, 0.8D, 1.5D, new ColorVector(0.4F,1.0F,0.4F,0.2F), new ColorVector(0.0F,0.0F,0.0F), 1F);
								} else { 
								
									Utils.drawEntityESP(vec, 0.5D, 1.0D, new ColorVector(0.4F,1.0F,0.4F,0.2F), new ColorVector(0.0F,0.0F,0.0F), 1F);
								}
							}
							
							// Hostile
							if(entity instanceof EntityMob){
								
								if(entity instanceof EntityBlaze){
									Utils.drawEntityESP(vec, 0.5D, 1.8D, new ColorVector(1.0F,0.2F,0.2F,0.3F), new ColorVector(0.0F,0.0F,0.0F), 2F);
								} else
									
								if(entity instanceof EntityZombie){
									Utils.drawEntityESP(vec, 0.5D, 1.9D, new ColorVector(1.0F,0.2F,0.2F,0.3F), new ColorVector(0.0F,0.0F,0.0F), 1F);
								} else
									
								if(entity instanceof EntitySkeleton){
									Utils.drawEntityESP(vec, 0.5D, 1.9D, new ColorVector(1.0F,0.2F,0.2F,0.3F), new ColorVector(0.0F,0.0F,0.0F), 1F);
								} else
									
								if(entity instanceof EntityCreeper){
									Utils.drawEntityESP(vec, 0.5D, 1.9D, new ColorVector(1.0F,0.2F,0.2F,0.3F), new ColorVector(0.0F,0.0F,0.0F), 1F);
								} else
								
								if(entity instanceof EntitySpider){
									Utils.drawEntityESP(vec, 1.0D, 1.0D, new ColorVector(1.0F,0.2F,0.2F,0.3F), new ColorVector(0.0F,0.0F,0.0F), 1F);
								} else
									
								if(entity instanceof EntitySilverfish){
									Utils.drawEntityESP(vec, 0.3D, 0.3D, new ColorVector(1.0F,0.2F,0.2F,0.3F), new ColorVector(0.0F,0.0F,0.0F), 1F);
								} else
									
								if(entity instanceof EntityEndermite){
									Utils.drawEntityESP(vec, 0.3D, 0.3D, new ColorVector(1.0F,0.2F,0.2F,0.3F), new ColorVector(0.0F,0.0F,0.0F), 1F);
								} else
								
								if(entity instanceof EntityWitch){
									Utils.drawEntityESP(vec, 0.5D, 2.4D, new ColorVector(1.0F,0.2F,0.2F,0.3F), new ColorVector(0.0F,0.0F,0.0F), 2F);
								} else
								
								if(entity instanceof EntityWither){
									Utils.drawEntityESP(vec, 1.5D, 2.8D, new ColorVector(1.0F,0.2F,0.2F,0.3F), new ColorVector(1.0F,0.4F,0.0F), 3F);
								} else {
									Utils.drawEntityESP(vec, 1.5D, 2.8D, new ColorVector(1.0F,0.2F,0.2F,0.3F), new ColorVector(0.0F,0.0F,0.0F), 1F);
								}
							}
							
							if(entity instanceof EntityGhast){
								Utils.drawEntityESP(vec, 2.5D, 4.5D, new ColorVector(1.0F,0.2F,0.2F,0.3F), new ColorVector(0.0F,0.0F,0.0F), 2F);
							}
							
							if(entity instanceof EntityDragon){
								Utils.drawEntityESP(vec, 6.0D, 8.0D, new ColorVector(0.8F,0.0F,0.0F,0.3F), new ColorVector(1.0F,0.4F,0.0F), 3F);
							}
							
							
						}
					}
				}
			}
		}
	}
	
	
	
	
}
