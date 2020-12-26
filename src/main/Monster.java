package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Hashtable;
import java.util.jar.Attributes.Name;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Monster extends Property {
	public JLabel monster_label;
	public JProgressBar powerBar;
	private JPanel battleField;
	private String name;
	private int blood;
	private int attack;
	private int money;
	private int power;
	private Hashtable<String, Integer> animate_status = new Hashtable<String, Integer>();

	public Monster() {

	}

	public Monster(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.monster_label = new JLabel();
		this.battleField.add(monster_label);
		this.animate_status.put("width", 0);
		this.animate_status.put("height", 0);
		this.animate_status.put("loc_x", 0);
		this.animate_status.put("loc_y", 0);
		this.animate_status.put("count", 0);
	}

	public void setStatus(int a, int b, int m, int p) {
		this.attack = a;
		this.blood = b;
		this.money = m;
		this.power = p;

	}

	public void setSize(int w, int h) {
		this.animate_status.replace("width", w);
		this.animate_status.replace("height", h);
	}

	public void setLoc(int x, int y, int c) {
		this.animate_status.replace("loc_x", x);
		this.animate_status.replace("loc_y", y);
		this.animate_status.replace("count", c);
	}

	public void setBattleField(JPanel field) {
		this.battleField = field;
		setMonsterProperty();
		setMonsterImage(false);
	}

	public void setMonsterImage(boolean attackFlag) {
		int width = this.animate_status.get("width");
		int height = this.animate_status.get("height");
		int count = this.animate_status.get("count");
		int locX = this.animate_status.get("loc_x");
		int locY = this.animate_status.get("loc_y");
		ImageIcon image2;
		if (attackFlag)
			image2 = new ImageIcon("./image/monster/" + count + "fire.gif");
		else
			image2 = new ImageIcon("./image/monster/" + count + ".gif");
		image2.setImage(image2.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		// m.setOpaque(true);
		// m.setBackground(Color.red);
		monster_label.setIcon(image2);
		monster_label.setSize(width, height);
		monster_label.setLocation(locX, locY);
	}

	public void setMonsterProperty() {
		bloodText = new JLabel("blood");
		bloodText.setSize(100, 50);
		bloodText.setLocation(1100, 0);
		bloodText.setForeground(Color.white);
		bloodText.setFont(new Font("dialog", 1, 20));
		blooBar = new JProgressBar();
		blooBar.setMaximum(this.blood);
		blooBar.setMinimum(0);
		blooBar.setValue(this.blood);
		blooBar.setForeground(Color.red);
		blooBar.setSize(200, 30);
		blooBar.setLocation(1100, 50);
		attackText = new JLabel("attack:" + this.attack);
		attackText.setSize(100, 50);
		attackText.setLocation(1100, 100);
		attackText.setForeground(Color.white);
		attackText.setFont(new Font("dialog", 1, 20));
		powerBar = new JProgressBar();
		powerBar.setMaximum(5);
		powerBar.setMinimum(0);
		powerBar.setValue(this.power);
		powerBar.setForeground(Color.yellow);
		powerBar.setLocation(1100, 150);
		powerBar.setSize(200, 20);
		battleField.add(blooBar);
		battleField.add(bloodText);
		battleField.add(attackText);
		battleField.add(powerBar);
	}

	public String getName() {
		return this.name;
	}

	public int getAttack() {
		return this.attack;
	}

	public int getMoney() {
		return this.money;
	}

	public int getBlood() {
		return this.blood;
	}

	public int getPower() {
		return this.power;
	}

	public void setAttack(int a) {
		this.attack = a;
	}

	public void updateMoney(int m) {
		this.money = m;
	}

	public void setBlood(int b) {
		this.blood = b;
	}

	public void setPower(int p) {
		this.power = p;
	}
}
