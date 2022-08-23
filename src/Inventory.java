public class Inventory {
    private Weapon weapon;
    private Armor armor;

    private boolean food,water,wood;

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Inventory() {
        this.armor = new Armor(-1,0,0,"Rags");
        this.weapon = new Weapon("Fist",-1,0,0);
        this.water = false;
        this.wood = false;
        this.food = false;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isWood() {
        return wood;
    }

    public void setWood(boolean wood) {
        this.wood = wood;
    }
}
