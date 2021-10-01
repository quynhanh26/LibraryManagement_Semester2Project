package entities;

public class Books {

	private String bookId;
	private String title;
	private String author;
	private String categoryId;
	private int quantity;
	private String img;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public Books() {
	}

	public Books(String bookId, String title, String author, String categoryId, int quantity, String img) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.categoryId = categoryId;
		this.quantity = quantity;
		this.img = img;
	}


}
