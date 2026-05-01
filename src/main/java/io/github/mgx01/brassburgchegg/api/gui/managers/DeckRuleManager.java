package io.github.mgx01.brassburgchegg.api.gui.managers;

public class DeckRuleManager {
    private byte maxDeckSize = 15;
    private byte minCardAmount = 0;
    private byte maxCardAmount = 3;

    public int getMaxDeckSize() {
        return maxDeckSize;
    }
    public void setMaxDeckSize(byte newLimit) {
        this.maxDeckSize = newLimit;
    }

    public byte getMinCardAmount(){return minCardAmount;}
    public void setMinCardAmount(byte newMinimum){this.minCardAmount = newMinimum;}

    public byte getMaxCardAmount(){return maxCardAmount;}
    public void setMaxCardAmount(byte newMaximimum){this.maxCardAmount = newMaximimum;}


    public boolean canAddMore(int currentTotal) {
        return currentTotal < maxDeckSize;
    }
}