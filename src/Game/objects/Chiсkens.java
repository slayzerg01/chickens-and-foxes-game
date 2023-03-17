package Game.objects;

import Game.Cell;



public class Chiсkens implements Chiсken {
    private Cell position;

    public Chiсkens(Cell position) {
        this.position = position;
    }


    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if (source.getY() == dest.getY() + 1 && source.getX() == dest.getX()) {
            steps = new Cell[] { dest };
        }
        if (source.getY() == dest.getY()  && source.getX() == dest.getX() + 1 ) {
            steps = new Cell[] { dest };
        }
        if (source.getY() == dest.getY()  && source.getX() == dest.getX() - 1) {
            steps = new Cell[] { dest };
        }
        return steps;
    }
    @Override
    public Chiсken copy(Cell dest) { return new Chiсkens(dest);}
}
