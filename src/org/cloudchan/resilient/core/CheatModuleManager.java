package org.cloudchan.resilient.core;

import java.util.ArrayList;
import org.cloudchan.resilient.cheats.*;
import org.cloudchan.resilient.utils.CheatModule;

public class CheatModuleManager {
	
	private static ArrayList<CheatModule> cheats = new ArrayList<CheatModule>();
	
	public CheatModuleManager(){
		cheats.add(new Sprint());
		cheats.add(new FullBright());
		cheats.add(new Rage());
		cheats.add(new PlayerAura());
		cheats.add(new MobAura());
		cheats.add(new PlayerAimBot());
		cheats.add(new MobAimBot());
		cheats.add(new Fly());
		cheats.add(new FastPlace());
		cheats.add(new FastBreak());
		cheats.add(new PlayerTracer());
		cheats.add(new MobTracer());
		cheats.add(new PlayerESP());
		cheats.add(new MobESP());
		cheats.add(new BowBot());
	}
	
	public static ArrayList<CheatModule> getModules(){
		return cheats;
	}
	
	public static CheatModule getModule(Class <?extends CheatModule> clazz){
		for(CheatModule mod : getModules()){
			if(mod.getClass() == clazz){
				return mod;
			}
		}
		return null;
	}
	
	
	
	
}
