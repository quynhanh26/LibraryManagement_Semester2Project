package entities;

public class Books_Detail {

	private int id;
	private String id_book;
	private int quantity;
	private int quantity_paid;

	public int getQuantity_paid() {
		return quantity_paid;
	}

	public void setQuantity_paid(int quantity_paid) {
		this.quantity_paid = quantity_paid;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Books_Detail(int id, String id_book, int quantity, int quantity_paid) {
		super();
		this.id = id;
		this.id_book = id_book;
		this.quantity = quantity;
		this.quantity_paid = quantity_paid;
	}

	public Books_Detail() {
		super();
	}



}
