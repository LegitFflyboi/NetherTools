package com.Resilient.settings;

import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.input.Keyboard;

import com.Resilient.Resilient;
import com.Resilient.gui.ResiChat;
import com.Resilient.input.Keybind;

public class Settings {
    /** Custom Keys */
    private Keybind openResiChat = new Keybind("Opens the Resilient Console", Keyboard.KEY_GRAVE);
    
    /** Toggles */
    private boolean resiConsole = false;
    private boolean lastBoolean = false;

    public void update() {
        
        // Still a wip...
        if (Keyboard.isKeyDown(openResiChat.getKeyCode())) {
            openResiChat.onTick(openResiChat.getKeyCode());
            resiConsole = !resiConsole;
            openResiChat.unPressAllKeys();
            Resilient.getInstance().getMc().displayGuiScreen(resiConsole ? new ResiChat(Resilient.PREFIX) : (GuiScreen)null);
        }
    }
}
