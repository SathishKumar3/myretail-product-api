
package com.tgt.myretail.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "is_lead_disclosure"
})
public class EnvironmentalSegmentation {

    @JsonProperty("is_lead_disclosure")
    private Boolean isLeadDisclosure;

    @JsonProperty("is_lead_disclosure")
    public Boolean getIsLeadDisclosure() {
        return isLeadDisclosure;
    }

    @JsonProperty("is_lead_disclosure")
    public void setIsLeadDisclosure(Boolean isLeadDisclosure) {
        this.isLeadDisclosure = isLeadDisclosure;
    }

}
