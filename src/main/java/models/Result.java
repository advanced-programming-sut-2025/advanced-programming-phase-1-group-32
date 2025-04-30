package models;

public record Result(Boolean success, String message) {
    @Override
    public String toString() {
        return message;
    }
}
