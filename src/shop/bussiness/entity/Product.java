package shop.bussiness.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Product implements Serializable {
    private String productId;
    private String productName;
    private float productPrice;
    private float productDiscount;
    private float exportPriceProduct;
    private String title;
    private String descriptionsProduct;
    private List<Color> colorList;
    private List<Size> sizeList;
    private Catalog catalog;
    private boolean statusProduct;
    private Date dateInputProduct;

    public Product() {
    }

    public Product(String productId, String productName, float productPrice, float productDiscount, float exportPriceProduct, String title, String descriptionsProduct, List<Color> colorList, List<Size> sizeList, Catalog catalog, boolean statusProduct, Date dateInputProduct) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.exportPriceProduct = exportPriceProduct;
        this.title = title;
        this.descriptionsProduct = descriptionsProduct;
        this.colorList = colorList;
        this.sizeList = sizeList;
        this.catalog = catalog;
        this.statusProduct = statusProduct;
        this.dateInputProduct = dateInputProduct;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public float getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(float productDiscount) {
        this.productDiscount = productDiscount;
    }

    public float getExportPriceProduct() {
        return exportPriceProduct;
    }

    public void setExportPriceProduct(float exportPriceProduct) {
        this.exportPriceProduct = exportPriceProduct;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionsProduct() {
        return descriptionsProduct;
    }

    public void setDescriptionsProduct(String descriptionsProduct) {
        this.descriptionsProduct = descriptionsProduct;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public void setColorList(List<Color> colorList) {
        this.colorList = colorList;
    }

    public List<Size> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<Size> sizeList) {
        this.sizeList = sizeList;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(boolean statusProduct) {
        this.statusProduct = statusProduct;
    }

    public Date getDateInputProduct() {
        return dateInputProduct;
    }

    public void setDateInputProduct(Date dateInputProduct) {
        this.dateInputProduct = dateInputProduct;
    }
}
