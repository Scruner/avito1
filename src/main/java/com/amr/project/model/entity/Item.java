package com.amr.project.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal basePrice;
    private BigDecimal price;
    private Long categoryId;

    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "item_image", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns
            = @JoinColumn(name = "image_id"))
    private List<Image> images;

    @OneToMany (mappedBy = "item" , cascade = CascadeType.ALL)
    private List<Review> reviews;

    private int count;
    private double rating;
    private String description;
    private int discount;

    @ManyToOne
    @JoinColumn (name = "shop_id")
    private Shop shop;

    @Column(name = "is_moderated")
    private boolean isModerated = false;
    private boolean isModerateAccept = false;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;

    public Item(Long id, String name, BigDecimal basePrice, BigDecimal price, Long categoryId, List<Image> images, List<Review> reviews, int count, double rating, String description, int discount, Shop shop, boolean isModerated, String moderatedRejectReason, boolean isPretendedToBeDeleted, boolean isModerateAccept) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.price = price;
        this.categoryId = categoryId;
        this.images = images;
        this.reviews = reviews;
        this.count = count;
        this.rating = rating;
        this.description = description;
        this.discount = discount;
        this.shop = shop;
        this.isModerated = isModerated;
        this.isModerateAccept = isModerateAccept;
        this.moderatedRejectReason = moderatedRejectReason;
        this.isPretendedToBeDeleted = isPretendedToBeDeleted;
    }

    public Item() {
    }

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getBasePrice() {
        return this.basePrice;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }

    public int getCount() {
        return this.count;
    }

    public double getRating() {
        return this.rating;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDiscount() {
        return this.discount;
    }

    public Shop getShop() {
        return this.shop;
    }

    public boolean isModerated() {
        return this.isModerated;
    }

    public String getModeratedRejectReason() {
        return this.moderatedRejectReason;
    }

    public boolean isPretendedToBeDeleted() {
        return this.isPretendedToBeDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setModerated(boolean isModerated) {
        this.isModerated = isModerated;
    }

    public void setModeratedRejectReason(String moderatedRejectReason) {
        this.moderatedRejectReason = moderatedRejectReason;
    }

    public void setPretendedToBeDeleted(boolean isPretendedToBeDeleted) {
        this.isPretendedToBeDeleted = isPretendedToBeDeleted;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Item)) return false;
        final Item other = (Item) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$basePrice = this.getBasePrice();
        final Object other$basePrice = other.getBasePrice();
        if (this$basePrice == null ? other$basePrice != null : !this$basePrice.equals(other$basePrice)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$categoryId = this.getCategoryId();
        final Object other$categoryId = other.getCategoryId();
        if (this$categoryId == null ? other$categoryId != null : !this$categoryId.equals(other$categoryId))
            return false;
        final Object this$images = this.getImages();
        final Object other$images = other.getImages();
        if (this$images == null ? other$images != null : !this$images.equals(other$images)) return false;
        final Object this$reviews = this.getReviews();
        final Object other$reviews = other.getReviews();
        if (this$reviews == null ? other$reviews != null : !this$reviews.equals(other$reviews)) return false;
        if (this.getCount() != other.getCount()) return false;
        if (Double.compare(this.getRating(), other.getRating()) != 0) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        if (this.getDiscount() != other.getDiscount()) return false;
        final Object this$shop = this.getShop();
        final Object other$shop = other.getShop();
        if (this$shop == null ? other$shop != null : !this$shop.equals(other$shop)) return false;
        if (this.isModerated() != other.isModerated()) return false;
        final Object this$moderatedRejectReason = this.getModeratedRejectReason();
        final Object other$moderatedRejectReason = other.getModeratedRejectReason();
        if (this$moderatedRejectReason == null ? other$moderatedRejectReason != null : !this$moderatedRejectReason.equals(other$moderatedRejectReason))
            return false;
        if (this.isPretendedToBeDeleted() != other.isPretendedToBeDeleted()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Item;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $basePrice = this.getBasePrice();
        result = result * PRIME + ($basePrice == null ? 43 : $basePrice.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $categoryId = this.getCategoryId();
        result = result * PRIME + ($categoryId == null ? 43 : $categoryId.hashCode());
        final Object $images = this.getImages();
        result = result * PRIME + ($images == null ? 43 : $images.hashCode());
        final Object $reviews = this.getReviews();
        result = result * PRIME + ($reviews == null ? 43 : $reviews.hashCode());
        result = result * PRIME + this.getCount();
        final long $rating = Double.doubleToLongBits(this.getRating());
        result = result * PRIME + (int) ($rating >>> 32 ^ $rating);
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        result = result * PRIME + this.getDiscount();
        final Object $shop = this.getShop();
        result = result * PRIME + ($shop == null ? 43 : $shop.hashCode());
        result = result * PRIME + (this.isModerated() ? 79 : 97);
        final Object $moderatedRejectReason = this.getModeratedRejectReason();
        result = result * PRIME + ($moderatedRejectReason == null ? 43 : $moderatedRejectReason.hashCode());
        result = result * PRIME + (this.isPretendedToBeDeleted() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "Item(id=" + this.getId() + ", name=" + this.getName() + ", basePrice=" + this.getBasePrice() + ", price=" + this.getPrice() + ", categoryId=" + this.getCategoryId() + ", images=" + this.getImages() + ", reviews=" + this.getReviews() + ", count=" + this.getCount() + ", rating=" + this.getRating() + ", description=" + this.getDescription() + ", discount=" + this.getDiscount() + ", shop=" + this.getShop() + ", isModerated=" + this.isModerated() + ", moderatedRejectReason=" + this.getModeratedRejectReason() + ", isPretendedToBeDeleted=" + this.isPretendedToBeDeleted() + ")";
    }

    public static class ItemBuilder {
        private Long id;
        private String name;
        private BigDecimal basePrice;
        private BigDecimal price;
        private Long categoryId;
        private List<Image> images;
        private List<Review> reviews;
        private int count;
        private double rating;
        private String description;
        private int discount;
        private Shop shop;
        private boolean isModerated;
        private boolean isModerateAccept;
        private String moderatedRejectReason;
        private boolean isPretendedToBeDeleted;

        ItemBuilder() {
        }

        public ItemBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ItemBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder basePrice(BigDecimal basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        public ItemBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ItemBuilder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public ItemBuilder images(List<Image> images) {
            this.images = images;
            return this;
        }

        public ItemBuilder reviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public ItemBuilder count(int count) {
            this.count = count;
            return this;
        }

        public ItemBuilder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public ItemBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ItemBuilder discount(int discount) {
            this.discount = discount;
            return this;
        }

        public ItemBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public ItemBuilder isModerated(boolean isModerated) {
            this.isModerated = isModerated;
            return this;
        }

        public ItemBuilder moderatedRejectReason(String moderatedRejectReason) {
            this.moderatedRejectReason = moderatedRejectReason;
            return this;
        }

        public ItemBuilder isPretendedToBeDeleted(boolean isPretendedToBeDeleted) {
            this.isPretendedToBeDeleted = isPretendedToBeDeleted;
            return this;
        }

        public ItemBuilder isModerateAccept(boolean isModerateAccept) {
            this.isModerateAccept = isModerateAccept;
            return this;
        }

        public Item build() {
            return new Item(id, name, basePrice, price, categoryId, images, reviews, count, rating, description, discount, shop, isModerated, moderatedRejectReason, isPretendedToBeDeleted, isModerateAccept);
        }

        public String toString() {
            return "Item.ItemBuilder(id=" + this.id + ", name=" + this.name + ", basePrice=" + this.basePrice + ", price=" + this.price + ", categoryId=" + this.categoryId + ", images=" + this.images + ", reviews=" + this.reviews + ", count=" + this.count + ", rating=" + this.rating + ", description=" + this.description + ", discount=" + this.discount + ", shop=" + this.shop + ", isModerated=" + this.isModerated + ", moderatedRejectReason=" + this.moderatedRejectReason + ", isPretendedToBeDeleted=" + this.isPretendedToBeDeleted + ")";
        }
    }
}
