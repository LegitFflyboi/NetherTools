package org.cloudchan.resilient.utils;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;

public class Utils {
	
	private static org.lwjgl.util.glu.Sphere sphere = new org.lwjgl.util.glu.Sphere();
	
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
			Wrapper.mc.thePlayer.rotationYaw = rotations[0];
			Wrapper.mc.thePlayer.rotationPitch = rotations[1] + 1.0F; // 14
		}
	}
	
	
	//////////////////////////////////////////////////////////
	
	
	public static synchronized float[] getRotationsNeeded(Entity entity) {
		if (entity == null) {
			return null;
		}

		double diffX = entity.posX - Wrapper.mc.thePlayer.posX;
		double diffZ = entity.posZ - Wrapper.mc.thePlayer.posZ;
		double diffY;

		if (entity instanceof EntityLivingBase) {
			EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
			diffY = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Wrapper.mc.thePlayer.posY + Wrapper.mc.thePlayer.getEyeHeight());
		} else {
			diffY = (entity.getBoundingBox().minY + entity.getBoundingBox().maxY) / 2.0D - (Wrapper.mc.thePlayer.posY + Wrapper.mc.thePlayer.getEyeHeight());
		}

		double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
		float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
		float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
		return new float[] {
				Wrapper.mc.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - Wrapper.mc.thePlayer.rotationYaw),
				Wrapper.mc.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - Wrapper.mc.thePlayer.rotationPitch) };
	}
	
	public static Vector3f getRealEntityCoords(Entity entity){
		float xPos = (float) ((entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * Wrapper.mc.timer.renderPartialTicks) - Wrapper.mc.getRenderManager().renderPosX);
		float yPos = (float) ((entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * Wrapper.mc.timer.renderPartialTicks) - Wrapper.mc.getRenderManager().renderPosY);
		float zPos = (float) ((entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * Wrapper.mc.timer.renderPartialTicks) - Wrapper.mc.getRenderManager().renderPosZ);
		return new Vector3f(xPos,yPos,zPos);
	}
	
	public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		worldRenderer.startDrawing(3);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
		tessellator.draw();
		worldRenderer.startDrawing(3);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
		tessellator.draw();
		worldRenderer.startDrawing(1);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
		tessellator.draw();
	}
	
	public static void drawBoundingBox(AxisAlignedBB aa)  {
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
		tessellator.draw();
	}

	public static void drawOutlinedBlockESP(Vector3f vec, ColorVector color, float lineWidth) {
		if(vec == null){return;}
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(770, 771);
		// GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glLineWidth(lineWidth);
		GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		drawOutlinedBoundingBox(new AxisAlignedBB(vec.getX(), vec.getY(), vec.getZ(), vec.getX() + 1D, vec.getY() + 1D, vec.getZ() + 1D));
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	public static void drawBlockESP(Vector3f vec, ColorVector color, ColorVector linecolor, float lineWidth) {
		if(vec == null){return;}
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(770, 771);
		// GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		drawBoundingBox(new AxisAlignedBB(vec.getX(), vec.getY(), vec.getZ(), vec.getX() + 1D, vec.getY() + 1D, vec.getZ() + 1D));
		GL11.glLineWidth(lineWidth);
		GL11.glColor4f(linecolor.getRed(), linecolor.getGreen(), linecolor.getBlue(), linecolor.getAlpha());
		drawOutlinedBoundingBox(new AxisAlignedBB(vec.getX(), vec.getY(), vec.getZ(), vec.getX() + 1D, vec.getY() + 1D, vec.getZ() + 1D));
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	public static void drawSolidBlockESP(Vector3f vec, ColorVector color) {
		if(vec == null){return;}
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(770, 771);
		// GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		drawBoundingBox(new AxisAlignedBB(vec.getX(), vec.getY(), vec.getZ(), vec.getX() + 1D, vec.getY() + 1D, vec.getZ() + 1D));
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	public static void drawOutlinedEntityESP(Vector3f vec, double width, double height, ColorVector color) {
		if(vec == null){return;}
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(770, 771);
		// GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		drawOutlinedBoundingBox(new AxisAlignedBB(vec.getX() - width, vec.getY(), vec.getZ() - width, vec.getX() + width , vec.getY() + height, vec.getZ() + width));
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	public static void drawSolidEntityESP(Vector3f vec, double width, double height, ColorVector color) {
		if(vec == null){return;}
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(770, 771);
		// GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		drawBoundingBox(new AxisAlignedBB(vec.getX() - width, vec.getY(), vec.getZ() - width, vec.getX() + width , vec.getY() + height, vec.getZ() + width));
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	public static void drawEntityESP(Vector3f vec, double width, double height, ColorVector color, ColorVector linecolor, float linewidth) {
		if(vec == null){return;}
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(770, 771);
		// GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		drawBoundingBox(new AxisAlignedBB(vec.getX() - width, vec.getY(), vec.getZ() - width, vec.getX() + width , vec.getY() + height, vec.getZ() + width));
		GL11.glLineWidth(linewidth);
		GL11.glColor4f(linecolor.getRed(), linecolor.getGreen(), linecolor.getBlue(), linecolor.getAlpha());
		drawOutlinedBoundingBox(new AxisAlignedBB(vec.getX() - width, vec.getY(), vec.getZ() - width, vec.getX() + width , vec.getY() + height, vec.getZ() + width));
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	public static void drawTracerLine(Vector3f vec, ColorVector color, float lineWidth) {
		if(vec == null){return;}
		GlStateManager.pushMatrix();
		GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(lineWidth);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        GL11.glVertex3f(0.0F, 0.0F + Minecraft.getMinecraft().thePlayer.getEyeHeight()-0.05F, 0.0F);
        GL11.glVertex3f(vec.getX(), vec.getY(), vec.getZ());
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glTranslatef(0.0F, Wrapper.mc.thePlayer.getEyeHeight()-0.05F, 0.0F);
        GL11.glColor4f(1F, 1F, 1F, 0.1F);
        sphere.setTextureFlag(false);
        sphere.draw(0.5F,30,30);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        
        GlStateManager.popMatrix();
	}
    public static void drawCircle(int x, int y, double r, int c)
    {
        float f = ((c >> 24) & 0xff) / 255F;
        float f1 = ((c >> 16) & 0xff) / 255F;
        float f2 = ((c >> 8) & 0xff) / 255F;
        float f3 = (c & 0xff) / 255F;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_LINE_LOOP);

        for (int i = 0; i <= 360; i++)
        {
            double x2 = Math.sin(((i * Math.PI) / 180)) * r;
            double y2 = Math.cos(((i * Math.PI) / 180)) * r;
            GL11.glVertex2d(x + x2, y + y2);
        }

        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void drawFilledCircle(int x, int y, double r, int c)
    {
        float f = ((c >> 24) & 0xff) / 255F;
        float f1 = ((c >> 16) & 0xff) / 255F;
        float f2 = ((c >> 8) & 0xff) / 255F;
        float f3 = (c & 0xff) / 255F;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);

        for (int i = 0; i <= 360; i++)
        {
            double x2 = Math.sin(((i * Math.PI) / 180)) * r;
            double y2 = Math.cos(((i * Math.PI) / 180)) * r;
            GL11.glVertex2d(x + x2, y + y2);
        }

        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    public static void dr(double i, double j, double k, double l, int i1)
    {
        if (i < k)
        {
            double j1 = i;
            i = k;
            k = j1;
        }

        if (j < l)
        {
            double k1 = j;
            j = l;
            l = k1;
        }

        float f = ((i1 >> 24) & 0xff) / 255F;
        float f1 = ((i1 >> 16) & 0xff) / 255F;
        float f2 = ((i1 >> 8) & 0xff) / 255F;
        float f3 = (i1 & 0xff) / 255F;
        Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(f1, f2, f3, f);
        worldRenderer.startDrawingQuads();
        worldRenderer.addVertex(i, l, 0.0D);
        worldRenderer.addVertex(k, l, 0.0D);
        worldRenderer.addVertex(k, j, 0.0D);
        worldRenderer.addVertex(i, j, 0.0D);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }
	
}
