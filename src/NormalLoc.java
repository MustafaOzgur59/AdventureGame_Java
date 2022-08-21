public class NormalLoc extends Location{
    public NormalLoc(String name, Player player) {
        super(name, player);
    }

    @Override
    boolean onLocation() {
        return true;
    }
}
