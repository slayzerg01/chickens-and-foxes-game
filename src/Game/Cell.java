package Game;
public enum Cell {

    A1(0, 0), A2(1, 0), A3(2, 0), A4(3, 0), A5(4, 0), A6(5, 0), A7(6, 0),
    B1(0, 1), B2(1, 1), B3(2, 1), B4(3, 1), B5(4, 1), B6(5, 1), B7(6, 1),
    C1(0, 2), C2(1, 2), C3(2, 2), C4(3, 2), C5(4, 2), C6(5, 2), C7(6, 2),
    D1(0, 3), D2(1, 3), D3(2, 3), D4(3, 3), D5(4, 3), D6(5, 3), D7(6, 3),
    E1(0, 4), E2(1, 4), E3(2, 4), E4(3, 4), E5(4, 4), E6(5, 4), E7(6, 4),
    F1(0, 5), F2(1, 5), F3(2, 5), F4(3, 5), F5(4, 5), F6(5, 5), F7(6, 5),
    G1(0, 6), G2(1, 6), G3(2, 6), G4(3, 6), G5(4, 6), G6(5, 6), G7(6, 6);


    private int x;
    private int y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}