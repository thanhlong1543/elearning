
package com.myclass.entity;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "courses")
public class Course {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "letures_count")
	private int leturesCount;
	
	@Column(name = "hour_count")
	private int hourCount;
	
	@Column(name = "view_count")
	private int viewCount = 0;
	
	@Column(name = "price")
	private double price = 0.000f;
	
	@Column(name = "discount")
	private int discount = 0;
	
	@Column(name = "promotion_price")
	private double promotionPrice = 0.000f;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(name = "last_update")
	private Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<UserCourse> userCourse;
	
	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Target> target;
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Video> listVideo;
	
	
	public Course() {
	}
	
	
	
	public Course(int id, String title,String image, int leturesCount, int hourCount,
			int viewCount, double price, int discount, double promotionPrice, String description, String content, int categoryId,
			Timestamp lastUpdate) {
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}