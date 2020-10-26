package com.myclass.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;


public class CourseDto {
	private int id;
	@NotBlank(message = "Vui lòng nhập tiêu đề")
	@Length(max = 255, message = "Vui lòng không nhập quá 255 ký tự.")
	private String title;
	private String image;
	private int leturesCount;
	private int hourCount;
	private int viewCount = 0;
	private double price = 0.000f;
	private int discount = 0;
	private double promotionPrice = 0.000f;
	@NotBlank(message = "Vui lòng nhập mô tả")
	private String description;
	private String content;
	private int categoryId;
	private String categoryTitle;
	private Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
	private MultipartFile fileImage;
	
	public CourseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CourseDto(int id, String title, String image, int leturesCount, int hourCount, int viewCount,
			double price, int discount, double promotionPrice, String description, String content,
			int categoryId, Timestamp lastUpdate,String categoryTitle) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.leturesCount = leturesCount;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;
		this.categoryId = categoryId;
		this.lastUpdate = lastUpdate;
		this.categoryTitle = categoryTitle;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getLeturesCount() {
		return leturesCount;
	}
	public void setLeturesCount(int leturesCount) {
		this.leturesCount = leturesCount;
	}
	public int getHourCount() {
		return hourCount;
	}
	public void setHourCount(int hourCount) {
		this.hourCount = hourCount;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public double getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public MultipartFile getFileImage() {
		return fileImage;
	}
	public void setFileImage(MultipartFile fileImage) {
		this.fileImage = fileImage;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	
	
}
