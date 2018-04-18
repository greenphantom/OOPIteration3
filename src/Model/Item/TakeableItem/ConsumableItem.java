package Model.Item.TakeableItem;

import Controller.Visitor.SavingVisitor;
import Model.Command.Command;
import Model.Entity.Entity;
import Model.Item.TakeableItem.InventoryStrategy.ConsumeStrategy;

public class ConsumableItem extends TakeableItem {

    private ConsumeStrategy consumeStrategy;

    public ConsumableItem(String name, Command command) {
        super(name, command);

        this.consumeStrategy = new ConsumeStrategy(this);
    }

    @Override
    public void select() {
        consumeStrategy.useStrategy();
    }

    protected void setItemStrategyEntity(Entity entity) {
        consumeStrategy.setEntity(entity);
    }

    public void consume(Entity entity) {
        executeCommand(entity);
        entity.removeItemFromInventory(this);
    }

    @Override
    public void accept(SavingVisitor visitor) {
        visitor.visitItem(this);
    }
}
