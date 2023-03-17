package Game;

import Game.objects.Chiсken;
import Game.objects.Fox;
import java.util.Random;


public class Logic {
    private static final Chiсken[] CHIKENS = new Chiсken[20];
    private static final Fox[] FOXES = new Fox[2];
    private int index = 0;
    private int indexf = 0;
    private int win = 20;

    public void add(Chiсken chiken) {
        this.CHIKENS[this.index++] = chiken;
        System.out.println("CHICKEN["+index+"]");
    }
    public void add(Fox fox) {
        this.FOXES[this.indexf++] = fox;
        System.out.println("FOX["+indexf+"]");
    }
    public Chiсken getChicken(int i){
        return this.CHIKENS[i];
    }
    public Fox getFox(int i){
        return this.FOXES[i];
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findCh(source);
        if (findCh(dest) == -1 && findFox(dest)==-1){
            if (index != -1) {
                Cell[] steps = this.CHIKENS[index].way(source, dest);
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    if (!wall(steps[0])){
                        return rst;
                    }
                    rst = true;
                    this.CHIKENS[index] = this.CHIKENS[index].copy(dest);
                }

            }
        }
        return rst;
    }

    private boolean wall(Cell cell){
        boolean rst = false;
        if (cell != Cell.A2 && cell != Cell.B2 && cell != Cell.B1 && cell != Cell.A6 && cell != Cell.B6 &&
                cell != Cell.B7 && cell != Cell.F1 && cell != Cell.F2 && cell != Cell.G2 && cell != Cell.G6 &&
                   cell != Cell.F6 && cell != Cell.F7){
            rst = true;
        }
        return rst;
    }

    private void minus(){
        win -=1;
    }
    public boolean winFox(){
        boolean rst = false;
        if (win==8){
            rst = true;
        }
        return rst;
    }

    public boolean winCh(){
        boolean rst = false;
        if (findCh(Cell.A3)!=-1 && findCh(Cell.A4)!=-1 && findCh(Cell.A5)!=-1 && findCh(Cell.B3)!=-1 &&
                findCh(Cell.B4)!=-1 && findCh(Cell.B5)!=-1 && findCh(Cell.C3)!=-1 && findCh(Cell.C4)!=-1 &&
                findCh(Cell.C5)!=-1){
            rst = true;
        }
        return rst;
    }

    public void eatFox(boolean blok){
        if (locator(FOXES[0].position()) != Cell.A1){
            System.out.println("FOX 1 eat");
            eatFox(false);
        }
        else if (locator(FOXES[1].position()) != Cell.A1){
            System.out.println("FOX 2 eat");
            eatFox(false);
        }
        else {
            if (blok){
            System.out.println("NOT EAT ");
            final Random random = new Random();
            boolean complete = false;
            while (!complete){
                int fox = random.nextInt(2);
                if (random.nextInt(2) == 0){
                    if (random.nextInt(2)==0){
                        for (Cell cell : Cell.values()) {
                            if (cell.getX() == FOXES[fox].position().getX() + 1 && cell.getY() == FOXES[fox].position().getY()) {
                                if (findFox(cell)==-1 && findCh(cell)==-1 && wall(cell)){
                                    FOXES[fox] = FOXES[fox].copy(cell);
                                    complete = true;
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        for (Cell cell : Cell.values()) {
                            if (cell.getX() == FOXES[fox].position().getX() - 1 && cell.getY() == FOXES[fox].position().getY()) {
                                if (findFox(cell)==-1 && findCh(cell)==-1 && wall(cell)){
                                    FOXES[fox] = FOXES[fox].copy(cell);
                                    complete = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                else {
                    if (random.nextInt(2)==0){
                        for (Cell cell : Cell.values()) {
                            if (cell.getX() == FOXES[fox].position().getX() && cell.getY() == FOXES[fox].position().getY() + 1) {
                                if (findFox(cell)==-1 && findCh(cell)==-1 && wall(cell)){
                                    FOXES[fox] = FOXES[fox].copy(cell);
                                    complete = true;
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        for (Cell cell : Cell.values()) {
                            if (cell.getX() == FOXES[fox].position().getX() && cell.getX() == FOXES[fox].position().getY() - 1) {
                                if (findFox(cell)==-1 && findCh(cell)==-1 && wall(cell)){
                                    FOXES[fox] = FOXES[fox].copy(cell);
                                    complete = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        }
    }

    private Cell locator(Cell posit){
            Cell rst = Cell.A1;
            for (Cell cell : Cell.values()) {
                if (cell.getX()== posit.getX()+1 && cell.getY() == posit.getY()) {
                    if (findCh(cell)!=-1){
                        for (Cell victim : Cell.values()) {
                            if (victim.getX() == posit.getX()+2 && victim.getY() == posit.getY()) {
                                if (findCh(victim)==-1 && findFox(victim)==-1 && wall(victim)){
                                    rst = victim;
                                    CHIKENS[findCh(cell)] = CHIKENS[findCh(cell)].copy(Cell.A1);
                                    FOXES[findFox(posit)] = FOXES[findFox(posit)].copy(rst);
                                    minus();
                                    return rst;
                                }
                            }
                        }
                    }
                }
            }
            for (Cell cell : Cell.values()) {
                if (cell.getX() == posit.getX()-1 && cell.getY() == posit.getY()) {
                    if (findCh(cell)!=-1) {
                        for (Cell victim : Cell.values()) {
                            if (victim.getX() == posit.getX()-2 && victim.getY() == posit.getY()) {
                                if (findCh(victim)==-1 && findFox(victim)==-1 && wall(victim)){
                                    rst = victim;
                                    CHIKENS[findCh(cell)] = CHIKENS[findCh(cell)].copy(Cell.A1);
                                    FOXES[findFox(posit)] = FOXES[findFox(posit)].copy(rst);
                                    minus();
                                    return rst;
                                }
                            }
                        }
                    }
                }
            }
            for (Cell cell : Cell.values()) {
                if (cell.getX() == posit.getX() && cell.getY() == posit.getY()+1) {
                    if (findCh(cell)!=-1) {
                        for (Cell victim : Cell.values()) {
                            if (victim.getX() == posit.getX() && victim.getY() == posit.getY()+2) {
                                if (findCh(victim)==-1 && findFox(victim)==-1 && wall(victim)){
                                    rst = victim;
                                    CHIKENS[findCh(cell)] = CHIKENS[findCh(cell)].copy(Cell.A1);
                                    FOXES[findFox(posit)] = FOXES[findFox(posit)].copy(rst);
                                    minus();
                                    return rst;
                                }
                            }
                        }
                    }
                }
            }
            for (Cell cell : Cell.values()) {
                if (cell.getX() == posit.getX() && cell.getY() == posit.getY() - 1) {
                    if (findCh(cell) != -1) {
                        for (Cell victim : Cell.values()) {
                            if (victim.getX() == posit.getX() && victim.getY() == posit.getY() - 2) {
                                if (findCh(victim) == -1 && findFox(victim) == -1 && wall(victim)) {
                                    rst = victim;
                                    CHIKENS[findCh(cell)] = CHIKENS[findCh(cell)].copy(Cell.A1);
                                    FOXES[findFox(posit)] = FOXES[findFox(posit)].copy(rst);
                                    minus();
                                    return rst;
                                }
                            }
                        }
                    }
                }
            }
            return rst;
    }

    private int findCh(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.CHIKENS.length; index++) {
            if (this.CHIKENS[index] != null && this.CHIKENS[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    private int findFox(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.FOXES.length; index++) {
            if (this.FOXES[index] != null && this.FOXES[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
