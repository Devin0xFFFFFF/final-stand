import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Actor
{
    private int[] weapons;
    private int[] weaponsCost;
    private String[] weaponName;

    private int[] currentAmmo;
    private int[] maxAmmo;

    private int[] weaponROF;
    private int[] bulletType;

    public int[] explosiveCosts;
    public int[] unitCosts;
    public int[] strikeCosts;
    public int[] perkCosts;

    private int points;
    private int life;
    private int moveSpeed;

    private int currentWeapon;
    private boolean hasUpgraded;

    private int delay;
    private int loaded;
    private boolean armed;
    private int reloadTimer;
    private int reloadTime;

    private boolean arrowControls;
    private int controlTimer;

    private int[] perkTimer;
    private int perkTime;
    private boolean[] perks;
    private int ammoHolder;
    private int tempWeapon;

    public Hero()
    {
        initializeArrays();

        points = 500000;
        life = 100;
        moveSpeed = 5;

        weapons[1] = 1;
        weapons[0] = 1;
        currentWeapon = 1;
        hasUpgraded = false;

        delay = 30;
        loaded = 0;
        armed = true;
        reloadTimer = 0;
        reloadTime = 50;

        arrowControls = true;
        controlTimer = 30;

        perkTime = 1000;
        ammoHolder = 0;
        tempWeapon = 0;

        setAmmo();
        setROF();
        setCosts();
        weaponName = new String[]{"FlameThrower","Pistol","SMG","Shotgun","Sniper",
            "Assault Rifle","Rocket Launcher","Heavy MG","Minigun","Tesla Cannon"};
    }

    private void initializeArrays()
    {
        weapons = new int[10];
        weaponsCost = new int[10];
        currentAmmo = new int[10];
        maxAmmo = new int[10];
        weaponROF = new int[10];
        bulletType = new int[10];
        explosiveCosts = new int[4];
        unitCosts = new int[4];
        strikeCosts = new int[4];
        perkCosts = new int[4];
        perks = new boolean[3];
        perkTimer = new int[3];
    }

    private void setAmmo()
    {
        maxAmmo[0] = 100;
        maxAmmo[1] = 15;
        maxAmmo[2] = 30;
        maxAmmo[3] = 6;
        maxAmmo[4] = 10;
        maxAmmo[5] = 50;
        maxAmmo[6] = 10;
        maxAmmo[7] = 100;
        maxAmmo[8] = 300;
        maxAmmo[9] = 100;

        for(int i=0;i<maxAmmo.length;i++)
        {
            currentAmmo[i] = maxAmmo[i];
        }
    }

    private void setCosts()
    {
        setInitialWeaponCosts();
        setInitialPowerUpCosts();
    }

    private void setInitialWeaponCosts()
    {
        weaponsCost[0] = 0;
        weaponsCost[1] = 1000;
        weaponsCost[2] = 250;
        weaponsCost[3] = 500;
        weaponsCost[4] = 1000;
        weaponsCost[5] = 2000;
        weaponsCost[6] = 3000;
        weaponsCost[7] = 5000;
        weaponsCost[8] = 10000;
        weaponsCost[9] = 300000;
    }

    private void setInitialPowerUpCosts()
    {
        explosiveCosts[1] = 50;
        explosiveCosts[2] = 150;
        explosiveCosts[3] = 300;

        unitCosts[1] = 500;
        unitCosts[2] = 1500;
        unitCosts[3] = 3000;

        strikeCosts[1] = 3000;
        strikeCosts[2] = 5000;
        strikeCosts[3] = 10000;

        perkCosts[1] = 250;
        perkCosts[2] = 500;
        perkCosts[3] = 1000;
    }

    private void setROF()
    {
        weaponROF[0] = 8;
        weaponROF[1] = 0;
        weaponROF[2] = 10;
        weaponROF[3] = 80;
        weaponROF[4] = 80;
        weaponROF[5] = 10;
        weaponROF[6] = 30;
        weaponROF[7] = 6;
        weaponROF[8] = 4;
        weaponROF[9] = 10;
    }

    public void act() 
    {
        movement();
        weapons();
        damage();
        perk();
    }    

    private void movement()
    {
        keys();
    }

    private void keys()
    {
        controlTimer++;
        if(controlTimer >= 50 && Greenfoot.isKeyDown("c"))
        {
            if(arrowControls)
            {
                arrowControls = false;
            }
            else
            {
                arrowControls = true;
            }
            controlTimer = 0;
        }

        if(arrowControls)
        {
            if(!atTop())
            {
                if(Greenfoot.isKeyDown("up"))
                {
                    setLocation(getX(),getY()-moveSpeed);
                }
            }
            if(!atBottom())
            {
                if(Greenfoot.isKeyDown("down"))
                {
                    setLocation(getX(),getY()+moveSpeed);
                }
            }
            if(!atRight())
            {
                if(Greenfoot.isKeyDown("right"))
                {
                    setLocation(getX()+moveSpeed,getY());
                }
            }
            if(!atLeft())
            {
                if(Greenfoot.isKeyDown("left"))
                {
                    setLocation(getX()-moveSpeed,getY());
                }
            }
        }
        else
        {
            if(!atTop())
            {
                if(Greenfoot.isKeyDown("w"))
                {
                    setLocation(getX(),getY()-moveSpeed);
                }
            }
            if(!atBottom())
            {
                if(Greenfoot.isKeyDown("s"))
                {
                    setLocation(getX(),getY()+moveSpeed);
                }
            }
            if(!atRight())
            {
                if(Greenfoot.isKeyDown("d"))
                {
                    setLocation(getX()+moveSpeed,getY());
                }
            }
            if(!atLeft())
            {
                if(Greenfoot.isKeyDown("a"))
                {
                    setLocation(getX()-moveSpeed,getY());
                }
            }
        }
    }

    private boolean atTop()
    {
        int blue = getWorld().getColorAt(getX(),getY()).getBlue();
        int green = getWorld().getColorAt(getX(),getY()).getGreen();
        if(blue > green)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean atBottom()
    {
        if(getY() > getWorld().getHeight()-10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean atRight()
    {
        if(getX() > getWorld().getWidth()-20)
        {
            return true;
        }
        return false;
    }

    private boolean atLeft()
    {
        Actor atBarrier = getOneIntersectingObject(Barrier.class);
        if(getX() < 400 || atBarrier != null)
        {
            return true;
        }
        return false;
    }

    private void damage()
    {
        Actor enemy = getOneIntersectingObject(Enemy.class);
        Actor boss = getOneIntersectingObject(Boss.class);
        Actor shot = getOneIntersectingObject(EnemyShot.class);
        if(enemy != null)
        {
            life--;
        }
        else if(boss != null)
        {
            life--;
        }
        else if(shot != null)
        {
            life--;
            getWorld().removeObject(shot);
        }

        if(life <= 0)
        {
            endGame();
        }
    }

    public void setPoints(int value)
    {
        points += value;
    }

    private void weapons()
    {
        selection();
        upgrades();
        shooting();
        returnCaliber();
    }

    private void selection()
    {
        if(Greenfoot.isKeyDown("1"))
        {
            if(weapons[1] > 0)
            {
                switchWeapon(1);
            }
            else if(weapons[1] == 0)
            {
                buyWeapon(1);
            }
        }
        else if(Greenfoot.isKeyDown("2"))
        {
            if(weapons[2] > 0)
            {
                switchWeapon(2);
            }
            else if(weapons[2] == 0)
            {
                buyWeapon(2);
            }
        }
        else if(Greenfoot.isKeyDown("3"))
        {
            if(weapons[3] > 0)
            {
                switchWeapon(3);
            }
            else if(weapons[3] == 0)
            {
                buyWeapon(3);
            }
        }
        else if(Greenfoot.isKeyDown("4"))
        {
            if(weapons[4] > 0)
            {
                switchWeapon(4);
            }
            else if(weapons[4] == 0)
            {
                buyWeapon(4);
            }
        }
        else if(Greenfoot.isKeyDown("5"))
        {
            if(weapons[5] > 0)
            {
                switchWeapon(5);
            }
            else if(weapons[5] == 0)
            {
                buyWeapon(5);
            }
        }
        else if(Greenfoot.isKeyDown("6"))
        {
            if(weapons[6] > 0)
            {
                switchWeapon(6);
            }
            else if(weapons[6] == 0)
            {
                buyWeapon(6);
            }
        }
        else if(Greenfoot.isKeyDown("7"))
        {
            if(weapons[7] > 0)
            {
                switchWeapon(7);
            }
            else if(weapons[7] == 0)
            {
                buyWeapon(7);
            }
        }
        else if(Greenfoot.isKeyDown("8"))
        {
            if(weapons[8] > 0)
            {
                switchWeapon(8);
            }
            else if(weapons[8] == 0)
            {
                buyWeapon(8);
            }
        }
        else if(Greenfoot.isKeyDown("9"))
        {
            if(weapons[9] > 0)
            {
                switchWeapon(9);
            }
            else if(weapons[9] == 0)
            {
                buyWeapon(9);
            }
        }
        else if(Greenfoot.isKeyDown("0"))
        {
            if(weapons[0] > 0)
            {
                switchWeapon(0);
            }
            else if(weapons[0] == 0)
            {
                buyWeapon(0);
            }
        }
    }

    private void switchWeapon(int weapon)
    {
        if(weapons[weapon] > 1)
        {
            checkUpgrade(weapon);
        }
        else
        {
            setImage("H"+weapon+".png");
        }
        currentWeapon = weapon;
    }

    private void checkUpgrade(int weapon)
    {
        if(weapons[weapon] == 2)
        {
            setImage("H"+weapon+"U.png");
        }
        else if(weapons[weapon] == 3)
        {
            setImage("H"+weapon+"G.png");
        }
    }

    private void buyWeapon(int weapon)
    {
        if(points >= weaponsCost[weapon])
        {
            if(weapons[weapon] == 0)
            {
                weapons[weapon] += 1;
                switchWeapon(weapon);
                points -= weaponsCost[weapon];
                weaponsCost[weapon] = weaponsCost[weapon]*2;
            }
        }
    }

    private void upgrades()
    {
        delay++;
        if(delay >= 30 && Greenfoot.isKeyDown("u"))
        {
            if(currentWeapon != 0 && currentWeapon != 9)
            {
                if(points >= weaponsCost[currentWeapon] && weapons[currentWeapon] < 3)
                {
                    if(weapons[currentWeapon] != 0)
                    {
                        points -= weaponsCost[currentWeapon];
                        upgradeWeapon(currentWeapon);
                        delay = 0;
                    }
                }
            }
        }
    }

    private void upgradeWeapon(int weapon)
    {
        if(weapons[weapon] == 1)
        {
            weapons[weapon] += 1;
            switchWeapon(weapon);
            weaponsCost[weapon] = weaponsCost[weapon]*2;
            maxAmmo[weapon] = maxAmmo[weapon]*2;
            currentAmmo[weapon] = maxAmmo[weapon];
            bulletType[weapon] = 1;
        }
        else if(weapons[weapon] == 2)
        {
            weapons[weapon] += 1;
            switchWeapon(weapon);
            weaponsCost[weapon] = weaponsCost[weapon]*2;
            maxAmmo[weapon] = maxAmmo[weapon]*2;
            currentAmmo[weapon] = maxAmmo[weapon];
            bulletType[weapon] = 2;
        }
    }

    private void shooting()
    {
        if(currentAmmo[currentWeapon] != maxAmmo[currentWeapon])
        {
            if(Greenfoot.isKeyDown("r") && currentAmmo[currentWeapon] != 0)
            {
                currentAmmo[currentWeapon] = 0;
            }
        }
        if(currentAmmo[currentWeapon] > 0)
        {
            fire();
            reloadTime = 50;
        }
        if(currentAmmo[currentWeapon] <= 0)
        {
            reload();
        }
    }

    private void fire()
    {
        loaded++;
        if(Greenfoot.isKeyDown("space"))
        {
            if(loaded >= weaponROF[currentWeapon] && currentWeapon != 1)
            {
                if(currentWeapon != 0 && currentWeapon != 6 && currentWeapon != 9)
                {
                    shootBullet();
                    flashCheck(currentWeapon);
                    currentAmmo[currentWeapon]--;
                    loaded = 0;
                }
                else if(currentWeapon == 0)
                {
                    shootFlame();
                    flash(1);
                    currentAmmo[currentWeapon]--;
                    loaded = 0;
                }
                else if(currentWeapon == 6)
                {
                    shootRocket();
                    flash(2);
                    currentAmmo[currentWeapon]--;
                    loaded = 0;
                }
                else if(currentWeapon == 9)
                {
                    shootEnergy();
                    currentAmmo[currentWeapon]--;
                    loaded = 0;
                }
            }
            else if(currentWeapon == 1 && armed)
            {
                shootBullet();
                flash(1);
                currentAmmo[currentWeapon]--;
                armed = false;
            }
        }
        else
        {
            armed = true;
        }
    }

    private void flash(int type)
    {
        int ranX = Greenfoot.getRandomNumber(20)+20;
        int ranY = Greenfoot.getRandomNumber(12)+5;

        if(type == 1)
        {
            getWorld().addObject(new Flash(), getX()-ranX,getY()-ranY);
        }
        else if(type == 2)
        {
            for(int i=0;i<10;i++)
            {
                ranX = Greenfoot.getRandomNumber(20)+20;
                ranY = Greenfoot.getRandomNumber(12)+5;
                getWorld().addObject(new Flash(), getX()-ranX,getY()-ranY);
            }
        }
    }

    private void flashCheck(int weapon)
    {
        if(weapon == 3 || weapon == 4)
        {
            flash(2);
        }
        else
        {
            flash(1);
        }
    }

    private void reload()
    {
        reloadTimer++;
        if(reloadTimer == 1)
        {
            if(currentWeapon != 1 && currentWeapon != 2 && currentWeapon != 3 
            && currentWeapon != 4 && currentWeapon != 5)
            {
                Greenfoot.playSound("gun_reload.wav");
                reloadTime = 250;
            }
            else if(currentWeapon == 3)
            {
                Greenfoot.playSound("shotgun_quick_reloading.wav");
                reloadTime = 180;
            }
            else
            {
                Greenfoot.playSound("reload_pistol.wav");
                reloadTime = 180;
            }
        }

        if(reloadTimer >= reloadTime)
        {
            currentAmmo[currentWeapon] = maxAmmo[currentWeapon];
            reloadTimer = 0;
        }
    }

    private void shootBullet()
    {
        if(currentWeapon != 3 && currentWeapon != 4)
        {
            Bullet bullet = new Bullet(weapons[currentWeapon], getCaliber(),1);
            getWorld().addObject(bullet, getX()-20,getY()-12);
        }
        else if(currentWeapon == 3)
        {
            bulletSpread();
        }
        else if(currentWeapon == 4)
        {
            Bullet bullet = new Bullet(weapons[currentWeapon], getCaliber(),3);
            getWorld().addObject(bullet, getX()-20,getY()-12);
        }
        chooseSound();
    }

    private void shootFlame()
    {
        int random = Greenfoot.getRandomNumber(2);
        int type = 0;
        if(random == 0)
        {
            type = 1;
        }
        else if(random == 1)
        {
            type = 2;
        }
        Flame flame = new Flame(type,getCaliber());
        getWorld().addObject(flame, getX()-90,getY()-12);
        chooseSound();
        if(Greenfoot.getRandomNumber(30) == 0)
        {
            getWorld().addObject(new Fire(),getX()-100,getY());
        }
    }

    private void shootRocket()
    {
        Rocket rocket = new Rocket(weapons[currentWeapon], getCaliber());
        getWorld().addObject(rocket, getX()-25,getY()-12);
        chooseSound();
    }

    private void shootEnergy()
    {
        Pulse pulse = new Pulse(getCaliber());
        getWorld().addObject(pulse, getX()-50,getY()-12);
        chooseSound();
    }

    private void bulletSpread()
    {
        if(weapons[currentWeapon] == 1)
        {
            for(int i=0;i<10;i++)
            {
                Bullet bullet = new Bullet(weapons[currentWeapon], getCaliber(),2);
                getWorld().addObject(bullet, getX()-20,getY()-12);
            }
        }
        else if(weapons[currentWeapon] > 1)
        {
            for(int i=0;i<15;i++)
            {
                Bullet bullet = new Bullet(weapons[currentWeapon], getCaliber(),2);
                getWorld().addObject(bullet, getX()-20,getY()-12);
            }
        }
    }

    private void chooseSound()
    {
        if(currentWeapon == 1 || currentWeapon == 2)
        {
            if(weapons[currentWeapon] > 1)
            {
                Greenfoot.playSound("Laser.wav");
            }
            else
            {
                Greenfoot.playSound("pistol_shot.wav");
            }
        }
        else if(currentWeapon == 3)
        {
            Greenfoot.playSound("shotgun_blast.wav");
        }
        else if(currentWeapon == 4)
        {
            if(weapons[currentWeapon] > 1)
            {
                Greenfoot.playSound("Laser_Sniper.wav");
            }
            else
            {
                Greenfoot.playSound("sniper_fire.wav");
            }
        }
        else if(currentWeapon == 5)
        {
            if(weapons[currentWeapon] > 1)
            {
                Greenfoot.playSound("Laser_fire.wav");
            }
            else
            {
                Greenfoot.playSound("assault_fire.wav");
            }
        }
        else if(currentWeapon == 6)
        {
            Greenfoot.playSound("rocket_fire.wav");
        }
        else if(currentWeapon == 7 || currentWeapon == 8)
        {
            if(weapons[currentWeapon] > 1)
            {
                Greenfoot.playSound("Laser_MG.wav");
            }
            else
            {
                Greenfoot.playSound("MG_fire.wav");
            }
        }
        else if(currentWeapon == 9)
        {
            Greenfoot.playSound("Electric Shot.wav");
        }
        else if(currentWeapon == 0)
        {
            Greenfoot.playSound("Flame1.wav");
        }
    }

    private int getCaliber()
    {
        int w = currentWeapon;
        if(weapons[currentWeapon] == 1)
        {
            if(w != 3 && w != 4 && w != 6)
            {
                return 1;
            }
            else if(w == 3)
            {
                return 2;
            }
            else if(w == 4)
            {
                return 5;
            }
            else if(w == 6)
            {
                return 1;
            }
        }
        else if(weapons[currentWeapon] == 2)
        {
            if(w != 3 && w != 4 && w != 6)
            {
                return 2;
            }
            else if(w == 3)
            {
                return 4;
            }
            else if(w == 4)
            {
                return 10;
            }
            else if(w == 6)
            {
                return 1;
            }
        }
        else if(weapons[currentWeapon] == 3)
        {
            if(w != 3 && w != 4 && w != 6)
            {
                return 4;
            }
            else if(w == 3)
            {
                return 8;
            }
            else if(w == 4)
            {
                return 20;
            }
            else if(w == 6)
            {
                return 1;
            }
        }
        return 0;
    }

    public void perk()
    {
        if(perks[0])
        {
            perkTimer[0]++;
            if(perkTimer[0] >= perkTime)
            {
                moveSpeed = 5;
                perkTimer[0] = 0;
                perks[0] = false;
            }
        }
        else if(perks[1])
        {
            perkTimer[1]++;
            if(perkTimer[1] >= perkTime)
            {
                setROF();
                perkTimer[1] = 0;
                perks[1] = false;
            }
        }
        else if(perks[2])
        {
            perkTimer[2]++;
            if(perkTimer[2] >= perkTime)
            {
                maxAmmo[tempWeapon] /= 2;
                currentAmmo[tempWeapon] /= 2;
                perkTimer[2] = 0;
                perks[1] = false;
            }
        }
    }

    public void returnCaliber()
    {
        GameWorld world = (GameWorld) getWorld();
        world.caliber = getCaliber();
    }

    public int getPoints()
    {
        return points;
    }

    public void increasePoints(int amt)
    {
        points += amt;
    }

    public void decreasePoints(int amt)
    {
        points -= amt;
    }

    public int getAmmo()
    {
        return currentAmmo[currentWeapon];
    }

    public void activateSpeedUp()
    {
        if(!perks[0])
        {
            moveSpeed = 8;
        }
        perks[0] = true;
        perkTimer[0] = 0;
    }

    public void activateQuickShot()
    {
        if(!perks[1])
        {
            weaponROF[currentWeapon] /= 2;
        }
        perks[1] = true;
        perkTimer[1] = 0;
    }

    public void activateX2Mags()
    {
        if(!perks[2])
        {
            tempWeapon = currentWeapon;
            maxAmmo[tempWeapon] *= 2;
            currentAmmo[tempWeapon] = 0;
        }
        perks[2] = true;
        perkTimer[2] = 0;
    }

    public String getWeaponInfo()
    {
        String cost = checkCost(weaponsCost[currentWeapon]);
        return currentWeapon + ": " + weaponName[currentWeapon] + "  DMG: " + getCaliber() + 
        "  Upgrade Cost: " + cost;
    }

    private String checkCost(int cost)
    {
        if(cost == 600000 || cost == 0 || weapons[currentWeapon] == 3)
        {
            return "---";
        }
        return ""+cost;
    }

    private void endGame()
    {
        Greenfoot.playSound("Torture.wav");
        Greenfoot.stop();
    }
}
