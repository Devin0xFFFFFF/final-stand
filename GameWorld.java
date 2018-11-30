import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld extends World
{
    private Hero hero;
    private Barrier barrier;
    private GreenfootSound soundtrack = new GreenfootSound("soundtrack.wav");
    private boolean playMusic;
    private int musicTimer;

    private int[] commonSpawnRate;
    private int[] commonSpawnTimer;
    private int[] commonSpawnLimit;
    private int[] commonCurrentSpawned;
    private int[] bossSpawned;

    public int caliber;
    private int level;
    private int pauseTimer;
    private int kills;
    public int points;
    private int baseValue;
    public int pts;
    private boolean send;

    private int bossLevel;
    private int boss;

    private int[] explosivesCosts;
    private int[] hireCosts;
    private int[] callCosts;
    private int[] perkCosts;

    private Display levelDisplay;
    private Display killsDisplay;
    private Display pointsDisplay;
    private Display ammoDisplay;
    private Display weaponDisplay;
    private Display barrierDisplay;
    private Display selectionDisplay;

    private Icon explosivesIcon;
    private Icon hireIcon;
    private Icon callIcon;
    private Icon perkIcon;

    private Selector selector;
    private Selection selection;

    private int icon;
    private int boundary;
    private boolean strike;
    private int strikeTimer;
    private int definedStrike;
    private int target;
    private int strikeX;
    private int location;
    private int strikeShots;

    public GameWorld()
    {    
        super(800, 600, 1, false); 

        setPaintOrder(Selector.class,Flash.class, Hero.class);

        caliber = 0;
        level = 1;
        pauseTimer = 0;
        kills = 0;
        points = 0;
        baseValue = 100;
        pts = 0;
        send = false;
        playMusic = false;
        musicTimer = 50;
        icon = 0;
        boundary = 1;
        strike = false;
        strikeTimer = 0;
        definedStrike = 0;
        target = 0;
        location = 0;
        strikeShots = 0;

        bossLevel = 15;
        boss = 0;

        initializeArrays();
        addAllObjects();
    }

    private void initializeArrays()
    {
        commonSpawnRate = new int[5];
        commonSpawnTimer = new int[5];
        commonSpawnLimit = new int[5];
        commonCurrentSpawned = new int[5];
        bossSpawned = new int[5];
        initializeCSR();
        commonSpawnLimit[0] = 10;

        explosivesCosts = new int[3];
        hireCosts = new int[3];
        callCosts = new int[3];
        perkCosts = new int[3];
    }

    private void initializeCSR()
    {
        for(int i=0;i<commonSpawnRate.length;i++)
        {
            commonSpawnRate[i] = Greenfoot.getRandomNumber(200)+100;
        }
    }

    private void addAllObjects()
    {
        hero = new Hero();
        addObject(hero, 700,460);

        barrier = new Barrier();
        addObject(barrier, 550,450);

        addDisplays();
        addIcons();
    }

    private void addDisplays()
    {
        levelDisplay = new Display("Level: 1");
        addObject(levelDisplay, 150, 20);

        killsDisplay = new Display("Kills: 0");
        addObject(killsDisplay, 250, 20);

        pointsDisplay = new Display("Points: 0");
        addObject(pointsDisplay, 350, 20);

        ammoDisplay = new Display("Ammo: 15");
        addObject(ammoDisplay, 500, 20);

        weaponDisplay = new Display("1: Pistol  DMG: 1  Upgrade Cost: 1000");
        addObject(weaponDisplay, 600, 20);

        barrierDisplay = new Display("Barrier Strength: 5000");
        addObject(barrierDisplay, 150, 50);

        selectionDisplay = new Display("Current Selection:");
        addObject(selectionDisplay, 715, 65);
    }

    private void addIcons()
    {
        explosivesIcon = new Icon(1);
        addObject(explosivesIcon, 230, 70);

        hireIcon = new Icon(2);
        addObject(hireIcon, 330, 70);

        callIcon = new Icon(3);
        addObject(callIcon, 430, 70);

        perkIcon = new Icon(4);
        addObject(perkIcon, 530, 70);

        selector = new Selector();
        addObject(selector, -100, 300);

        selection = new Selection();
        addObject(selection, 720, 70);
    }

    public void act()
    {
        soundtrack();
        spawning();
        displays();
        icons();
    }

    private void soundtrack()
    {
        if(playMusic)
        {
            soundtrack.play();
        }
        else
        {
            soundtrack.pause();
        }

        musicTimer++;
        if(musicTimer >= 50 && Greenfoot.isKeyDown("m"))
        {
            if(playMusic == true)
            {
                playMusic = false;
            }
            else
            {
                playMusic = true;
            }
            musicTimer = 0;
        }
    }

    public int getCaliber()
    {
        return caliber;
    }

    private void spawning()
    {
        if(!isSpawnCompleted())
        {
            spawn(0);
            if(level >= 10)
            {
                spawn(1);
            }
            if(level >= 20)
            {
                spawn(2);
            }
            if(level >= 30)
            {
                spawn(3);
            }
            if(level >= 40)
            {
                spawn(4);
            }
        }
        if(isSpawnCompleted())
        {
            updateSpawning();
        }
    }

    private void spawn(int enemy)
    {
        commonSpawnTimer[enemy]++;
        if(commonSpawnTimer[enemy] >= commonSpawnRate[enemy])
        {
            int ranY = Greenfoot.getRandomNumber(300)+300;
            addObject(new Enemy(enemy),-100,ranY);
            commonCurrentSpawned[enemy]++;
            commonSpawnRate[enemy] = Greenfoot.getRandomNumber(200)+100;
            commonSpawnTimer[enemy] = 0;
        }
    }

    private boolean isSpawnCompleted()
    {
        for(int i=0;i<commonSpawnLimit.length;i++)
        {
            if(commonCurrentSpawned[i] > commonSpawnLimit[i])
            {
                return true;
            }
        }
        return false;
    }

    private void updateSpawning()
    {
        pauseTimer++;
        if(pauseTimer >= 1000)
        {
            commonSpawnLimit[0] += 10;
            commonCurrentSpawned[0] = 0;
            if(level >= 10)
            {
                commonSpawnLimit[1] += 10;
                commonCurrentSpawned[1] = 0;
            }
            if(level >= 20)
            {
                commonSpawnLimit[2] += 10;
                commonCurrentSpawned[2] = 0;
            }
            if(level >= 30)
            {
                commonSpawnLimit[3] += 10;
                commonCurrentSpawned[3] = 0;
            }
            if(level >= 40)
            {
                commonSpawnLimit[4] += 10;
                commonCurrentSpawned[4] = 0;
            }
            bossCheck();
            level++;
            increasePoints(baseValue);
            baseValue += 100;
            pauseTimer = 0;
        }
    }

    private void bossCheck()
    {
        if(boss < 4)
        {
            if(level == bossLevel)
            {
                if(bossSpawned[boss] == 0)
                {
                    addObject(new Boss(boss),-100,getHeight()/2);
                    bossSpawned[boss] = 1;
                    boss++;
                    bossLevel += 10;
                }
            }
        }
        if(level == 50)
        {
            if(bossSpawned[4] == 0)
            {
                addObject(new Boss(4),-100,getHeight()/2);
                bossSpawned[4] = 1;
            }
        }
    }

    public void increaseKills()
    {
        kills++;
    }

    public void increasePoints(int amt)
    {
        hero.increasePoints(amt);
    }

    private void displays()
    {
        levelDisplay.setValue("Level: " + level);
        killsDisplay.setValue("Kills: " + kills);
        pointsDisplay.setValue("Points: " + hero.getPoints());
        if(hero.getAmmo() != 0)
        {
            ammoDisplay.setValue("Ammo: " + hero.getAmmo());
        }
        else
        {
            ammoDisplay.setValue("RELOADING");
        }
        weaponDisplay.setValue(hero.getWeaponInfo());
        barrierDisplay.setValue("Barrier Strength: " + barrier.getStrength());
    }

    private void icons()
    {
        changeSelection();
        addSelection();
        strikes();
    }

    private void changeSelection()
    {
        if(Greenfoot.mouseClicked(explosivesIcon))
        {
            explosivesIcon.upImage();
            selection.setThisImage(explosivesIcon.getThisImage());
            boundary = 1;
            icon = explosivesIcon.image;
        }
        if(Greenfoot.mouseClicked(hireIcon))
        {
            hireIcon.upImage();
            selection.setThisImage(hireIcon.getThisImage());
            boundary = 2;
            icon = hireIcon.image;
        }
        if(Greenfoot.mouseClicked(callIcon))
        {
            callIcon.upImage();
            selection.setThisImage(callIcon.getThisImage());
            boundary = 3;
            icon = callIcon.image;
        }
        if(Greenfoot.mouseClicked(perkIcon))
        {
            perkIcon.upImage();
            selection.setThisImage(perkIcon.getThisImage());
            boundary = 4;
            icon = perkIcon.image;
        }   
    }

    private void addSelection()
    {
        if(Greenfoot.mouseClicked(this)) 
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            selector.selected = true;
        }

        if(selector.boundaries(boundary))
        {
            if(boundary == 1 && hero.getPoints() > hero.explosiveCosts[icon+1])
            {
                addObject(new Explosive(icon),selector.getX(),selector.getY());
                hero.decreasePoints(hero.explosiveCosts[icon+1]);
            }
            else if(boundary == 2 && hero.getPoints() > hero.unitCosts[icon+1])
            {
                addObject(new Unit(icon),selector.getX(),selector.getY());
                hero.decreasePoints(hero.unitCosts[icon+1]);
            }
            else if(boundary == 3 && hero.getPoints() > hero.strikeCosts[icon+1] && !strike)
            {
                strike = true;
                target = icon;
                strikeX = 500;
                strikeShots = Greenfoot.getRandomNumber(10)+10;
                definedStrike = 100;
                hero.decreasePoints(hero.strikeCosts[icon+1]);
            }
            else if(boundary == 4 && hero.getPoints() > hero.perkCosts[icon+1])
            {
                initiatePerks(icon);
                hero.decreasePoints(hero.perkCosts[icon+1]);
            }
        }
    }

    private void strikes()
    {
        if(strike)
        {
            strikeTimer++;
            if(target == 0)
            {
                if(strikeTimer >= definedStrike)
                {
                    addObject(new Strike(icon,location),strikeX,-100);
                    strikeX -= 50;
                    strikeTimer = 0;
                }
                if(strikeX <= 0)
                {
                    strikeTimer = 0;
                    strike = false;
                }
            }
            if(target == 1)
            {
                if(strikeTimer >= definedStrike && strikeShots > 0)
                {
                    addObject(new Strike(icon,location),900,getHeight()/2);
                    strikeShots--;
                    strikeTimer = 0;
                }
                if(strikeShots <= 0)
                {
                    strikeTimer = 0;
                    strike = false;
                }
            }
            if(target == 2)
            {
                if(strikeTimer >= definedStrike)
                {
                    addObject(new Strike(icon,location),getWidth()/2,-100);
                    strikeTimer = 0;
                    strike = false;
                }
            }
        }
    }

    public void setCurrentLocation(int y)
    {
        location = y;
    }

    private void initiatePerks(int perk)
    {
        if(perk == 0)
        {
            hero.activateSpeedUp();
        }
        else if(perk == 1)
        {
            hero.activateQuickShot();
        }
        else if(perk == 2)
        {
            hero.activateX2Mags();
        }
    }
}
