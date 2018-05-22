
package com.tgt.myretail.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "weight",
        "weight_unit_of_measure",
        "width",
        "depth",
        "height",
        "dimension_unit_of_measure"
})
public class PackageDimensions {

    @JsonProperty("weight")
    private String weight;
    @JsonProperty("weight_unit_of_measure")
    private String weightUnitOfMeasure;
    @JsonProperty("width")
    private String width;
    @JsonProperty("depth")
    private String depth;
    @JsonProperty("height")
    private String height;
    @JsonProperty("dimension_unit_of_measure")
    private String dimensionUnitOfMeasure;

    @JsonProperty("weight")
    public String getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(String weight) {
        this.weight = weight;
    }

    @JsonProperty("weight_unit_of_measure")
    public String getWeightUnitOfMeasure() {
        return weightUnitOfMeasure;
    }

    @JsonProperty("weight_unit_of_measure")
    public void setWeightUnitOfMeasure(String weightUnitOfMeasure) {
        this.weightUnitOfMeasure = weightUnitOfMeasure;
    }

    @JsonProperty("width")
    public String getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(String width) {
        this.width = width;
    }

    @JsonProperty("depth")
    public String getDepth() {
        return depth;
    }

    @JsonProperty("depth")
    public void setDepth(String depth) {
        this.depth = depth;
    }

    @JsonProperty("height")
    public String getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(String height) {
        this.height = height;
    }

    @JsonProperty("dimension_unit_of_measure")
    public String getDimensionUnitOfMeasure() {
        return dimensionUnitOfMeasure;
    }

    @JsonProperty("dimension_unit_of_measure")
    public void setDimensionUnitOfMeasure(String dimensionUnitOfMeasure) {
        this.dimensionUnitOfMeasure = dimensionUnitOfMeasure;
    }

}
