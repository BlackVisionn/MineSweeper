package Model;

public enum CellState {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    WALL,
    BLOCKED,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Object image;

    CellState getNextNumber ()
    {
        return CellState.values() [this.ordinal() + 1];
    }

    int getNumber ()
    {
        return this.ordinal();
    }
}
