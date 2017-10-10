
package com.example.abecruz.cinepolistestia.Perfil.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Level {

    @SerializedName("next_level")
    @Expose
    private String nextLevel;
    @SerializedName("advance_percent")
    @Expose
    private Double advancePercent;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("message")
    @Expose
    private String message;

    public String getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(String nextLevel) {
        this.nextLevel = nextLevel;
    }

    public Double getAdvancePercent() {
        return advancePercent;
    }

    public void setAdvancePercent(Double advancePercent) {
        this.advancePercent = advancePercent;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
