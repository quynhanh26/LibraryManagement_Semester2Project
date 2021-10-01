package model_view;

import java.time.LocalDate;

public class Book_CallCard_People {
	private int no;
	private int callCardId;
	private String peopleId;
	private String bookId;
	private LocalDate dateBorrowed;
	private LocalDate dateDue;
	private int expDate;
	private int quantityBorrowed;
	private int quantityDue;
	private int overdueFines;
	private boolean status;
	private String peopleName;
	private String phone;
	private String email;
	private String className;
	private String title;
	private String author;
	private String categoryid;

	/**
	 * @return the no
	 */
	public int getNo() {
		return no;
	}

	/**
	 * @param no the no to set
	 */
	public void setNo(int no) {
		this.no = no;
	}

	/**
	 * @return the callCardId
	 */
	public int getCallCardId() {
		return callCardId;
	}

	/**
	 * @param callCardId the callCardId to set
	 */
	public void setCallCardId(int callCardId) {
		this.callCardId = callCardId;
	}

	/**
	 * @return the peopleId
	 */
	public String getPeopleId() {
		return peopleId;
	}

	/**
	 * @param peopleId the peopleId to set
	 */
	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	/**
	 * @return the bookId
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the dateBorrowed
	 */
	public LocalDate getDateBorrowed() {
		return dateBorrowed;
	}

	/**
	 * @param dateBorrowed the dateBorrowed to set
	 */
	public void setDateBorrowed(LocalDate dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	/**
	 * @return the dateDue
	 */
	public LocalDate getDateDue() {
		return dateDue;
	}

	/**
	 * @param dateDue the dateDue to set
	 */
	public void setDateDue(LocalDate dateDue) {
		this.dateDue = dateDue;
	}

	/**
	 * @return the expDate
	 */
	public int getExpDate() {
		return expDate;
	}

	/**
	 * @param expDate the expDate to set
	 */
	public void setExpDate(int expDate) {
		this.expDate = expDate;
	}

	/**
	 * @return the quantityBorrowed
	 */
	public int getQuantityBorrowed() {
		return quantityBorrowed;
	}

	/**
	 * @param quantityBorrowed the quantityBorrowed to set
	 */
	public void setQuantityBorrowed(int quantityBorrowed) {
		this.quantityBorrowed = quantityBorrowed;
	}

	/**
	 * @return the quantityDue
	 */
	public int getQuantityDue() {
		return quantityDue;
	}

	/**
	 * @param quantityDue the quantityDue to set
	 */
	public void setQuantityDue(int quantityDue) {
		this.quantityDue = quantityDue;
	}

	/**
	 * @return the overdueFines
	 */
	public int getOverdueFines() {
		return overdueFines;
	}

	/**
	 * @param overdueFines the overdueFines to set
	 */
	public void setOverdueFines(int overdueFines) {
		this.overdueFines = overdueFines;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the peopleName
	 */
	public String getPeopleName() {
		return peopleName;
	}

	/**
	 * @param peopleName the peopleName to set
	 */
	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the categoryid
	 */
	public String getCategoryid() {
		return categoryid;
	}

	/**
	 * @param categoryid the categoryid to set
	 */
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public Book_CallCard_People() {
	}

	public Book_CallCard_People(int no, int callCardId, String peopleId, String bookId, LocalDate dateBorrowed,
			LocalDate dateDue, int expDate, int quantityBorrowed, int quantityDue, int overdueFines, boolean status,
			String peopleName, String phone, String email, String className, String title, String author,
			String categoryid) {
		super();
		this.no = no;
		this.callCardId = callCardId;
		this.peopleId = peopleId;
		this.bookId = bookId;
		this.dateBorrowed = dateBorrowed;
		this.dateDue = dateDue;
		this.expDate = expDate;
		this.quantityBorrowed = quantityBorrowed;
		this.quantityDue = quantityDue;
		this.overdueFines = overdueFines;
		this.status = status;
		this.peopleName = peopleName;
		this.phone = phone;
		this.email = email;
		this.className = className;
		this.title = title;
		this.author = author;
		this.categoryid = categoryid;
	}

}
