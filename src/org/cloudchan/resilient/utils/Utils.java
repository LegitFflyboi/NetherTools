package org.cloudchan.resilient.utils;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class Utils {
	
	private static ArrayList<Integer> idlist = new ArrayList<Integer>();
	private int id;
	private String text;
	
	public Utils(){
		
	}
	
	/**
	 * Draw text to screen
	 * @param font
	 * @param text
	 * @param x
	 * @param y
	 */
	public static void drawString(FontRenderer font, String text, float x, float y){
		font.drawString(text, x, y, 0xFFFFFFFF);
	}
	
	/**
	 * Draw a Rectangle at a location on the screen with a background color
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @param background
	 */
	public static void drawRect(int left, int top, int right, int bottom, int background){
		net.minecraft.client.gui.Gui.drawRect(left, top, right, bottom, background);
	}
	
	/**
	 * Draw a Rectangle at a location on the screen with a background color and a border
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @param background
	 * @param border_size
	 * @param border_color
	 */
	public static void drawRectWithBorder(int left, int top, int right, int bottom, int background, int border_size, int border_color){
		
		//Left border
		net.minecraft.client.gui.Gui.drawRect(left-border_size, top-border_size, left+border_size, bottom-border_size, border_color);
		
		//Top border
		net.minecraft.client.gui.Gui.drawRect(left-border_size, top-border_size, right-border_size, top+border_size, border_color);
		
		//Right border
		net.minecraft.client.gui.Gui.drawRect(right+border_size, top+border_size, right-border_size, bottom-border_size, border_color);
		
		//Bottom border
		net.minecraft.client.gui.Gui.drawRect(left-border_size, bottom-border_size, right-border_size, bottom+border_size, border_color);
		
		//Rectangle
		net.minecraft.client.gui.Gui.drawRect(left, top, right, bottom, background);
	}
	
	public static synchronized void faceEntity(Entity entity) {
		final float[] rotations = getRotationsNeeded(entity);

		if (rotations != null) {
			Minecraft.getMinecraft().thePlayer.rotationYaw = rotations[0];
			Minecraft.getMinecraft().thePlayer.rotationPitch = rotations[1] + 1.0F;// 14
		}
	}

	public static float[] getRotationsNeeded(Entity entity) {
		if (entity == null) {
			return null;
		}

		final double diffX = entity.posX - Minecraft.getMinecraft().thePlayer.posX;
		final double diffZ = entity.posZ - Minecraft.getMinecraft().thePlayer.posZ;
		double diffY;

		if (entity instanceof EntityLivingBase) {
			final EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
			diffY = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
		} else {
			diffY = (entity.getBoundingBox().minY + entity.getBoundingBox().maxY) / 2.0D - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
		}

		final double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
		final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
		final float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
		return new float[] { Minecraft.getMinecraft().thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - Minecraft.getMinecraft().thePlayer.rotationYaw), Minecraft.getMinecraft().thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - Minecraft.getMinecraft().thePlayer.rotationPitch) };
	}
	
}
