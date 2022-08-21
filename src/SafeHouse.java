public class SafeHouse extends NormalLoc{

    public SafeHouse(Player player) {
        super("Güvenli Ev", player);
    }

    @Override
    public boolean onLocation(){
        System.out.println("Güvenli evdesiniz. Canınız yenilendi");
        return true;
    }
}
