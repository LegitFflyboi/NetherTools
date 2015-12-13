package net.minecraft.client.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cloudchan.resilient.core.SettingsMenu;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.ISaveFormat;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {
	private static final AtomicInteger field_175373_f = new AtomicInteger(0);
	private static final Logger logger = LogManager.getLogger();
	private static final Random field_175374_h = new Random();

	/** Counts the number of screen updates. */
	private float updateCounter;

	/** The splash message. */
	private String splashText;
	private GuiButton buttonResetDemo;

	/** Timer used to rotate the panorama, increases every tick. */
	private int panoramaTimer;

	/**
	 * Texture allocated for the current viewport of the main menu's panorama
	 * background.
	 */
	private DynamicTexture viewportTexture;
	private boolean field_175375_v = true;
	private final Object field_104025_t = new Object();
	private String field_92025_p;
	private String field_146972_A;
	private String field_104024_v;
	private static final ResourceLocation splashTexts = new ResourceLocation("texts/splashes.txt");
	private static final ResourceLocation minecraftTitleTextures = new ResourceLocation(
			"textures/gui/title/minecraft.png");

	/** An array of all the paths to the panorama pictures. */
	private static final ResourceLocation[] titlePanoramaPaths = new ResourceLocation[] {
			new ResourceLocation("textures/gui/title/background/panorama_0.png"),
			new ResourceLocation("textures/gui/title/background/panorama_1.png"),
			new ResourceLocation("textures/gui/title/background/panorama_2.png"),
			new ResourceLocation("textures/gui/title/background/panorama_3.png"),
			new ResourceLocation("textures/gui/title/background/panorama_4.png"),
			new ResourceLocation("textures/gui/title/background/panorama_5.png") };
	public static final String field_96138_a = "Please click " + EnumChatFormatting.UNDERLINE + "here"
			+ EnumChatFormatting.RESET + " for more information.";
	private int field_92024_r;
	private int field_92023_s;
	private int field_92022_t;
	private int field_92021_u;
	private int field_92020_v;
	private int field_92019_w;
	private float logo_red;
	private float logo_green;
	private float logo_blue;
	private boolean logo_ring;
	private boolean logo_ging;
	private boolean logo_bing;
	private ResourceLocation field_110351_G;
	private GuiButton field_175372_K;
	private static final String __OBFID = "CL_00001154";

	public GuiMainMenu() {
		this.field_146972_A = field_96138_a;
		this.splashText = "";
		BufferedReader var1 = null;

		try {
			ArrayList var2 = Lists.newArrayList();
			var1 = new BufferedReader(new InputStreamReader(
					Minecraft.getMinecraft().getResourceManager().getResource(splashTexts).getInputStream(),
					Charsets.UTF_8));
			String var3;

			while ((var3 = var1.readLine()) != null) {
				var3 = var3.trim();

				if (!var3.isEmpty()) {
					var2.add(var3);
				}
			}

			if (!var2.isEmpty()) {
				do {
					// this.splashText =
					// (String)var2.get(field_175374_h.nextInt(var2.size()));
				} while (this.splashText.hashCode() == 125780783);
			}
		} catch (IOException var12) {
			;
		} finally {
			if (var1 != null) {
				try {
					var1.close();
				} catch (IOException var11) {
					;
				}
			}
		}

		this.updateCounter = field_175374_h.nextFloat();
		this.field_92025_p = "";

		if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.areShadersSupported()) {
			this.field_92025_p = I18n.format("title.oldgl1", new Object[0]);
			this.field_146972_A = I18n.format("title.oldgl2", new Object[0]);
			this.field_104024_v = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
		}
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	public void updateScreen() {
		++this.panoramaTimer;
	}

	/**
	 * Returns true if this GUI should pause the game when it is displayed in
	 * single-player
	 */
	public boolean doesGuiPauseGame() {
		return false;
	}

	/**
	 * Fired when a key is typed (except F11 who toggle full screen). This is
	 * the equivalent of KeyListener.keyTyped(KeyEvent e). Args : character
	 * (character on the key), keyCode (lwjgl Keyboard key code)
	 */
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@SuppressWarnings("unchecked")
	public void initGui() {
		this.viewportTexture = new DynamicTexture(256, 256);
		this.field_110351_G = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
		Calendar var1 = Calendar.getInstance();
		var1.setTime(new Date());

		if (var1.get(2) + 1 == 11 && var1.get(5) == 9) {
			this.splashText = "Happy birthday, ez!";
		} else if (var1.get(2) + 1 == 6 && var1.get(5) == 1) {
			this.splashText = "Happy birthday, Notch!";
		} else if (var1.get(2) + 1 == 12 && var1.get(5) == 24) {
			this.splashText = "Merry X-mas!";
		} else if (var1.get(2) + 1 == 1 && var1.get(5) == 1) {
			this.splashText = "Happy new year!";
		} else if (var1.get(2) + 1 == 10 && var1.get(5) == 31) {
			this.splashText = "OOoooOOOoooo! Spooky!";
		} else {
			this.splashText = "";
		}

		boolean var2 = true;
		int var3 = this.height / 4 + 48;

		this.addSingleplayerMultiplayerButtons();

		this.buttonList.add(new GuiButton(0, this.width - 120, 3, 60, 20, I18n.format("menu.options", new Object[0])));
		this.buttonList.add(new GuiButton(4, this.width - 54, 3, 50, 20, I18n.format("menu.quit", new Object[0])));

		// Remove annoying language button
		// this.buttonList.add(new GuiButtonLanguage(5, this.width / 2 - 124,
		// var3 + 72 + 12));

		Object var4 = this.field_104025_t;

		synchronized (this.field_104025_t) {
			this.field_92023_s = this.fontRendererObj.getStringWidth(this.field_92025_p);
			this.field_92024_r = this.fontRendererObj.getStringWidth(this.field_146972_A);
			int var5 = Math.max(this.field_92023_s, this.field_92024_r);
			this.field_92022_t = (this.width - var5) / 2;
			this.field_92021_u = ((GuiButton) this.buttonList.get(0)).yPosition - 24;
			this.field_92020_v = this.field_92022_t + var5;
			this.field_92019_w = this.field_92021_u + 24;
		}
	}

	/**
	 * Adds Singleplayer and Multiplayer buttons on Main Menu for players who
	 * have bought the game.
	 */
	@SuppressWarnings("unchecked")
	private void addSingleplayerMultiplayerButtons() {
		this.buttonList.add(new GuiButton(1, 4, 3, 60, 20, I18n.format("menu.singleplayer", new Object[0])));
		this.buttonList.add(new GuiButton(2, 70, 3, 50, 20, I18n.format("menu.multiplayer", new Object[0])));
		this.buttonList.add(new GuiButton(14, 126, 3, 60, 20, "§bResilient§r"));

	}

	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == 0) {
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
		}

		if (button.id == 1) {
			this.mc.displayGuiScreen(new GuiSelectWorld(this));
		}

		if (button.id == 2) {
			this.mc.displayGuiScreen(new GuiMultiplayer(this));
		}

		if (button.id == 14) {
			// Launch Resilient Options
			this.mc.displayGuiScreen(new SettingsMenu(this));
		}

		if (button.id == 4) {
			this.mc.shutdown();
		}

	}

	public void confirmClicked(boolean result, int id) {
		if (result && id == 12) {
			ISaveFormat var6 = this.mc.getSaveLoader();
			var6.flushCache();
			this.mc.displayGuiScreen(this);
		} else if (id == 13) {
			if (result) {
				try {
					Class var3 = Class.forName("java.awt.Desktop");
					Object var4 = var3.getMethod("getDesktop", new Class[0]).invoke((Object) null, new Object[0]);
					var3.getMethod("browse", new Class[] { URI.class }).invoke(var4,
							new Object[] { new URI(this.field_104024_v) });
				} catch (Throwable var5) {
					logger.error("Couldn\'t open link", var5);
				}
			}

			this.mc.displayGuiScreen(this);
		}
	}

	/**
	 * Draws the main menu panorama
	 */
	private void drawPanorama(int p_73970_1_, int p_73970_2_, float p_73970_3_) {
		Tessellator var4 = Tessellator.getInstance();
		WorldRenderer var5 = var4.getWorldRenderer();
		GlStateManager.matrixMode(5889);
		GlStateManager.pushMatrix();
		GlStateManager.loadIdentity();
		Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
		GlStateManager.matrixMode(5888);
		GlStateManager.pushMatrix();
		GlStateManager.loadIdentity();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.disableCull();
		GlStateManager.depthMask(false);
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		byte var6 = 8;

		for (int var7 = 0; var7 < var6 * var6; ++var7) {
			GlStateManager.pushMatrix();
			float var8 = ((float) (var7 % var6) / (float) var6 - 0.5F) / 64.0F;
			float var9 = ((float) (var7 / var6) / (float) var6 - 0.5F) / 64.0F;
			float var10 = 0.0F;
			GlStateManager.translate(var8, var9, var10);
			GlStateManager.rotate(MathHelper.sin(((float) this.panoramaTimer + p_73970_3_) / 30.0F) * 25.0F + 20.0F,
					1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(-((float) this.panoramaTimer + p_73970_3_) * -1.3F, 0.0F, 1.0F, 0.0F);

			for (int var11 = 0; var11 < 6; ++var11) {
				GlStateManager.pushMatrix();

				if (var11 == 1) {
					GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
				}

				if (var11 == 2) {
					GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
				}

				if (var11 == 3) {
					GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
				}

				if (var11 == 4) {
					GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
				}

				if (var11 == 5) {
					GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
				}

				this.mc.getTextureManager().bindTexture(titlePanoramaPaths[var11]);
				var5.startDrawingQuads();
				var5.func_178974_a(16777215, 255 / (var7 + 1));
				float var12 = 0.0F;
				var5.addVertexWithUV(-1.0D, -1.0D, 1.0D, (double) (0.0F + var12), (double) (0.0F + var12));
				var5.addVertexWithUV(1.0D, -1.0D, 1.0D, (double) (1.0F - var12), (double) (0.0F + var12));
				var5.addVertexWithUV(1.0D, 1.0D, 1.0D, (double) (1.0F - var12), (double) (1.0F - var12));
				var5.addVertexWithUV(-1.0D, 1.0D, 1.0D, (double) (0.0F + var12), (double) (1.0F - var12));
				var4.draw();
				GlStateManager.popMatrix();
			}

			GlStateManager.popMatrix();
			GlStateManager.colorMask(true, true, true, false);
		}

		var5.setTranslation(0.0D, 0.0D, 0.0D);
		GlStateManager.colorMask(true, true, true, true);
		GlStateManager.matrixMode(5889);
		GlStateManager.popMatrix();
		GlStateManager.matrixMode(5888);
		GlStateManager.popMatrix();
		GlStateManager.depthMask(true);
		GlStateManager.enableCull();
		GlStateManager.enableDepth();
	}

	/**
	 * Rotate the skybox view in the main menu
	 */
	private void rotateSkybox() {
		this.mc.getTextureManager().bindTexture(this.field_110351_G);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.colorMask(true, true, true, false);
		Tessellator var2 = Tessellator.getInstance();
		WorldRenderer var3 = var2.getWorldRenderer();
		var3.startDrawingQuads();
		GlStateManager.disableAlpha();
		byte var4 = 3;

		var2.draw();
		GlStateManager.enableAlpha();
		GlStateManager.colorMask(true, true, true, true);
	}

	/**
	 * Renders the skybox in the main menu
	 */
	private void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_) {
		this.mc.getFramebuffer().unbindFramebuffer();
		GlStateManager.viewport(0, 0, 256, 256);
		this.drawPanorama(p_73971_1_, p_73971_2_, p_73971_3_);
		this.rotateSkybox();
		this.mc.getFramebuffer().bindFramebuffer(true);
		GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
		Tessellator var4 = Tessellator.getInstance();
		WorldRenderer var5 = var4.getWorldRenderer();
		var5.startDrawingQuads();
		float var6 = this.width > this.height ? 120.0F / (float) this.width : 120.0F / (float) this.height;
		float var7 = (float) this.height * var6 / 256.0F;
		float var8 = (float) this.width * var6 / 256.0F;
		var5.func_178960_a(1.0F, 1.0F, 1.0F, 1.0F);
		int var9 = this.width;
		int var10 = this.height;
		var5.addVertexWithUV(0.0D, (double) var10, (double) this.zLevel, (double) (0.5F - var7),
				(double) (0.5F + var8));
		var5.addVertexWithUV((double) var9, (double) var10, (double) this.zLevel, (double) (0.5F - var7),
				(double) (0.5F - var8));
		var5.addVertexWithUV((double) var9, 0.0D, (double) this.zLevel, (double) (0.5F + var7), (double) (0.5F - var8));
		var5.addVertexWithUV(0.0D, 0.0D, (double) this.zLevel, (double) (0.5F + var7), (double) (0.5F + var8));
		var4.draw();
	}

	/**
	 * Draws the screen and all the components in it. Args : mouseX, mouseY,
	 * renderPartialTicks
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.disableAlpha();
		this.renderSkybox(mouseX, mouseY, partialTicks);
		GlStateManager.enableAlpha();
		Tessellator var4 = Tessellator.getInstance();
		WorldRenderer var5 = var4.getWorldRenderer();
		short var6 = 274;

		// Logo Width
		int var7 = this.width / 2 - var6 / 2;

		// Logo Height
		int var8 = (this.height / 2) - 30;

		// Color speed
		float colorspeed = 0.2f;
		// this.drawGradientRect(0, 0, this.width, this.height, -2130706433,
		// 16777215);
		// this.drawGradientRect(0, 0, this.width, this.height, 0,
		// Integer.MIN_VALUE);
		this.mc.getTextureManager().bindTexture(minecraftTitleTextures);

		GlStateManager.color(logo_red, logo_green, logo_blue, 1.0F);

		if (logo_red > 1.0f) {
			logo_ring = false;
		}
		if (logo_green > 1.0f) {
			logo_ging = false;
		}
		if (logo_blue > 1.0f) {
			logo_bing = false;
		}
		if (logo_red < 0.0f) {
			logo_ring = true;
		}
		if (logo_green < 0.0f) {
			logo_ging = true;
		}
		if (logo_blue < 0.0f) {
			logo_bing = true;
		}

		if (logo_ring) {
			logo_red += (colorspeed * 0.02f);
		} else {
			logo_red -= (colorspeed * 0.1f);
		}

		if (logo_ging) {
			logo_green += (colorspeed * 0.1f);
		} else {
			logo_green -= (colorspeed * 0.06f);
		}

		if (logo_bing) {
			logo_blue += (colorspeed * 0.3f);
		} else {
			logo_blue -= (colorspeed * 0.02f);
		}

		if ((double) this.updateCounter < 1.0E-4D) {
			this.drawTexturedModalRect(var7 + 0, var8 + 0, 0, 0, 99, 44);
			this.drawTexturedModalRect(var7 + 99, var8 + 0, 129, 0, 27, 44);
			this.drawTexturedModalRect(var7 + 99 + 26, var8 + 0, 126, 0, 3, 44);
			this.drawTexturedModalRect(var7 + 99 + 26 + 3, var8 + 0, 99, 0, 26, 44);
			this.drawTexturedModalRect(var7 + 155, var8 + 0, 0, 45, 155, 44);
		} else {
			this.drawTexturedModalRect(var7 + 0, var8 + 0, 0, 0, 155, 44);
			this.drawTexturedModalRect(var7 + 155, var8 + 0, 0, 45, 155, 44);
		}

		var5.func_178991_c(-1);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) (this.width / 2 + 90), 70.0F, 0.0F);

		float var9 = 1.8F - MathHelper.abs(
				MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
		var9 = var9 * 100.0F / (float) (this.fontRendererObj.getStringWidth(this.splashText) + 10);
		GlStateManager.scale(var9 / 2, var9 / 2, var9 / 2);

		this.drawCenteredString(this.fontRendererObj, this.splashText, 0, -8, 252);
		GlStateManager.popMatrix();

		String var10 = "§7Minecraft 1.8 (Cheats: " + this.mc.resilient.getColoredStatus().toUpperCase() + "§7)§r";
		this.drawString(this.fontRendererObj, var10, 2, this.height - 10, -1);

		String var11 = "§b§nhttps://cloudchan.org/§r";
		this.drawString(this.fontRendererObj, var11, this.width - this.fontRendererObj.getStringWidth(var11) - 2,
				this.height - 10, -1);

		String var12 = "§5Founded by Notch§r";
		this.drawString(this.fontRendererObj, var12, 2, this.height - 20, -1);

		String var13 = "§c§mMicrosoft(c)§r";
		this.drawString(this.fontRendererObj, var13, 2, this.height - 30, -1);

		GlStateManager.pushMatrix();
		GlStateManager.scale(1.8f, 2.4f, 1.0f);
		GlStateManager.translate(16.5f, 0.0f, 0.0f);

		int x = (this.width / 4);
		int y = (this.height / 4);
		this.drawCenteredString(this.fontRendererObj,
				"§2§l" + this.mc.resilient.NAME + " " + this.mc.resilient.VERSION + "§r", x, y, -1);

		GlStateManager.popMatrix();

		// Draw top navbar
		drawRect(0, 0, this.width, 25, 1428160502);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	/**
	 * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
	 */
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		Object var4 = this.field_104025_t;

		synchronized (this.field_104025_t) {
			if (this.field_92025_p.length() > 0 && mouseX >= this.field_92022_t && mouseX <= this.field_92020_v
					&& mouseY >= this.field_92021_u && mouseY <= this.field_92019_w) {
				GuiConfirmOpenLink var5 = new GuiConfirmOpenLink(this, this.field_104024_v, 13, true);
				var5.disableSecurityWarning();
				this.mc.displayGuiScreen(var5);
			}
		}
	}
}
