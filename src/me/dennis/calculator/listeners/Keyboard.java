package me.dennis.calculator.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Keyboard implements KeyListener {

	public static ArrayList<Boolean> keys = new ArrayList<Boolean>();
	public static ArrayList<Boolean> pkeys = new ArrayList<Boolean>();
	public static ArrayList<Boolean> rkeys = new ArrayList<Boolean>();
	
	public void setup() {
		for (int i = 0; i < KeyEvent.KEY_LAST; i++) {
			keys.add(false);
			pkeys.add(false);
			rkeys.add(false);
		}
	}
	
	public void reset() {
		for (int i = 0; i < keys.size(); i++) {
			pkeys.set(i, false);
			rkeys.set(i, false);
		}
	}
	
	public static Boolean isDirect(Integer key) {
		return keys.get(key);
	}
	
	public static Boolean isPressed(Integer key) {
		return pkeys.get(key);
	}
	
	public static Boolean isReleased(Integer key) {
		return rkeys.get(key);
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		keys.set(event.getKeyCode(), true);
		pkeys.set(event.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		keys.set(event.getKeyCode(), false);
		rkeys.set(event.getKeyCode(), true);
	}

	@Override
	public void keyTyped(KeyEvent event) {}

}
