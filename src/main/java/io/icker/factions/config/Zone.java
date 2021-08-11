package io.icker.factions.config;

import java.util.List;

// first claim constraint will take priority - if none found default to type `claimable`
public class Zone {
    public static enum Type {
        DEFAULT,
        WILDERNESS,
        ADMIN;
    }

    Type type;
    String message;
    Constraint x;
    Constraint y;
    List<String> includedDimensions = List.of("*");
    List<String> excludedDimensions;

    public Zone(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public boolean isApplicable(String dimension, int x, int y) {
        return matchDimension(dimension) && matchCoords(x, y);
    }

    public boolean matchDimension(String dimension) {
        boolean included = includedDimensions.contains("*") || includedDimensions.contains(dimension);
        return excludedDimensions.contains(dimension) ? false : included;
    }

    public boolean matchCoords(int xPos, int yPos) {
        return x.validate(xPos) && y.validate(yPos);
    }

    public String getFailMessage() {
        return message;
    }

    public Type getType() {
        return Type.DEFAULT;
    }
}