package model.items.factoryItem;

public abstract class AbstractFactoryItem implements IFactoryItem {
    protected String name;
    protected int power;
    protected int minRange;
    protected int maxRange;

    /**
     * Setters the parameters for default to each items
     * @param name is the name of the item
     */
    public AbstractFactoryItem(String name){
        this.name = name;
        this.power = 10;
        this.minRange = 1;
        this.maxRange = 2;
    }

    /**
     * Setter the parameters for default in the creation
     * @param name is the name of the item
     * @param power power for default of the item
     * @param minRange for default of the item
     * @param maxRange for default of the item
     */
    public AbstractFactoryItem(String name, int power, int minRange, int maxRange){
        this.name = name;
        this.power = power;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getMaxRange() {
        return maxRange;
    }
    @Override
    public int getMinRange() {
        return minRange;
    }
    @Override
    public int getPower() {
        return power;
    }
    @Override
    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }
    @Override
    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void setPower(int power) {
        this.power = power;
    }
}
