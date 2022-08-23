import java.util.Scanner;

public class Player {
    private int damage,health,money,originalHealth;
    private String name;
    private String charName;
    private Inventory inventory;

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }

    private Scanner input = new Scanner(System.in);

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Player(String name) {
        this.inventory = new Inventory();
        this.name = name;
    }

    public void selectChar(){
        GameChar[] charList = {new Samurai(),new Archer(), new Knight()};
        System.out.println("----------------------------");
        for (GameChar gameChar: charList) {
            System.out.println("ID : " + gameChar.getId()
                    +"\t Character: "  + gameChar.getName()
                    + " \t Damage: " + gameChar.getDamage()
                    +" \t Health: "+ gameChar.getHealth()
                    +" \t Money: " + gameChar.getMoney());
        }
        System.out.println("----------------------------");
        System.out.println("Please enter a character : ");
        int selectChar = input.nextInt();
        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
        System.out.println(
                "Character: "  + this.getCharName()
                + " \t Damage: " + this.getTotalDamage()
                +" \t Health: "+ this.getHealth()
                +" \t Money: " + this.getMoney());
    }



    public void initPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setOriginalHealth(gameChar.getHealth());
        this.setCharName(gameChar.getName());
    }

    public void printInfo(){
        System.out.println("Weapon : " + this.getInventory().getWeapon().getName()
                + "\tArmor : " + this.getInventory().getArmor().getName()
                +"\tBlock : " +  this.getInventory().getArmor().getBlock()
                + "\tDamage : " + this.getTotalDamage()
                + "\tHealth : " + this.getHealth()
                + "\tMoney : " + this.getMoney());
    }
    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }
}
