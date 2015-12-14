package org.cloudchan.resilient.core;

import java.util.HashMap;

import org.cloudchan.resilient.cheats.*;
import org.cloudchan.resilient.utils.CheatModule;

public class CheatModuleManager {
	
	public static HashMap<String,CheatModule> cheats = new HashMap<String,CheatModule>();
	
	public CheatModuleManager(){
		cheats.put("sprint", new Sprint());
		cheats.put("fullbright", new FullBright());
		cheats.put("rage", new Rage());
		cheats.put("playeraura", new PlayerAura());
		cheats.put("mobaura", new MobAura());
		cheats.put("playeraimbot", new PlayerAimBot());
		cheats.put("mobaimbot", new MobAimBot());
		cheats.put("fly", new Fly());
		cheats.put("fastplace", new FastPlace());
		
		
		
	}
	
	public static CheatModule getModule(String name){
		if(cheats.containsKey(name)){
			return cheats.get(name);
		}
		return null;
	}
	
	
	
	
}
