package model;
/*   
 */
public class JdModel {
    private String goodId;
    private String goodName;
    private String goodPrice;
    private String goodOriginalPrice;
    private String url;
    public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGoodOriginalPrice() {
		return goodOriginalPrice;
	}
	public void setGoodOriginalPrice(String goodOriginalPrice) {
		this.goodOriginalPrice = goodOriginalPrice;
	}
	public String getGoodID() {
        return goodId;
    }
    public void setGoodID(String goodID) {
        this.goodId = goodID;
    }
    public String getGoodName() {
        return goodName;
    }
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
    public String getGoodPrice() {
        return goodPrice;
    }
    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }
}
