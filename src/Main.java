public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Samurai s = new Samurai();
        System.out.println(s.getHealth());
        game.start();
    }
}
