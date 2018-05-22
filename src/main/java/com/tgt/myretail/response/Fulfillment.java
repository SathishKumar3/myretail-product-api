
package com.tgt.myretail.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "is_po_box_prohibited",
        "po_box_prohibited_message"
})
public class Fulfillment {

    @JsonProperty("is_po_box_prohibited")
    private Boolean isPoBoxProhibited;
    @JsonProperty("po_box_prohibited_message")
    private String poBoxProhibitedMessage;

    @JsonProperty("is_po_box_prohibited")
    public Boolean getIsPoBoxProhibited() {
        return isPoBoxProhibited;
    }

    @JsonProperty("is_po_box_prohibited")
    public void setIsPoBoxProhibited(Boolean isPoBoxProhibited) {
        this.isPoBoxProhibited = isPoBoxProhibited;
    }

    @JsonProperty("po_box_prohibited_message")
    public String getPoBoxProhibitedMessage() {
        return poBoxProhibitedMessage;
    }

    @JsonProperty("po_box_prohibited_message")
    public void setPoBoxProhibitedMessage(String poBoxProhibitedMessage) {
        this.poBoxProhibitedMessage = poBoxProhibitedMessage;
    }

}
