public class Inventory {
    private Weapon weapon;
    private Armor armor;

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
        this.armor = new Armor(-1,0,0,"Paçavra");
        this.weapon = new Weapon("Yumruk",-1,0,0);
    }
}
