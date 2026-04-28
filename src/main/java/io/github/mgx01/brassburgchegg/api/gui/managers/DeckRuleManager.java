package io.github.mgx01.brassburgchegg.api.gui.managers;

public class DeckRuleManager {
    private int maxDeckSize = 15;

    public int getMaxDeckSize() {
        return maxDeckSize;
    }

    public void setMaxDeckSize(int newLimit) {
        this.maxDeckSize = newLimit;
    }

    public boolean canAddMore(int currentTotal) {
        return currentTotal < maxDeckSize;
    }
}