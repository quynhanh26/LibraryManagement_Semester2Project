package model_view;

import java.time.LocalDate;

public class CallCard_People {

	private int no;
	private int callCardId;
	private String peopleId;
	private LocalDate dateBorrowed;
	private LocalDate dateDue;
	private int quantityBorrowed;
	private int quantityDue;
	private int overdueFines;
	private boolean status;
	private String peopleName;
	private String phone;
	private String email;
	private String className;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

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

	public int getQuantityBorrowed() {
		return quantityBorrowed;
	}

	public void setQuantityBorrowed(int quantityBorrowed) {
		this.quantityBorrowed = quantityBorrowed;
	}

	public int getQuantityPaid() {
		return quantityDue;
	}

	public void setQuantityPaid(int quantityPaid) {
		this.quantityDue = quantityPaid;
	}

	public int getOverdueFines() {
		return overdueFines;
	}

	public void setOverdueFines(int overdueFines) {
		this.overdueFines = overdueFines;
	}

	public int getQuantityBrrowed() {
		return quantityBorrowed;
	}

	public void setQuantityBrrowed(int quantityBrrowed) {
		this.quantityBorrowed = quantityBrrowed;
	}

	public int getQuantityDue() {
		return quantityDue;
	}

	public void setQuantityDue(int quantityDue) {
		this.quantityDue = quantityDue;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public CallCard_People() {

	}

	public CallCard_People(int no, int callCardId, String peopleId, LocalDate dateBorrowed, LocalDate dateDue,
			int quantityBorrowed, int quantityDue, int overdueFines, boolean status, String peopleName, String phone,
			String email, String className) {
		this.no = no;
		this.callCardId = callCardId;
		this.peopleId = peopleId;
		this.dateBorrowed = dateBorrowed;
		this.dateDue = dateDue;
		this.quantityBorrowed = quantityBorrowed;
		this.quantityDue = quantityDue;
		this.overdueFines = overdueFines;
		this.status = status;
		this.peopleName = peopleName;
		this.phone = phone;
		this.email = email;
		this.className = className;
	}


}
