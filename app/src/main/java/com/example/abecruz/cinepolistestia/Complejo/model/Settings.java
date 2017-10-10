package com.example.abecruz.cinepolistestia.Complejo.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by albertocruz on 29/09/17.
 */


public class Settings {

    @SerializedName("is_special_prices")
    @Expose
    private Boolean isSpecialPrices;
    @SerializedName("type_food_sales")
    @Expose
    private String typeFoodSales;
    @SerializedName("cs_merchant_id")
    @Expose
    private String csMerchantId;
    @SerializedName("vco_merchant_id")
    @Expose
    private String vcoMerchantId;

    public Boolean getIsSpecialPrices() {
        return isSpecialPrices;
    }

    public void setIsSpecialPrices(Boolean isSpecialPrices) {
        this.isSpecialPrices = isSpecialPrices;
    }

    public String getTypeFoodSales() {
        return typeFoodSales;
    }

    public void setTypeFoodSales(String typeFoodSales) {
        this.typeFoodSales = typeFoodSales;
    }

    public String getCsMerchantId() {
        return csMerchantId;
    }

    public void setCsMerchantId(String csMerchantId) {
        this.csMerchantId = csMerchantId;
    }

    public String getVcoMerchantId() {
        return vcoMerchantId;
    }

    public void setVcoMerchantId(String vcoMerchantId) {
        this.vcoMerchantId = vcoMerchantId;
    }

}