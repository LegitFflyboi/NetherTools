package org.cloudchan.resilient.utils;

public class Wait {

	public Wait() {}
	
	public void begin(final int millis, final DelayedExecute de){
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(millis);
					de.execute();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
