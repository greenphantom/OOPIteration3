package View.MenuView;

import Configs.Commons;
import Model.Entity.Entity;
import Model.MenuModel.MenuModel;
import Model.MenuModel.PetMenu;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class PetView extends InGameMenuView {

    private int selectedX;
    private int selectedY;
    private int selectedPet;
    private int selectedFocus;
    private int selectedPriority;
    private String[] focuses = {"General", "Combat", "Item", "Passive"};
    private String[] priorities = {"Items", "Player", "Enemies", "None"};
    private List<Entity> pets;

    public PetView(MenuModel menuModel) {
        super(menuModel);
    }

    @Override
    protected void renderSubMenu(GraphicsContext gc) {
        selectedX = menuModel.getSelectedHorizontal();
        selectedY = menuModel.getSelectedVertical();

        selectedPet = ((PetMenu)menuModel.getActiveState()).getSelectedPet();
        selectedFocus = ((PetMenu)menuModel.getActiveState()).getSelectedFocus();
        selectedPriority = ((PetMenu)menuModel.getActiveState()).getSelectedPriority();
        pets = ((PetMenu)menuModel.getActiveState()).getPets();

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10.0f);

        renderPetSprite(gc);
        renderPetName(gc);
        renderPetFocus(gc);
        if(selectedFocus == 0) renderPetPriority(gc);
    }

    private void renderPetSprite(GraphicsContext gc) {
        int startX = Commons.SCREEN_WIDTH / 3;
        int startY = Commons.SCREEN_HEIGHT / 4;
        int width = Commons.SCREEN_WIDTH / 8;
        int height = Commons.SCREEN_HEIGHT / 8;


    }

    private void renderPetName(GraphicsContext gc) {
        int startX = Commons.SCREEN_WIDTH / 3;
        int startY = Commons.SCREEN_HEIGHT / 3;
        int width = Commons.SCREEN_WIDTH / 8;
        int height = Commons.SCREEN_HEIGHT / 8;

        gc.setFont(new Font(40.0f).font("System", FontWeight.BOLD, 40.0f));
        gc.setFill(Color.WHITESMOKE);

        gc.fillText("-", startX + width / 7, startY + 3*height/5);
        //gc.fillText(pets.get(selectedPet).getName(), startX+width / 2, startY +3*height/5);
        gc.fillText("+", startX + 2 * width + 3 * width / 7, startY +3*height/5);

        gc.strokeRect(startX, startY, width / 3, height);
        gc.strokeRect(startX + width / 3, startY, 6 * width / 3, height);
        gc.strokeRect(startX + 7 * width / 3, startY, width / 3, height);

        if(selectedY == 0){

        }
    }

    private void renderPetFocus(GraphicsContext gc) {
        int startX = Commons.SCREEN_WIDTH / 3;
        int startY = Commons.SCREEN_HEIGHT / 2;
        int width = Commons.SCREEN_WIDTH / 8;
        int height = Commons.SCREEN_HEIGHT / 8;

        gc.setFont(new Font(40.0f).font("System", FontWeight.BOLD, 40.0f));
        gc.setFill(Color.WHITESMOKE);

        gc.fillText("Focus:", startX, startY +3*height/5);

        gc.fillText("-", startX + width, startY + 3*height/5);
        gc.fillText(focuses[selectedFocus], startX+ 3 * width / 2, startY +3*height/5);
        gc.fillText("+", startX + 2 * width + 3 * width / 7, startY +3*height/5);

        gc.strokeRect(startX + width, startY, width / 3, height);
        gc.strokeRect(startX +  3 * width / 2, startY, 6 * width / 3, height);
        gc.strokeRect(startX + 7 * width / 3, startY, width / 3, height);

        if(selectedY == 1){
            if(selectedX == 1){
                gc.drawImage(selected, startX + width, startY, width / 3, height);
            }
            if(selectedX == 2){
                gc.drawImage(selected, startX + 7 * width / 3, startY, width / 3, height);
            }
        }
    }

    private void renderPetPriority(GraphicsContext gc) {
        int startX = Commons.SCREEN_WIDTH / 3;
        int startY = 2 * Commons.SCREEN_HEIGHT / 3;
        int width = Commons.SCREEN_WIDTH / 8;
        int height = Commons.SCREEN_HEIGHT / 8;

        gc.setFont(new Font(40.0f).font("System", FontWeight.BOLD, 40.0f));
        gc.setFill(Color.WHITESMOKE);

        gc.fillText("-", startX + width / 7, startY + 3*height/5);
        gc.fillText("Priority:", startX+width / 2, startY +3*height/5);
        gc.fillText(priorities[selectedPriority], startX+ width, startY +3*height/5);
        gc.fillText("+", startX + 2 * width + 3 * width / 7, startY +3*height/5);

        gc.strokeRect(startX, startY, width / 3, height);
        gc.strokeRect(startX + width / 3, startY, 6 * width / 3, height);
        gc.strokeRect(startX + 7 * width / 3, startY, width / 3, height);

        if(selectedY == 2){

        }
    }
}
