import java.util.Random;

public class Snake extends Obstacle{
    private static final Random rand = new Random();
    public Snake() {
        super(4, rand.nextInt(3) + 3, 12, "Snake", 0, 12);
    }
}
