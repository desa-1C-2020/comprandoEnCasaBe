package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

public class Product {

	private int id;
	private String name;
	private String brand;
	private int stock;
	private double price;
	private String imageUrl;
	
	public Product(int id, String name, String brand, int stock, double price, String url) {
		this.id = id;
		this.name = name;
		this.brand = brand; 
		this.stock = stock;
		this.price = price;
		this.imageUrl = url;
	}
	
	public Product(String name, String brand, int stock, double price, String url) {
		this.name = name;
		this.brand = brand; 
		this.stock = stock;
		this.price = price;
		this.imageUrl = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
