package model_view;

import java.time.LocalDate;

public class Book_BookDetail_CallCard {
	private int no;
	private int id;
	private String id_book;
	private String title;
	private String author;
	private String id_category;
	private int quantity;
	private int quantity_due;
	private int num;
	private int idCallCard;
	private String bookId;
	private int quantityDue;
	private String categoryId;
	private String img;
	private String idPeople;
	private LocalDate dateBorrowed;
	private LocalDate dateDue;
	private int total;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getId() {
		return id;
	}

	public String getId_book() {
		return id_book;
	}

	public void setId_book(String id_book) {
		this.id_book = id_book;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getId_category() {
		return id_category;
	}

	public void setId_category(String id_category) {
		this.id_category = id_category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity_due() {
		return quantity_due;
	}

	public void setQuantity_due(int quantity_due) {
		this.quantity_due = quantity_due;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return the idCallCard
	 */
	public int getIdCallCard() {
		return idCallCard;
	}

	/**
	 * @param idCallCard the idCallCard to set
	 */
	public void setIdCallCard(int idCallCard) {
		this.idCallCard = idCallCard;
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
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return the idPeople
	 */
	public String getIdPeople() {
		return idPeople;
	}

	/**
	 * @param idPeople the idPeople to set
	 */
	public void setIdPeople(String idPeople) {
		this.idPeople = idPeople;
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
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}


	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	public Book_BookDetail_CallCard() {
	}

	public Book_BookDetail_CallCard(int no, int id, String id_book, String title, String author, String id_category,
			int quantity, int quantity_due, int num, int idCallCard, String bookId, int quantityDue, String categoryId,
			String img, String idPeople, LocalDate dateBorrowed, LocalDate dateDue, int total) {
		this.no = no;
		this.id = id;
		this.id_book = id_book;
		this.title = title;
		this.author = author;
		this.id_category = id_category;
		this.quantity = quantity;
		this.quantity_due = quantity_due;
		this.num = num;
		this.idCallCard = idCallCard;
		this.bookId = bookId;
		this.quantityDue = quantityDue;
		this.categoryId = categoryId;
		this.img = img;
		this.idPeople = idPeople;
		this.dateBorrowed = dateBorrowed;
		this.dateDue = dateDue;
		this.total = total;
	}



}
