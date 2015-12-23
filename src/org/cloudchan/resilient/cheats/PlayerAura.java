package org.cloudchan.resilient.cheats;

import java.util.List;

import org.cloudchan.resilient.utils.Category;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Wrapper;
import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class PlayerAura extends CheatModule {

	private int delay;

	public PlayerAura() {
		super("PlayerAura", Category.COMBAT);
	}

	@Override
	public void onUpdate() {
		if (this.isEnabled()) {
			this.killplayers();
		}
	}


	@SuppressWarnings("rawtypes")
	private void killplayers() {
		List list = Wrapper.mc.theWorld.playerEntities;
		delay++;

		for (int k = 0; k < list.size(); k++) {
			EntityPlayer entityPlayer = (EntityPlayer) list.get(k);
			if(Wrapper.mc.thePlayer.getDistanceToEntity(entityPlayer) < 4F){
				if (entityPlayer.getName().equalsIgnoreCase(Wrapper.mc.thePlayer.getName())) {
					continue;
				}

				if (friendListHasPlayerNameIgnoreCase(entityPlayer.getName())) {
					continue;
				}

				if (((EntityPlayer) list.get(k)).getName() == Wrapper.mc.thePlayer.getName()) {
					continue;
				}

				if (delay > 2) {
					Wrapper.mc.playerController.attackEntity(Wrapper.mc.thePlayer, entityPlayer);
					Wrapper.mc.thePlayer.swingItem();
					delay = 0;
					continue;
				}
			}
		}
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
