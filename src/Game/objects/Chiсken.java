package Game.objects;

import Game.Cell;


public interface Chiсken {
    Cell position();

    Cell[] way(Cell source,Cell dest);

    Chiсken copy(Cell dest);
}
