package gamePackage;

public class Move {
    public  int x, y;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Move move) {
        return this.x == move.x && this.y == move.y;
    }

    @Override
    public String toString() {
        return "Move{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}