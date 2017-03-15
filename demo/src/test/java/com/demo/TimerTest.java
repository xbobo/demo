package com.demo;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	public static void main(String[] args) {
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				timer.cancel();
			}
		}, 5 * 60 * 1000);
	}
}
