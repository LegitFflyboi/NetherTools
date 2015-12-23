package org.cloudchan.resilient.cheats;

import java.util.List;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.ColorVector;
import org.cloudchan.resilient.utils.Utils;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityAnimal;

public class MobTracer extends CheatModule {

	public MobTracer() {
		super("MobTracer", Keyboard.KEY_L, Category.RENDER);
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
								Utils.drawTracerLine(vec, new ColorVector(0.4F, 1.0F, 0.4F), 1.0F);
							}
							
							// Hostile
							if(entity instanceof EntityMob){
								
								if(entity instanceof EntityDragon){
									Utils.drawTracerLine(vec, new ColorVector(1.0F, 0.0F, 0.0F), 4.5F);
								} else
								
								if(entity instanceof EntityBlaze){
									Utils.drawTracerLine(vec, new ColorVector(1.0F, 0.3F, 0.3F), 3.5F);
								} else
								
								if(entity instanceof EntityWitch){
									Utils.drawTracerLine(vec, new ColorVector(1.0F, 0.3F, 0.3F), 3.5F);
								} else
								
								if(entity instanceof EntityWither){
									Utils.drawTracerLine(vec, new ColorVector(1.0F, 0.0F, 0.0F), 4.5F);
								} else {
									Utils.drawTracerLine(vec, new ColorVector(1.0F, 0.4F, 0.4F), 1.0F);
								}
							}
							
							
						}
					}
				}
			}
		}
		super.onRender();
	}
	
}
