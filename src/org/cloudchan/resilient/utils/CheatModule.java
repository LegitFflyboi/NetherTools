package org.cloudchan.resilient.utils;

public class CheatModule {
	
	// Default values
	private String cheatname;
	private int keybind;
	private Category category;
	private boolean enabled;
	
	public CheatModule(String cheatname, int keybind, Category category){
		this.cheatname = cheatname;
		this.keybind = keybind;
		this.category = category;
		this.enabled = false;
	}
	
	public CheatModule(String cheatname, Category category){
		this.cheatname = cheatname;
		this.keybind = -1;
		this.category = category;
		this.enabled = false;
	}
	
	public String getCheatName(){
		return cheatname;
	}
	
	public int getKeyBind(){
		return keybind;
	}
	
	public Category getCategory(){
		return category;
	}
	
	public boolean isEnabled(){
		return enabled;
	}
	
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
		if (Wrapper.mc.resilient.isEnabled()) {
			this.onToggle();
			if (this.enabled) {
				this.onEnable();
			} else {
				this.onDisable();
			}
		}
	}
	
	public void setKeyBind(int keybind){
		this.keybind = keybind;
	}

	public void toggle() {
		if (Wrapper.mc.resilient.isEnabled()) {
			this.enabled = !this.enabled;
			this.onToggle();
		}
	}

	public void onToggle() {}

	public void onEnable() {}

	public void onDisable() {}

	public void onUpdate() {}

	public void onRender() {}
	
}
