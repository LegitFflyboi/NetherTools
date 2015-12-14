package org.cloudchan.resilient.gui;

import org.cloudchan.resilient.core.CheatModuleManager;
import org.cloudchan.resilient.core.Resilient;
import org.cloudchan.resilient.utils.CheatModule;
import org.cloudchan.resilient.utils.Wrapper;
import org.cloudchan.resilient.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;

public class GuiInGameHook extends GuiIngame {
	
	private int tick = 0;
	private int color = -1;
	
	public GuiInGameHook(Minecraft mcIn) {
		super(mcIn);
	}
	
	@Override
	public void func_175180_a(float p_175180_1_) {
		super.func_175180_a(p_175180_1_);
		
		if(Wrapper.mc.resilient.isEnabled()){
			color = 0xFF44FF44;
		} else {
			color = 0xFFFF0000;
		}
		Wrapper.fr.drawString(Resilient.NAME + " " + Resilient.VERSION, 2, 2, 0xFF99FF99);
		
		renderArrayList();
		//drawRadar();
	}
	
	private void renderArrayList() {
		int yCount = 25;
		if(Wrapper.mc.resilient.isEnabled()){
			for (CheatModule m : CheatModuleManager.cheats.values()) {
				m.onRender();
				GlStateManager.pushMatrix();
				GlStateManager.scale(0.5f, 0.5f, 0.5f);
				if (m.isEnabled()) {
					Wrapper.fr.drawString(EnumChatFormatting.GOLD + "[" + m.getCategory().toString().toUpperCase().substring(0, 2)+ "] " + EnumChatFormatting.GREEN + m.getCheatName() + EnumChatFormatting.RESET, 5, yCount, color);
				} else {
					Wrapper.fr.drawString(EnumChatFormatting.GOLD + "[" + m.getCategory().toString().toUpperCase().substring(0, 2)+ "] " + EnumChatFormatting.DARK_RED + m.getCheatName() + EnumChatFormatting.RESET, 5, yCount, color);
				}
				GlStateManager.popMatrix();
				yCount += 10;
			}
			Utils.drawRectWithBorder(-2, -2, 64, yCount-55, 0x60000000, 1, 0xFF00FF00);
		} else {
			Utils.drawRectWithBorder(-2, -2, 64, 12, 0x60000000, 1, 0xFFFF0000);
		}
	}

	/*
	public void drawRadar() {
		tick++;

		if (tick >= 50) {
			tick = 0;
		}

		GL11.glLineWidth(2.0F);
		RenderUtils.drawFilledCircle(Minecraft.displayWidth - 60, 60, 50, 0x77007700);
		RenderUtils.drawCircle(Minecraft.displayWidth - 60, 60, 50, 0xff000000);
		RenderUtils.drawCircle(Minecraft.displayWidth - 60, 60, 38, 0xff000000);
		RenderUtils.drawCircle(Minecraft.displayWidth - 60, 60, 25, 0xff000000);
		RenderUtils.drawCircle(Minecraft.displayWidth - 60, 60, 13, 0xff000000);
		RenderUtils.drawCircle(Minecraft.displayWidth - 60, 60, tick, 0xff00ffff);
		RenderUtils.dr(Minecraft.displayWidth - 110, 59.5, Minecraft.displayWidth - 10, 60.5, 0xff00ffff);
		RenderUtils.dr(Minecraft.displayWidth - 59.5, 10, Minecraft.displayWidth - 60.5, 110, 0xff00ffff);
		RenderUtils.drawCircle(Minecraft.displayWidth - 60, 60, 1, 0xffffffff); // Player
		List list1 = Wrapper.mc.theWorld.loadedEntityList;
		GL11.glLineWidth(1.0F);

		for (int i = 0; i < list1.size(); i++) {
			Entity entity = (Entity) list1.get(i);
			double xdis = Wrapper.mc.thePlayer.posX - entity.posX;
			double zdis = Wrapper.mc.thePlayer.posZ - entity.posZ;
			double tdis = Math.sqrt((xdis * xdis) + (zdis * zdis));
			double difInAng = MathHelper.wrapAngleTo180_double(
					Wrapper.mc.thePlayer.rotationYaw - ((Math.atan2(zdis, xdis) * 180.0D) / Math.PI));
			double finalX = Math.cos(Math.toRadians(difInAng)) * tdis;
			double finalY = -Math.sin(Math.toRadians(difInAng)) * tdis;
			GL11.glPushMatrix();
			GL11.glTranslatef(Minecraft.displayWidth - 60, 60, 0);

			if (tdis <= 100) {
				if (!(entity instanceof EntityPlayerMP)) {
					if (entity instanceof EntityPlayer) {
						RenderUtils.drawCircle((int) finalX / 2, (int) finalY / 2, 1, 0xff0000ff);
						GL11.glScalef(0.5F, 0.5F, 0.5F);
						EntityPlayer p = (EntityPlayer) entity;
						// String u = p.username;
						// this.mc.fontRenderer.drawString(u, (int)(finalX) -
						// (this.mc.fontRenderer.getStringWidth(u) / 2), (int)
						// finalY - 10, 0xffffff);
						GL11.glScalef(1F, 0.5F, 1F);
					}

					if (entity instanceof EntityAnimal) {
						RenderUtils.drawCircle((int) finalX / 2, (int) finalY / 2, 1, 0xff00ff00);
					}

					if (entity instanceof EntityMob) {
						RenderUtils.drawCircle((int) finalX / 2, (int) finalY / 2, 1, 0xffff0000);
					}

					if (entity instanceof EntitySlime) {
						RenderUtils.drawCircle((int) finalX / 2, (int) finalY / 2, 1, 0xffff88cc);
					}

					if (entity instanceof EntityVillager) {
						RenderUtils.drawCircle((int) finalX / 2, (int) finalY / 2, 1, 0xff8b4513);
					}

					if (entity instanceof EntityBat) {
						RenderUtils.drawCircle((int) finalX / 2, (int) finalY / 2, 1, 0xfff4a460);
					}

					if (entity instanceof EntitySquid) {
						RenderUtils.drawCircle((int) finalX / 2, (int) finalY / 2, 1, 0xff003399);
					}
				}
			}

			GL11.glPopMatrix();
		}
	}
	*/
	
	

}
