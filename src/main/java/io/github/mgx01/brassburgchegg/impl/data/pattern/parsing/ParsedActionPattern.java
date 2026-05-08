package io.github.mgx01.brassburgchegg.impl.data.pattern.parsing;

import java.util.ArrayList;
import java.util.List;

public class ParsedActionPattern {
    private final List<GridOffset> validMoves = new ArrayList<>();
    private final List<GridOffset> validAttacks = new ArrayList<>();
    private final List<GridOffset> validSpecials = new ArrayList<>();

    public void addMove(int x, int z) {
        validMoves.add(new GridOffset(x, z));
    }

    public void addAttack(int x, int z) {
        validAttacks.add(new GridOffset(x, z));
    }

    public void addSpecial(int x, int z) {
        validSpecials.add(new GridOffset(x, z));
    }

    public List<GridOffset> getMoves() { return validMoves; }
    public List<GridOffset> getAttacks() { return validAttacks; }
    public List<GridOffset> getSpecials() { return validSpecials; }

    public boolean isEmpty() {
        return validMoves.isEmpty() && validAttacks.isEmpty();
    }
}