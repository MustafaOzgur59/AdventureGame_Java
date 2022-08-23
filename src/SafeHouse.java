public class SafeHouse extends NormalLoc{

    public SafeHouse(Player player) {
        super("Safe House", player);
    }

    @Override
    public boolean onLocation(){
        System.out.println("You are in Safe House. Health is replenished");
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
        return true;
    }
}
