
package com.tgt.myretail.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "manufacturer_style",
        "vendor_name"
})
public class ProductVendor {

    @JsonProperty("id")
    private String id;
    @JsonProperty("manufacturer_style")
    private String manufacturerStyle;
    @JsonProperty("vendor_name")
    private String vendorName;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("manufacturer_style")
    public String getManufacturerStyle() {
        return manufacturerStyle;
    }

    @JsonProperty("manufacturer_style")
    public void setManufacturerStyle(String manufacturerStyle) {
        this.manufacturerStyle = manufacturerStyle;
    }

    @JsonProperty("vendor_name")
    public String getVendorName() {
        return vendorName;
    }

    @JsonProperty("vendor_name")
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

}
