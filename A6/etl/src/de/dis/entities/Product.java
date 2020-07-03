package de.dis.entities;

public class Product {

    private int id;
    private int articleId;
    private String articleName;
    private int productGroupId;
    private String productGroupName;
    private int productFamilyId;
    private String productFamilyName;
    private int productCategoryId;
    private String productCategoryName;
    private double price;

    public Product() {

    }

    public Product(int id, int articleId, String articleName, int productGroupId, String productGroupName, int productFamilyId, String productFamilyName, int productCategoryId, String productCategoryName, double price) {
        this.id = id;
        this.articleId = articleId;
        this.articleName = articleName;
        this.productGroupId = productGroupId;
        this.productGroupName = productGroupName;
        this.productFamilyId = productFamilyId;
        this.productFamilyName = productFamilyName;
        this.productCategoryId = productCategoryId;
        this.productCategoryName = productCategoryName;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public int getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(int productGroupId) {
        this.productGroupId = productGroupId;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public int getProductFamilyId() {
        return productFamilyId;
    }

    public void setProductFamilyId(int productFamilyId) {
        this.productFamilyId = productFamilyId;
    }

    public String getProductFamilyName() {
        return productFamilyName;
    }

    public void setProductFamilyName(String productFamilyName) {
        this.productFamilyName = productFamilyName;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String string =  "Article ID: " + this.getArticleId() + "\n"
                + "Article Name: " + this.getArticleName() + "\n"
                + "Product Group ID: " + this.getProductGroupId() + "\n"
                + "Product Group Name: " + this.getProductGroupName() + "\n"
                + "Product Family ID: " + this.getProductFamilyId() + "\n"
                + "Product Family Name: " + this.getProductFamilyName() + "\n"
                + "Product Category ID: " + this.getProductCategoryId() + "\n"
                + "Product Category Name: " + this.getProductCategoryName() + "\n"
                + "Price: " + this.getPrice() + "\n";
        return string;
    }
}
