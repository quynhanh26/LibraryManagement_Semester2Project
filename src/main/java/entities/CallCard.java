package entities;

import java.time.LocalDate;

public class CallCard {

	private int callCardId;
	private String peopleId;
	private LocalDate dateBorrowed;
	private LocalDate dateDue;
	private int quantityBrrowed;
	private int quantityDue;
	private int overdueFines;
	private boolean status;

	public int getCallCardId() {
		return callCardId;
	}

	public void setCallCardId(int callCardId) {
		this.callCardId = callCardId;
	}

	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public LocalDate getDateBorrowed() {
		return dateBorrowed;
	}

	public void setDateBorrowed(LocalDate dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	public LocalDate getDateDue() {
		return dateDue;
	}

	public void setDateDue(LocalDate dateDue) {
		this.dateDue = dateDue;
	}


	public int getQuantityBrrowed() {
		return quantityBrrowed;
	}

	public void setQuantityBrrowed(int quantityBrrowed) {
		this.quantityBrrowed = quantityBrrowed;
	}

	public int getQuantityDue() {
		return quantityDue;
	}

	public void setQuantityDue(int quantityDue) {
		this.quantityDue = quantityDue;
	}

	public int getOverdueFines() {
		return overdueFines;
	}

	public void setOverdueFines(int overdueFines) {
		this.overdueFines = overdueFines;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public CallCard() {
	}

	public CallCard(int callCardId, String peopleId, LocalDate dateBorrowed, LocalDate dateDue,
			int quantityBrrowed, int quantityDue, int overdueFines, boolean status) {
		this.callCardId = callCardId;
		this.peopleId = peopleId;
		this.dateBorrowed = dateBorrowed;
		this.dateDue = dateDue;
		this.quantityBrrowed = quantityBrrowed;
		this.quantityDue = quantityDue;
		this.overdueFines = overdueFines;
		this.status = status;
	}

}
