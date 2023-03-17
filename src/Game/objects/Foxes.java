package Game.objects;

import Game.Cell;



public class Foxes implements Fox {
    private final Cell position;

    public Foxes(Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Fox copy(Cell dest) { return new Foxes(dest);}
}