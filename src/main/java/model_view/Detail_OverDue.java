package model_view;

import java.time.LocalDate;

public class Detail_OverDue {

	private int no;
	private int idCallCard;
	private String bookId;
	private int quantity;
	private int quantityDue;
	private String title;
	private String author;

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

	public int getIdCallCard() {
		return idCallCard;
	}

	public void setIdCallCard(int idCallCard) {
		this.idCallCard = idCallCard;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantityDue() {
		return quantityDue;
	}

	public void setQuantityDue(int quantityDue) {
		this.quantityDue = quantityDue;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getIdPeople() {
		return idPeople;
	}

	public void setIdPeople(String idPeople) {
		this.idPeople = idPeople;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Detail_OverDue() {

	}

	public Detail_OverDue(int no, int idCallCard, String bookId, int quantity, int quantityDue, String title,
			String author, String categoryId, String img, String idPeople, LocalDate dateBorrowed, LocalDate dateDue,
			int total) {
		super();
		this.no = no;
		this.idCallCard = idCallCard;
		this.bookId = bookId;
		this.quantity = quantity;
		this.quantityDue = quantityDue;
		this.title = title;
		this.author = author;
		this.categoryId = categoryId;
		this.img = img;
		this.idPeople = idPeople;
		this.dateBorrowed = dateBorrowed;
		this.dateDue = dateDue;
		this.total = total;
	}

}
