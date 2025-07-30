package game;

import game.actors.npcs.HumanoidFigure;
import game.grounds.interactable.*;
import game.items.ToiletPaperRoll;
import game.items.special.Astley;
import game.items.special.Theseus;
import game.items.template.Buyable;
import game.items.weapon.*;
import game.spawners.SuspiciousAstronautSpawner;
import game.utility.Maps;
import game.utility.FancyMessage;
import game.grounds.abilities.*;
import game.items.consumable.*;
import game.spawners.AlienBugSpawner;
import game.spawners.HuntsmanSpiderSpawner;
import game.actors.Player;
import game.grounds.*;
import game.items.scraps.*;
import game.spawners.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import engine.displays.Display;
import engine.items.Item;
import engine.positions.FancyGroundFactory;
import engine.positions.GameMap;
import engine.positions.Location;
import engine.positions.World;

/**
 * The main class to start the game.
 * 
 * @author Darryl Teh
 * @author Yu Bin
 * @author Nah Xin Wen
 * @author Tan Zhong Yi
 * @version 2.0.0
 */
public class Application {
    /**
     * This is the main method which creates an instance of the World
     * class, and starts the Static Factory game.
     * @param args An array of string passed in as command line parameters
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new InheritreeSapling(), new InheritreeMature(),
             new InheritreeSprout(), new InheritreeYoung());

        GameMap gameMap = new GameMap(groundFactory, Maps.POLYMORPHIA);
        world.addGameMap(gameMap);

        GameMap factoryMap = new GameMap(groundFactory, Maps.STATIC_FACTORY);
        world.addGameMap(factoryMap);

        GameMap survivalMoonMap = new GameMap(groundFactory, Maps.REFACTORIO);
        world.addGameMap(survivalMoonMap);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        ArrayList<Buyable> itemList = new ArrayList<Buyable>();
        itemList.add(new DragonSlayerSword());
        itemList.add(new EnergyDrink());
        itemList.add(new ToiletPaperRoll());
        itemList.add(new Theseus());
        itemList.add(new Astley());

        Map<Location, String> locationMap = new HashMap<>();
        locationMap.put(survivalMoonMap.at(15, 5), "Connascence");
        locationMap.put(factoryMap.at(3, 2), "the Static Factory");
        locationMap.put(gameMap.at(15, 5), "Polymorphia");

        ComputerTerminal terminal = new ComputerTerminal(itemList, locationMap);

        gameMap.at(15, 5).setGround(terminal);
        factoryMap.at(3, 2).setGround(terminal);
        survivalMoonMap.at(15, 5).setGround(terminal);

        Item largeBolt = new LargeBolt();
        gameMap.at(4,7).addItem(largeBolt);
        gameMap.at(6,7).addItem(largeBolt);

        Item metalSheet = new MetalSheet();
        gameMap.at(4,9).addItem(metalSheet);
        gameMap.at(6,9).addItem(metalSheet);

        Item metalPipe = new MetalPipe();
        gameMap.at(15, 8).addItem(metalPipe);

        Item pickles = new JarOfPickles();
        gameMap.at(15,9).addItem(pickles);

        Item gold = new PotOfGold();
        gameMap.at(15,10).addItem(gold);


        gameMap.at(15,11).addItem(pickles);


        gameMap.at(15,12).addItem(gold);


        gameMap.at(14,1).setGround(new Crater(new HuntsmanSpiderSpawner()));
        gameMap.at(5,9).setGround(new Crater(new HuntsmanSpiderSpawner()));

        // Alien Bug Craters
        gameMap.at(25,9).setGround(new Crater(new AlienBugSpawner()));

        // Suspicious Astronaut Craters
        gameMap.at(13,13).setGround(new Crater(new SuspiciousAstronautSpawner()));


        //Tree in Polymorphia
        InheritreeSapling sapling1InPo = new InheritreeSapling();
        InheritreeSapling sapling2InPo = new InheritreeSapling();
        InheritreeMature mature1InPo = new InheritreeMature();
        InheritreeMature mature2InPo = new InheritreeMature();

        sapling1InPo.addAbility(1, new ProducingAbility(new SmallFruitSpawner()));
        sapling1InPo.addAbility(0, new GrowingAbility(5,mature1InPo));

        sapling2InPo.addAbility(1, new ProducingAbility(new SmallFruitSpawner()));
        sapling2InPo.addAbility(0, new GrowingAbility(5,mature2InPo));

        mature1InPo.addAbility(1,  new ProducingAbility(new LargeFruitSpawner()));
        mature2InPo.addAbility(1,  new ProducingAbility(new LargeFruitSpawner()));

        gameMap.at(3,3).setGround(sapling1InPo);
        gameMap.at(5,5).setGround(sapling2InPo);

        //Tree in Connascence
        InheritreeSprout sprout = new InheritreeSprout();
        InheritreeSapling sapling = new InheritreeSapling();
        InheritreeYoung young = new InheritreeYoung();
        InheritreeMature mature = new InheritreeMature();

        survivalMoonMap.at(3,3).setGround(sprout);
        sprout.addAbility(0,new GrowingAbility(3, sapling));
        sapling.addAbility(1, new ProducingAbility(new SmallFruitSpawner()));

        sapling.addAbility(0, new GrowingAbility(6, young));
        mature.addAbility(1,  new ProducingAbility(new LargeFruitSpawner()));
        young.addAbility(0, new GrowingAbility(5,mature));

        Player player = new Player("Intern", '@', 4);
        world.addPlayer(player, gameMap.at(15, 6));
//        player.addBalance(1241252);
        player.addBalance(100);
//        player.hurt(3);
        factoryMap.at(3,9).addActor(new HumanoidFigure());

        world.run();
    }
}
