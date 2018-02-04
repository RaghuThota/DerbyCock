package com.bean;

import java.util.List;

public class Players {
	private int id;
	private String playerId;
	private String name;
	private String email;
	private long phone;
	private short activeInd;
	private List<Entries> entriesList;

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

}
