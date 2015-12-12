package com.Resilient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

import com.Resilient.settings.Settings;

public class Resilient {
    /** Basic Client Info */
    public static final String CLIENT_TITLE = "Resilient";
    public static final String STAGE        = "ALPHA";
    public static final String PREFIX       = "[" + CLIENT_TITLE + "]";

    /** Objects */
    public static Resilient    core;
    private Minecraft          mc;
    private GuiIngame          gui;
    private Settings           settings;
    

    public Resilient(Minecraft mc, GuiIngame gui) {
        System.out.println("Resilient Client Loading..."); // Change the console print to whatever you want
        core = this;
        settings = new Settings();
        this.mc = mc;
        this.gui = gui;
    }

    public void update() {
        settings.update();
    }
    
    public Minecraft getMc(){
        return mc;
    }
    
    public static Resilient getInstance(){
        return core;
    }
}
