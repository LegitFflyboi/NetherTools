package org.cloudchan.resilient.cheats;

import java.util.List;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.ColorVector;
import org.cloudchan.resilient.utils.Utils;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerESP extends CheatModule {

	public PlayerESP() {
		super("PlayerESP", Category.RENDER);
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
							Utils.drawEntityESP(vec, 0.5D, 2.0D, new ColorVector(0.0F, 1.0F, 0.4F,0.2F), new ColorVector(0.0F,0.0F,0.4F), 3F);
						} else {
							Utils.drawEntityESP(vec, 0.5D, 2.0D, new ColorVector(1.0F, 1.0F, 0.0F,0.2F), new ColorVector(0.0F,0.0F,0.0F), 3F);
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
