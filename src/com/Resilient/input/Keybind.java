package com.Resilient.input;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IntHashMap;

public class Keybind implements Comparable
{
    private static final List keybindArray = Lists.newArrayList();
    private static final IntHashMap hash = new IntHashMap();
    private static final Set keybindSet = Sets.newHashSet();
    private final String keyDescription;
    private final int keyCodeDefault;
    private int keyCode;

    /** because _303 wanted me to call it that(Caironater) */
    private boolean pressed;
    private int pressTime;

    public static void onTick(int keyCode)
    {
        if (keyCode != 0)
        {
            Keybind var1 = (Keybind)hash.lookup(keyCode);

            if (var1 != null)
            {
                ++var1.pressTime;
            }
        }
    }

    public static void setKeyBindState(int keyCode, boolean pressed)
    {
        if (keyCode != 0)
        {
            Keybind var2 = (Keybind)hash.lookup(keyCode);

            if (var2 != null)
            {
                var2.pressed = pressed;
            }
        }
    }

    public static void unPressAllKeys()
    {
        Iterator var0 = keybindArray.iterator();

        while (var0.hasNext())
        {
            Keybind var1 = (Keybind)var0.next();
            var1.unpressKey();
        }
    }

    public static void resetKeyBindingArrayAndHash()
    {
        hash.clearMap();
        Iterator var0 = keybindArray.iterator();

        while (var0.hasNext())
        {
            Keybind var1 = (Keybind)var0.next();
            hash.addKey(var1.keyCode, var1);
        }
    }

    public static Set getKeybinds()
    {
        return keybindSet;
    }

    public Keybind(String description, int keyCode)
    {
        this.keyDescription = description;
        this.keyCode = keyCode;
        this.keyCodeDefault = keyCode;
        keybindArray.add(this);
        hash.addKey(keyCode, this);
    }

    public boolean getIsKeyPressed()
    {
        return this.pressed;
    }

    public boolean isPressed()
    {
        if (this.pressTime == 0)
        {
            return false;
        }
        else
        {
            --this.pressTime;
            return true;
        }
    }

    private void unpressKey()
    {
        this.pressTime = 0;
        this.pressed = false;
    }

    public String getKeyDescription()
    {
        return this.keyDescription;
    }

    public int getKeyCodeDefault()
    {
        return this.keyCodeDefault;
    }

    public int getKeyCode()
    {
        return this.keyCode;
    }

    public void setKeyCode(int keyCode)
    {
        this.keyCode = keyCode;
    }

    public int compareTo(Object p_compareTo_1_)
    {
        return this.compareTo((Keybind)p_compareTo_1_);
    }
}
