package com.project.derby.action;

public class OpponentVO {

	private String fightID, opponent1, opponent2, fightStatus, wonBy, uniqueFightID;

	public OpponentVO(String fightID, String opponent1, String opponent2,
			String fightStatus, String wonBy) {
		this.fightID = fightID;
		this.fightID = fightID;
		this.opponent1 = opponent1;
		this.opponent2 = opponent2;
		this.fightStatus = fightStatus;
		this.wonBy = wonBy;
	}

	public OpponentVO(String fightID, String opponent1, String opponent2,
			String fightStatus) {
		this.fightID = fightID;
		this.fightID = fightID;
		this.opponent1 = opponent1;
		this.opponent2 = opponent2;
		this.fightStatus = fightStatus;
		;
	}

	public OpponentVO() {
	}

	public String getUniqueFightID() {
		return uniqueFightID;
	}

	public void setUniqueFightID(String uniqueFightID) {
		this.uniqueFightID = uniqueFightID;
	}

	public String getWonBy() {
		if (wonBy == null || wonBy.length() == 0) {
			return "NA";
		}
		return wonBy;
	}

	public void setWonBy(String wonBy) {
		this.wonBy = wonBy;
	}

	public String getFightID() {
		return fightID;
	}

	public void setFightID(String fightID) {
		this.fightID = fightID;
	}

	public String getOpponent1() {
		return opponent1;
	}

	public void setOpponent1(String opponent1) {
		this.opponent1 = opponent1;
	}

	public String getOpponent2() {
		return opponent2;
	}

	public void setOpponent2(String opponent2) {
		this.opponent2 = opponent2;
	}

	public String getFightStatus() {
		return fightStatus;
	}

	public void setFightStatus(String fightStatus) {
		this.fightStatus = fightStatus;
	}

	@Override
	public String toString() {
		return "OpponentVO [fightID=" + fightID + ", opponent1=" + opponent1
				+ ", opponent2=" + opponent2 + ", fightStatus=" + fightStatus + ", wonBy="
				+ wonBy + "]";
	}

}
