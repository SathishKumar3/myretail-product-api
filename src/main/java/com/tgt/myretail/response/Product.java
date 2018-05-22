
package com.tgt.myretail.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "deep_red_labels",
        "available_to_promise_network",
        "item"
})
public class Product {

    @JsonProperty("deep_red_labels")
    private DeepRedLabels deepRedLabels;
    @JsonProperty("available_to_promise_network")
    private AvailableToPromiseNetwork availableToPromiseNetwork;
    @JsonProperty("item")
    private Item item;

    @JsonProperty("deep_red_labels")
    public DeepRedLabels getDeepRedLabels() {
        return deepRedLabels;
    }

    @JsonProperty("deep_red_labels")
    public void setDeepRedLabels(DeepRedLabels deepRedLabels) {
        this.deepRedLabels = deepRedLabels;
    }

    @JsonProperty("available_to_promise_network")
    public AvailableToPromiseNetwork getAvailableToPromiseNetwork() {
        return availableToPromiseNetwork;
    }

    @JsonProperty("available_to_promise_network")
    public void setAvailableToPromiseNetwork(AvailableToPromiseNetwork availableToPromiseNetwork) {
        this.availableToPromiseNetwork = availableToPromiseNetwork;
    }

    @JsonProperty("item")
    public Item getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(Item item) {
        this.item = item;
    }

}
