package com.bean;

import java.util.List;

public class Players implements Comparable<Players>{
	@Override
	public String toString() {
		return "Players [ playerId=" + playerId + ", name=" + name + ", entry=" + entry + "]";
	}

	private int id;
	private String playerId;
	private String name;
	private String email;
	private long phone;
	private short activeInd;
	private List<Entries> entriesList;
	private Entries entry;
	private int points;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Entries getEntry() {
		return entry;
	}

	public void setEntry(Entries entry) {
		this.entry = entry;
	}

	public List<Entries> getEntriesList() {
		return entriesList;
	}

	public void setEntriesList(List<Entries> entriesList) {
		this.entriesList = entriesList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public short getActiveInd() {
		return activeInd;
	}

	public void setActiveInd(short activeInd) {
		this.activeInd = activeInd;
	}

	@Override
	public int compareTo(Players player) {
		// TODO Auto-generated method stub
		double weight  = player.getEntry().getDerbyWeight();
		return new Double(this.entry.getDerbyWeight()).compareTo( weight);
	}

}
