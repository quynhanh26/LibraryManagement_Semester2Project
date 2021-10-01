package model_view;

public class Books_Category {

	private int no;
	private String bookId;
	private String title;
	private String author;
	private String categoryId;
	private int quantity;
	private String nameCategory;
	private String img;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

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

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
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

	public Books_Category() {
		super();
	}

	public Books_Category(int no, String bookId, String title, String author, String categoryId, int quantity,
			String nameCategory, String img) {
		super();
		this.no = no;
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.categoryId = categoryId;
		this.quantity = quantity;
		this.nameCategory = nameCategory;
		this.img = img;
	}



}
