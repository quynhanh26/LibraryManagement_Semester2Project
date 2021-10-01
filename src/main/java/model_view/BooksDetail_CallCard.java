package model_view;

public class BooksDetail_CallCard {

	private int no;
	private int id;
	private String id_book;
	private String title;
	private String author;
	private String id_category;
	private String idPeople;
	private int quantity;
	private int quantity_due;
	private int num;

	public String getIdPeople() {
		return idPeople;
	}

	public void setIdPeople(String idPeople) {
		this.idPeople = idPeople;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public BooksDetail_CallCard(int no, int id, String id_book, String title, String author, String id_category,
			String idPeople, int quantity, int quantity_due, int num) {
		super();
		this.no = no;
		this.id = id;
		this.id_book = id_book;
		this.title = title;
		this.author = author;
		this.id_category = id_category;
		this.idPeople = idPeople;
		this.quantity = quantity;
		this.quantity_due = quantity_due;
		this.num = num;
	}

	public BooksDetail_CallCard() {

	}

	public BooksDetail_CallCard(int no, int id, String id_book, String title, String author, String id_category,
			int quantity, int quantity_due, int num) {
		this.no = no;
		this.id = id;
		this.id_book = id_book;
		this.title = title;
		this.author = author;
		this.id_category = id_category;
		this.quantity = quantity;
		this.quantity_due = quantity_due;
		this.num = num;
	}

}
