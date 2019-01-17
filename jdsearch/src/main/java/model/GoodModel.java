package model;
/*   
 */
public class GoodModel {
	/**
	 * 序号
	 */
	private String index;
    /**
     * 货号
     */
	private String goodNo;
    /**
     * 商品名称
     */
    private String goodName;
    /**
     * 京东价格
     */
    private String goodPrice;
    public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}
	public String getPromotionalPrice() {
		return promotionalPrice;
	}
	public void setPromotionalPrice(String promotionalPrice) {
		this.promotionalPrice = promotionalPrice;
	}
	public String getAgreementPrice() {
		return agreementPrice;
	}
	public void setAgreementPrice(String agreementPrice) {
		this.agreementPrice = agreementPrice;
	}
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	/**
     * 现在价格
     */
    private String nowPrice;
    /**
     * 促销价格
     */
    private String promotionalPrice;
    /**
     * 协议价
     */
    private String agreementPrice;
    /**
     * 销售价
     */
    private String salePrice;
    public String getGoodNo() {
        return goodNo;
    }
    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo;
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
