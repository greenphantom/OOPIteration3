package Model.MenuModel;

import Controller.GameLoop;
import Model.Entity.Entity;
import Model.Entity.EntityAttributes.Inventory;
import Model.Item.Item;
import Model.Item.TakeableItem.TakeableItem;

public class TradingMenu extends MenuState {

    private Entity player;
    private Entity npc;
    private Inventory playerInventory;
    private Inventory npcInventory;


    public TradingMenu(MenuModel menuModel, GameLoop gameLoop, Entity player, Entity npc) {
        super(menuModel, gameLoop);
        this.player = player;
        this.playerInventory = player.getInventory();
        this.npc = npc;
        this.npcInventory = npc.getInventory();
    }

    @Override
    public void correctParameters() {
        if(selectedLeftRight < 0) selectedLeftRight = 1;
        if(selectedLeftRight > 1) selectedLeftRight = 0;
        if(selectedLeftRight == 0){
            if (selectedUpDown < 0) selectedUpDown = playerInventory.size() - 1;
            if (selectedUpDown > playerInventory.size() - 1) selectedUpDown = 0;
        }
        if(selectedLeftRight == 1){
            if (selectedUpDown < 0) selectedUpDown = npcInventory.size() - 1;
            if (selectedUpDown > npcInventory.size() - 1) selectedUpDown = 0;
        }
    }

    @Override
    public void select() {
        switch (selectedLeftRight){
            case 0:
                sell();
                break;
            case 1:
                buy();
                break;
        }
    }

    private void sell() {
        TakeableItem itemSelling = playerInventory.getItem(selectedUpDown);
        player.removeItemFromInventory(itemSelling);
        player.addGold(itemSelling.getPrice());
        npc.addItemToInventory(itemSelling);
    }

    private void buy() {
        TakeableItem itemBuying = npcInventory.getItem(selectedUpDown);
        if(player.getGold() >= itemBuying.getPrice()) {
            player.removeGold(itemBuying.getPrice());
            npc.removeItemFromInventory(itemBuying);
            player.addItemToInventory(itemBuying);
        }
    }
}
