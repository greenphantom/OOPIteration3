package Model.Entity.EntityAttributes;

public class Mana {

    private int manaPoints;
    private int maxMana;
    private int regenRate;

    public Mana() {
        manaPoints = 50;
        maxMana = 100;
        regenRate = 1;
    }

    public Mana(int manaPoints, int maxMana, int regenRate) {
        this.manaPoints = manaPoints;
        this.maxMana = maxMana;
        this.regenRate = regenRate;
    }

    public void increaseMana(int amount) {
        if(manaPoints + amount > maxMana) {
            manaPoints = maxMana;
        }

        else {
            manaPoints += amount;
        }
    }

    public void decreaseMana(int amount) {
        if(manaPoints - amount < 0) {
            manaPoints = 0;
        }

        else {
            manaPoints -= amount;
        }
    }

    public int getCurrentMana() {
        return manaPoints;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void refill() {
        manaPoints = maxMana;
    }

    public void regenerate() {
        manaPoints += regenRate;

        if(manaPoints > maxMana) {
            manaPoints = maxMana;
        }
    }
}
