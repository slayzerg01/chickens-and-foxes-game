package Game.objects;

import Game.Cell;

public interface Fox {
    Cell position();

    Fox copy(Cell dest);
}
