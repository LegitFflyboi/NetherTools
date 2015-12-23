package org.cloudchan.resilient.cheats;

import java.util.List;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.ColorVector;
import org.cloudchan.resilient.utils.Utils;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerTracer extends CheatModule {

	public PlayerTracer() {
		super("PlayerTracer", Keyboard.KEY_L, Category.RENDER);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void onRender(){
		if(this.isEnabled()){
			List list = Wrapper.mc.theWorld.playerEntities;
			for (int i = 0; i < list.size(); i++) {
				EntityPlayer entityPlayer = (EntityPlayer) list.get(i);
				if (Wrapper.mc.thePlayer.getDistanceToEntity(entityPlayer) <= 160.0F) {
					if (entityPlayer != Wrapper.mc.thePlayer) {
						Vector3f vec = Utils.getRealEntityCoords(entityPlayer);
						
						if (friendListHasPlayerNameIgnoreCase(entityPlayer.getName())) {
							Utils.drawTracerLine(vec, new ColorVector(0.0F, 0.9F, 0.0F), 3.5F);
						} else {
							Utils.drawTracerLine(vec, new ColorVector(0.8F, 0.8F, 0.0F), 2.0F);
						}
					}
				}
			}
		}
		super.onRender();
	}
	
	private boolean friendListHasPlayerNameIgnoreCase(String playername) {
		for (int i = 0; i < Wrapper.mc.resilient.getFriendList().size(); i++) {
			String player = (String) Wrapper.mc.resilient.getFriendList().get(i);
			if (playername.equalsIgnoreCase(player)) {
				return true;
			}
		}
		return false;
	}
	
}
