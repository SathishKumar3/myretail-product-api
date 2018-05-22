
package com.tgt.myretail.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "user",
        "policyDays",
        "guestMessage"
})
public class ReturnPolicies {

    @JsonProperty("user")
    private String user;
    @JsonProperty("policyDays")
    private String policyDays;
    @JsonProperty("guestMessage")
    private String guestMessage;

    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty("policyDays")
    public String getPolicyDays() {
        return policyDays;
    }

    @JsonProperty("policyDays")
    public void setPolicyDays(String policyDays) {
        this.policyDays = policyDays;
    }

    @JsonProperty("guestMessage")
    public String getGuestMessage() {
        return guestMessage;
    }

    @JsonProperty("guestMessage")
    public void setGuestMessage(String guestMessage) {
        this.guestMessage = guestMessage;
    }

}
