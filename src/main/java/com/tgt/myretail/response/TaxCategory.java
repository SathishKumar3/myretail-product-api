
package com.tgt.myretail.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "tax_class",
        "tax_code_id",
        "tax_code"
})
public class TaxCategory {

    @JsonProperty("tax_class")
    private String taxClass;
    @JsonProperty("tax_code_id")
    private Integer taxCodeId;
    @JsonProperty("tax_code")
    private String taxCode;

    @JsonProperty("tax_class")
    public String getTaxClass() {
        return taxClass;
    }

    @JsonProperty("tax_class")
    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    @JsonProperty("tax_code_id")
    public Integer getTaxCodeId() {
        return taxCodeId;
    }

    @JsonProperty("tax_code_id")
    public void setTaxCodeId(Integer taxCodeId) {
        this.taxCodeId = taxCodeId;
    }

    @JsonProperty("tax_code")
    public String getTaxCode() {
        return taxCode;
    }

    @JsonProperty("tax_code")
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

}
