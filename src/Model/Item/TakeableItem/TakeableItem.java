package Model.Item.TakeableItem;

import Controller.Visitor.Visitable;
import Model.Command.Command;
import Model.Command.EntityCommand.NonSettableCommand.DropItemCommand;
import Model.Entity.Entity;
import Model.Item.Item;
import Model.Item.TakeableItem.InventoryStrategy.DropStrategy;
import Model.Level.LevelMessenger;

public abstract class TakeableItem extends Item implements Visitable {

    private int price;
    private DropStrategy dropStrategy;

    protected TakeableItem(String name, Command command) {
        super(name, command);
    }

    @Override
    public void onTouch(Entity entity) {
        entity.addItemToInventory(this);

        if(entity.hasItemInInventory(this)) {
            setDropStrategyEntity(entity);

            setItemStrategyEntity(entity);

            setToBeDeleted();
        }
    }

    final public void drop(){
        dropStrategy.useStrategy();
    }



    public void setCurrentLevelMessenger(LevelMessenger levelMessenger) {
        dropStrategy = new DropStrategy(this, new DropItemCommand(levelMessenger));
    }

    public abstract void setItemStrategyEntity(Entity entity);

    public void setDropStrategyEntity(Entity entity) {
        dropStrategy.setEntity(entity);
        if(observer != null) {
            getObserver().setToRender(false);
        }
    }

    public abstract void select();

    public int getPrice() {
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public abstract boolean usableByEntity(Entity entity);
}
