package View.LevelView;

import Configs.Commons;
import Model.Entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HUDStatsView {
    private Entity player;
    private int statBarWidth;
    private int statBarHeight;
    public HUDStatsView(Entity player) {
        this.player = player;
        statBarWidth = 200;
        statBarHeight = 20;
    }

    public void render(GraphicsContext gc) {


        renderHealthBar(gc);
        renderExperienceBar(gc);
        renderManaBar(gc);

        renderPlayerLevel(gc);
    }

    public void renderHealthBar(GraphicsContext gc) {
        float currentHealth = (float) player.getCurrentHealth();
        float maxHealth = (float) player.getMaxHealth();
        float healthPercentage = currentHealth/maxHealth;

        renderStatBar(Commons.SCREEN_WIDTH-statBarWidth, 0, statBarWidth, statBarHeight, healthPercentage, Color.RED, gc);

    }

    public void renderManaBar(GraphicsContext gc) {
        float currentMana = (float) player.getManaPoints();
        float maxMana = (float) player.getMaxMana();
        float manaPercentage = currentMana/maxMana;

        renderStatBar(Commons.SCREEN_WIDTH-statBarWidth, statBarHeight, statBarWidth, statBarHeight, manaPercentage, Color.BLUE, gc);
    }

    public void renderExperienceBar(GraphicsContext gc) {
        float currentExperience = (float) player.getExperience();
        float experienceAtLevelUp = currentExperience + (float) player.getExperienceToNextLevel();
        float experiencePercentage = currentExperience/experienceAtLevelUp;

        renderStatBar(Commons.SCREEN_WIDTH-statBarWidth, statBarHeight*2, statBarWidth, statBarHeight/2, experiencePercentage, Color.GOLD, gc);
    }

    public void renderPlayerLevel(GraphicsContext gc) {
        int level = player.getLevel();

        gc.setFill(Color.GOLDENROD);
        gc.fillRect(Commons.SCREEN_WIDTH-statBarWidth-(statBarHeight*2.5), 0, (statBarHeight*2.5), (statBarHeight*2.5));


        gc.setFill(Color.BLACK);
        gc.setFont(Font.font ("Verdana", FontWeight.BOLD, 30));

        gc.fillText(Integer.toString(level), Commons.SCREEN_WIDTH-statBarWidth-(statBarHeight*2.5)+15, 35);
    }



    public void renderStatBar(int x, int y, int width, int height, float percentFill, Color fillColor, GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(x, y, width, height);

        gc.setFill(fillColor);
        gc.fillRect(x, y, width*percentFill, height);
    }


}
