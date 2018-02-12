package com.bean;

import java.util.List;

public class Entries {

	private int id;
	private String palyerId;
	private String entryId;
	private double derbyWeight;
	private List<Double> derbyWeightList;
	private short activeInd;

	@Override
	public String toString() {
		return "Entries [ palyerId=" + palyerId + ", entryId=" + entryId
				+ ", derbyWeight=" + derbyWeight + "]";
	}

	public int getId() {
		return id;
	}

	public List<Double> getDerbyWeightList() {
		return derbyWeightList;
	}

	public void setDerbyWeightList(List<Double> derbyWeightList) {
		this.derbyWeightList = derbyWeightList;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPalyerId() {
		return palyerId;
	}

	public void setPalyerId(String palyerId) {
		this.palyerId = palyerId;
	}

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public double getDerbyWeight() {
		return derbyWeight;
	}

	public void setDerbyWeight(double derbyWeight) {
		this.derbyWeight = derbyWeight;
	}

	public short getActiveInd() {
		return activeInd;
	}

	public void setActiveInd(short activeInd) {
		this.activeInd = activeInd;
	}

}
