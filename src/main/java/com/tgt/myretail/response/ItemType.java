
package com.tgt.myretail.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "category_type",
        "type",
        "name"
})
public class ItemType {

    @JsonProperty("category_type")
    private String categoryType;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("name")
    private String name;

    @JsonProperty("category_type")
    public String getCategoryType() {
        return categoryType;
    }

    @JsonProperty("category_type")
    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

}
