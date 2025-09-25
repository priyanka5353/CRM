package edu.ccrm.domain;

public enum Grade {
    S(10), A(9), B(8), C(7), D(6), F(0);

    private final int points;

    Grade(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return name() + " (" + points + ")";
    }

    // 🔹 Helper method for user input
    public static Grade fromInput(String input) {
        input = input.trim().toUpperCase();
        switch (input) {
            case "S": return S;
            case "A": return A;
            case "B": return B;
            case "C": return C;
            case "D": return D;
            case "F": return F;
            default: throw new IllegalArgumentException("Invalid grade: " + input);
        }
    }
}
