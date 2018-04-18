package Controller;

import Controller.Factories.ControllerSetFactory;
import Controller.Factories.EntityFactory;
import Controller.Visitor.SavingVisitor;
import Model.Command.GameLoopCommand.GameLoopCommand;
import Model.Entity.Entity;
import Model.Level.GameModel;
import Model.MenuModel.InventoryMenu;
import Model.MenuModel.MainMenuState;
import Model.MenuModel.MenuModel;
import Model.MenuModel.MenuState;
import View.MenuView.InventoryView;
import View.MenuView.MenuView;
import View.MenuView.MenuViewState;
import View.MenuView.TitleScreenView;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop {

    private AnimationTimer loopTimer;
    private GameModel gameModel;
    private MenuView menuView;
    private MenuModel menuModel;
    private SavingVisitor gameSaver;
    private EntityFactory entityFactory;
    private ControllerSetFactory controllerSetFactory;
    private KeyEventImplementor controls;

    public GameLoop() {
        //TODO: Add loading logic
        controls = new KeyEventImplementor(this);
        loopTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameModel.advance();
            }
        };

        menuModel = new MenuModel(this);
        menuView = new MenuView();

        controls.createMenuSet(menuModel);
        setMenuState(new MainMenuState(menuModel, this), new TitleScreenView(menuModel));
    }

    public void openBarterWindow(Entity playerEntity, int playerBarterStrength, Entity receivingEntity) {
        // TODO: implement
        if(playerEntity == null || receivingEntity == null) {
            // do nothing if either entity is null
        }
    }

    public void openDialogWindow(Entity playerEntity, Entity receivingEntity) {
        // TODO: implement
        if(playerEntity == null || receivingEntity == null) {
            // do nothing if either entity is null
        }
    }

    public void createObservationWindow(String randomEntityFacts) {
        // TODO: implement
    }

    public void startTimer() {
        loopTimer.start();
    }

    public void pauseTimer() {
        loopTimer.stop();
    }

    public void loadGame(int i) {

    }

    public void saveGame(int i) {

    }

    public void newGame(int i) {

    }

    public void setMenuState(MenuState menuState, MenuViewState menuViewState){
        menuModel.setActiveState(menuState);
        menuView.setActiveState(menuViewState);
    }

    public void render(GraphicsContext gc){
        menuView.render(gc);
    }

    public KeyEventImplementor getControls() {
        return controls;
    }
}
