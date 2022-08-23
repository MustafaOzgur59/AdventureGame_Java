import java.util.Scanner;

public abstract class Location {
    private String name;
    private Player player;

    private boolean isReachable;
    protected static Scanner input = new Scanner(System.in);

    public Location(String name, Player player) {
        this.name = name;
        this.player = player;
        this.isReachable = true;
    }

    abstract boolean onLocation();

    public boolean isReachable() {
        return isReachable;
    }

    public void setReachable(boolean reachable) {
        isReachable = reachable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
