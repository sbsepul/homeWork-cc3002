package model.items.factoryItem;

public abstract class AbstractFactoryItem implements IFactoryItem {
    protected String name;
    protected int power;
    protected int minRange;
    protected int maxRange;

    /**
     * Setters the parameters for default to each items
     * @param name
     */
    public AbstractFactoryItem(String name){
        this.name = name;
        this.power = 50;
        this.minRange = 1;
        this.maxRange = 2;
    }

    public AbstractFactoryItem(String name, int power){
        this.name = name;
        this.power = power;
        this.minRange = 1;
        this.maxRange = 2;
    }
    public AbstractFactoryItem(String name, int power, int minRange, int maxRange){
        this.name = name;
        this.power = power;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

}
