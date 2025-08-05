package game.utility;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.DivineBeastDancingLion;
import game.actors.SuspiciousTrader;
import game.consumables.CrimsonTear;
import game.grounds.*;

import game.grounds.graveyard.Graveyard;
import game.grounds.graveyard.ManFlySpawner;
import game.grounds.graveyard.SpiritSpawner;
import game.pets.HealingUnicorn;
import game.pets.LightningEel;
import game.pets.TankyKong;
import game.shop.ConsumableShop;
import game.shop.Gold;
import game.shop.WeaponArtShop;
import game.shop.WeaponShop;
import game.snapshot.ActorAttributeSnapshot;
import game.snapshot.Snapshot;
import game.weapons.GreatKnife;
import game.weapons.ShortSword;
import game.actors.FurnaceGolem;
import game.actors.Player;
import game.consumables.FlaskofHealing;
import game.consumables.FlaskofRejuvenation;
import game.consumables.ShadowtreeFragments;
import game.weapons.weaponart.Lifesteal;
import game.weapons.weaponart.Memento;
import game.weapons.weaponart.Quickstep;
import game.weapons.weaponart.WeaponArt;


/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Somkiet Phromsuwan, Au Jenq, Aw Shen Yang, Ashraaf Rahman
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new PoisonSwamp());

        List<String> graveSite = Arrays.asList(
                "..........~~~~~~~...~~~~~~~......~...........",
                "~..........~~~~~....~~~~~~...................",
                "~~.........~~~~.....~~~~~~...................",
                "~~~..#####..~~.....~~~~~~~...................",
                "~~~..#___#........~~~~~~~~~..................",
                "~~~..#___#.......~~~~~~.~~~..................",
                "~~~..##_##......~~~~~~.......................",
                "~~~~...........~~~~~~~...........~~..........",
                "~~~~~.........~~~~~~~~.......~~~~~~~.........",
                "~~~~~~.......~~~~~~~~~~.....~~~~~~~~.........");

        GameMap gravesiteMap = new GameMap("Gravesite Plain", groundFactory, graveSite);
        world.addGameMap(gravesiteMap);

        List<String> beluratTower = Arrays.asList(
                "###########........................##########",
                "#____#____#......................._____#____#",
                "#____#_.._#.#...~~~.......~~~....#____#____##",
                "###_~~____###...~~~..~~~..~~~...####______###",
                "###...____###..~~~~..~~~~..~~~...######_____#",
                "##~~###..####..~~~...~~~.....~~~..####..#####",
                "##__.....####..~~~.~~~~~..~~~....#####____###",
                "###..##..##.#..~~..~~~~~..~~~~....####~..####",
                "#....__..__.#..~~..~~~~~~..~~....__~~~~######",
                "###########....................##############");

        GameMap beluratTowerMap = new GameMap("Belurat, Tower Settlement", groundFactory, beluratTower);
        world.addGameMap(beluratTowerMap);

        List<String> beluratSewers = Arrays.asList(
                "##++++++#####++++++++~~~~~++++",
                "##+++++++###+++++++++~~~~~++++",
                "##++++++++++++++++++~~~~~~~++~",
                "###+++++++++++++++.~~~~~~~~.~~",
                "~~~~~.+++++~~~++++~~~~~~~~~..~",
                "~~~~~~~~~~~~~~~++++~~~~+++~...",
                "~~~~+~~~~~~~~~~+++++~~~~~~~###",
                "+~~~~++####~~~~~++++##.~++~###",
                "++~~+++#####~~~~~++###++~~~###",
                "+~~++++######~~~~++###++~~~###"
        );

        GameMap beluratSewersMap = new GameMap("Belurat Sewers", groundFactory, beluratSewers);
        world.addGameMap(beluratSewersMap);

        // Assignment 3: New map is added
        List<String> stagefront = Arrays.asList(
                "#################",
                "#~~~..........~~#",
                "#~~~...........~#",
                "#~~.............#",
                "#............~~~#",
                "#..........~~~~~#",
                "#######...#######"
        );

        GameMap stagefrontMap = new GameMap("Stagefront", groundFactory, stagefront);
        world.addGameMap(stagefrontMap);

        // Gate connecting to Belurat Tower and Sewers map
        List<Location> BeluratTowerSewerGate = new ArrayList<>();
        BeluratTowerSewerGate.add(beluratTowerMap.at(3, 6));
        BeluratTowerSewerGate.add(beluratSewersMap.at(10, 6));

        // Gate connecting to Gravesite map
        List<Location> GravesiteGate = new ArrayList<>();
        GravesiteGate.add(gravesiteMap.at(29, 6));

        // Assignment 3: Gate connecting to Stagefront map
        List<Location> StageFrontLocations = new ArrayList<>();
        StageFrontLocations.add(stagefrontMap.at(5,3));

        // Set the ground for each map location with the new Gate instance
        gravesiteMap.at(29, 6).setGround(new Gate(BeluratTowerSewerGate)); //Gravesite to Belurat Tower and Sewer
        beluratTowerMap.at(3, 6).setGround(new Gate(GravesiteGate)); //Belurat Tower South Gate to Gravesite
        beluratSewersMap.at(10, 6).setGround(new Gate(GravesiteGate)); //Belurat Sewer to Gravesite

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        Player player = new Player("Tarnished", '@', 150, 100, 5);
        world.addPlayer(player, gravesiteMap.at(7,5));

        // TEST REQUIREMENT 1
        beluratTowerMap.at(23, 0).setGround(new Gate(StageFrontLocations)); //Belurat Tower North Gate to Stagefront
        stagefrontMap.at(9,5).addActor(new DivineBeastDancingLion(beluratTowerMap));

        // TEST REQUIREMENT 2
        gravesiteMap.at(8,4).addActor(new SuspiciousTrader());

        // TEST REQUIREMENT 3
        Snapshot ActorSnapshot = new ActorAttributeSnapshot();
        List<Snapshot> snapshots = new ArrayList<>();
        snapshots.add(ActorSnapshot);

        WeaponArt memento = new Memento(snapshots);

        WeaponArt lifesteal = new Lifesteal();
        WeaponArt quickstep = new Quickstep();

        gravesiteMap.at(31,3).addItem(new GreatKnife(memento));

        // TEST REQUIREMENT 4

        gravesiteMap.at(5,1).addItem((new Gold(5)));
        gravesiteMap.at(12,1).addItem((new Gold(5)));
        gravesiteMap.at(39,2).addItem((new Gold(15)));
        gravesiteMap.at(29,3).addItem((new Gold(5)));
        gravesiteMap.at(1,4).addItem((new Gold(10)));
        gravesiteMap.at(27,6).addItem((new Gold(5)));
        gravesiteMap.at(3,7).addItem((new Gold(10)));
        gravesiteMap.at(15,9).addItem((new Gold(20)));
        gravesiteMap.at(30,9).addItem((new Gold(10)));

        ConsumableShop consumableShop = new ConsumableShop('1', "Consumable Shop");
        consumableShop.addConsumable(new FlaskofHealing());
        consumableShop.addConsumable(new FlaskofRejuvenation());
        consumableShop.addConsumable(new ShadowtreeFragments());
        consumableShop.addConsumable(new CrimsonTear());
        gravesiteMap.at(42,0).setGround(consumableShop);

        WeaponShop weaponShop = new WeaponShop('2', "Weapon Shop");
        weaponShop.addWeapon(new ShortSword(null));
        weaponShop.addWeapon(new GreatKnife(null));
        gravesiteMap.at(43, 0).setGround(weaponShop);

        WeaponArtShop weaponArtShop = new WeaponArtShop('3', "Weapon Art Shop");
        weaponArtShop.addWeaponWithWeaponArt(new ShortSword(lifesteal));
        weaponArtShop.addWeaponWithWeaponArt(new ShortSword(quickstep));
        weaponArtShop.addWeaponWithWeaponArt(new GreatKnife(lifesteal));
        weaponArtShop.addWeaponWithWeaponArt(new GreatKnife(quickstep));
        gravesiteMap.at(44, 0).setGround(weaponArtShop);

        gravesiteMap.at(0,0).addActor(new LightningEel());
        gravesiteMap.at(1,0).addActor(new HealingUnicorn());
        gravesiteMap.at(2,0).addActor(new TankyKong());

        ////////////////////////////////////////////////////////////

        gravesiteMap.at(42, 4).addActor(new FurnaceGolem());
        gravesiteMap.at(5,8).addItem(new GreatKnife(lifesteal));
        gravesiteMap.at(7,8).addItem(new ShortSword(quickstep));
        gravesiteMap.at(5,9).addItem(new GreatKnife(quickstep));
        gravesiteMap.at(6,5).addItem(new FlaskofHealing());
        gravesiteMap.at(8,5).addItem(new FlaskofRejuvenation());
        gravesiteMap.at(8,8).addItem(new ShadowtreeFragments());
        gravesiteMap.at(15,9).addItem(new ShadowtreeFragments());
        gravesiteMap.at(19,6).addItem(new ShadowtreeFragments());
        gravesiteMap.at(6,7).addItem(new ShadowtreeFragments());
        gravesiteMap.at(10,4).addItem(new ShadowtreeFragments());

        //////////////////////////////////////////////////////////////
        // Code from Assignment 2 to randomly set ground to graveyard, can be comment until // below to improve readability and testing
        Random random = new Random();
        int graveyardsPlaced = 0;
        while (graveyardsPlaced < 3) {
            int TowerX = random.nextInt(beluratTowerMap.getXRange().min(), beluratTowerMap.getXRange().max() + 1);
            int TowerY = random.nextInt(beluratTowerMap.getYRange().min(), beluratTowerMap.getYRange().max() + 1);
            int SewerX = random.nextInt(beluratSewersMap.getXRange().min(), beluratSewersMap.getXRange().max() + 1);
            int SewerY = random.nextInt(beluratSewersMap.getYRange().min(), beluratSewersMap.getYRange().max() + 1);
            beluratTowerMap.at(TowerX, TowerY).setGround(new Graveyard(new SpiritSpawner()));
            beluratSewersMap.at(SewerX, SewerY).setGround(new Graveyard(new ManFlySpawner()));
            graveyardsPlaced++;
        }
        // Comment until the line above

        world.run();
    }
}
