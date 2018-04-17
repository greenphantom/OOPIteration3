package ControllerTests;

import Controller.GameLoader;
import Model.AreaEffect.AreaEffect;
import Model.AreaEffect.InfiniteAreaEffect;
import Model.AreaEffect.OneShotAreaEffect;
import Model.InfluenceEffect.AngularInfluenceEffect;
import Model.InfluenceEffect.InfluenceEffect;
import Model.InfluenceEffect.LinearInfluenceEffect;
import Model.InfluenceEffect.RadialInfluenceEffect;
import Model.Item.InteractiveItem;
import Model.Item.Item;
import Model.Item.OneShotItem;
import Model.Item.TakeableItem.ArmorItem;
import Model.Item.TakeableItem.ConsumableItem;
import Model.Item.TakeableItem.RingItem;
import Model.Level.Level;
import Model.Level.Obstacle;
import Model.Level.Terrain;
import javafx.geometry.Point3D;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class GameLoaderTests {

    private GameLoader gameLoader;
    private String fileName;

    @Before
    public void init() {
        gameLoader = new GameLoader();
        fileName = "SAVEFILE.xml";
    }

    @Test
    public void testTerrainsLoad() throws ParserConfigurationException, SAXException, IOException {
        String fileName = "SAVEFILE.xml";
        gameLoader.loadGame(fileName);
        Level testedLevel = gameLoader.getCurrentLevel();
        Map<Point3D, Terrain> testedMap = testedLevel.getTerrainMap();

        assertTrue(testedMap.containsKey(new Point3D(0,0,0)));

        assertTrue(testedMap.containsValue(Terrain.GRASS));
    }

    @Test
    public void testMulitpleLevelsLoad() throws ParserConfigurationException, SAXException, IOException {
        gameLoader.loadGame(fileName);
        assertTrue(gameLoader.getWorld().size() != 1);
    }

    @Test
    public void testAreaEffectsLoad() throws ParserConfigurationException, SAXException, IOException {
        gameLoader.loadGame(fileName);
        Level currentLevel = gameLoader.getCurrentLevel();
        Map<Point3D, AreaEffect> testedAreaEffects = currentLevel.getAreaEffectLocations();

        assertTrue(testedAreaEffects.size() == 2);
        assertTrue(testedAreaEffects.get(new Point3D(0,0,0)) instanceof OneShotAreaEffect);
        assertTrue(testedAreaEffects.get(new Point3D(0,0,1)) instanceof InfiniteAreaEffect);
    }

    @Test
    public void testInfluencesLoad() throws ParserConfigurationException, SAXException, IOException {
        gameLoader.loadGame(fileName);
        Level currentLevel = gameLoader.getCurrentLevel();
        Map<Point3D, InfluenceEffect> testedInfluences = currentLevel.getInfluencesMap();

        assertTrue(testedInfluences.size() == 3);
        assertTrue(testedInfluences.get(new Point3D(0,0,0)) instanceof AngularInfluenceEffect);
        assertTrue(testedInfluences.get(new Point3D(0,0,1)) instanceof LinearInfluenceEffect);
        assertTrue(testedInfluences.get(new Point3D(0,0,2)) instanceof RadialInfluenceEffect);
    }

    @Test
    public void testObstaclesLoad() throws ParserConfigurationException, SAXException, IOException {
        gameLoader.loadGame(fileName);
        Level currentLevel = gameLoader.getCurrentLevel();
        Map<Point3D, Obstacle> testedObstacles = currentLevel.getObstacleLocations();

        assertTrue(testedObstacles.size() == 1);
        assertTrue(testedObstacles.get(new Point3D(0,0,0)) instanceof Obstacle);
    }

    @Test
    public void testItemsLoad() throws ParserConfigurationException, SAXException, IOException {
        gameLoader.loadGame(fileName);
        Level level = gameLoader.getCurrentLevel();
        Map<Point3D, Item> testedItems = level.getItemLocations();

        assertTrue(testedItems.size() == 5);
        assertTrue(testedItems.get(new Point3D(0,0,0)) instanceof RingItem);
        assertTrue(testedItems.get(new Point3D(0,0,1)) instanceof ArmorItem);
        assertTrue(testedItems.get(new Point3D(0,0,2)) instanceof ConsumableItem);
        assertTrue(testedItems.get(new Point3D(0,0,3)) instanceof OneShotItem);
        assertTrue(testedItems.get(new Point3D(0,0,4)) instanceof InteractiveItem);
    }
}
