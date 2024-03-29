package Controller.Factories.EntityFactories;

import Controller.Factories.SkillsFactory;
import Model.Entity.Entity;

import Model.Entity.EntityAttributes.SightRadius;
import View.LevelView.EntityView.SmasherView;
import javafx.geometry.Point3D;


public class SmasherFactory extends EntityFactory {

    public SmasherFactory(SkillsFactory skillsFactory) {
        super(skillsFactory);
    }

    @Override
    public Entity buildEntity() {
        Entity smasher = new Entity();
        smasher.setSpeed(0750000000L);
        smasher.setSightRadius(new SightRadius(3));

        smasher.addWeaponSkills(getSkillsFactory().getOneHandedSkill(),
                getSkillsFactory().getTwoHandedSkill(),
                getSkillsFactory().getBrawlerSkill()
        );

        smasher.addNonWeaponSkills(getSkillsFactory().getBargainSkill(),
                getSkillsFactory().getObserveSkill(),
                getSkillsFactory().getBindWounds()
        );

        smasher.setName("smasher");

        return smasher;
    }


    public void buildEntitySprite(Entity entity) {
        SmasherView smasherView = new SmasherView(entity, new Point3D(0,0,0));
    }

}
