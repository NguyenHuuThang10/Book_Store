package model;

public class Product {

	private String id;
	private String name;
	private Author author;
	private int publishYear;
	private double importPrice;
	private double originalPrice;
	private double sellPrice;
	private int quantity;
	private Category categogy;
	private String language;
	private String description;
	private String img;
	
	public Product() {
		
	}

	public Product(String id, String name, Author author, int publishYear, double importPrice, double originalPrice,
			double sellPrice, int quantity, Category categogy, String language, String description, String img) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.publishYear = publishYear;
		this.importPrice = importPrice;
		this.originalPrice = originalPrice;
		this.sellPrice = sellPrice;
		this.quantity = quantity;
		this.categogy = categogy;
		this.language = language;
		this.description = description;
		this.img = img;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public double getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(double importPrice) {
		this.importPrice = importPrice;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategogy() {
		return categogy;
	}

	public void setCategogy(Category categogy) {
		this.categogy = categogy;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
	
}
