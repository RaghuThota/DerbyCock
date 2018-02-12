package com.bean;

public class Fights {
	private int fightId;
	private String player1IdName;
	private String player2IdName;
	private String Entry1Id;
	private String Entry2Id;
	private String wonBy;
	
	public int getFightId() {
		return fightId;
	}
	public void setFightId(int fightId) {
		this.fightId = fightId;
	}
	public String getPlayer1IdName() {
		return player1IdName;
	}
	public void setPlayer1IdName(String player1IdName) {
		this.player1IdName = player1IdName;
	}
	public String getPlayer2IdName() {
		return player2IdName;
	}
	public void setPlayer2IdName(String player2IdName) {
		this.player2IdName = player2IdName;
	}
	public String getEntry1Id() {
		return Entry1Id;
	}
	public void setEntry1Id(String entry1Id) {
		Entry1Id = entry1Id;
	}
	public String getEntry2Id() {
		return Entry2Id;
	}
	public void setEntry2Id(String entry2Id) {
		Entry2Id = entry2Id;
	}
	public String getWonBy() {
		return wonBy;
	}
	@Override
	public String toString() {
		return "Fights [fightId=" + fightId + ", player1IdName=" + player1IdName + ", player2IdName=" + player2IdName
				+ ", Entry1Id=" + Entry1Id + ", Entry2Id=" + Entry2Id + ", wonBy=" + wonBy + "]";
	}
	public void setWonBy(String wonBy) {
		this.wonBy = wonBy;
	}
}
