import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {

        System.out.println("Welcome to the adventure game !!");
        System.out.println("Please enter a name : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        Location safeHouse = new SafeHouse(player);
        Location toolStore = new ToolStore(player);
        Location cave = new Cave(player);
        Location river = new River(player);
        Location forest = new Forest(player);
        Location mine = new Mine(player);

        System.out.println(player.getName() + " welcome to this dark and foggy island ! " +
                "Everything here is real !!");
        System.out.println("Please choose a character !");
        player.selectChar();
        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("########Areas########");
            System.out.println();
            System.out.println("0 - Quit Game.");
            System.out.println("1 - Safe House");
            System.out.println("2 - Shop");
            System.out.println("3 - Cave");
            System.out.println("4 - Forest");
            System.out.println("5 - River");
            System.out.println("6 - Mine");
            System.out.println("Where do you want to go : ");
            int selectLoc = input.nextInt();
            switch (selectLoc) {
                case 0:
                    location=null;
                    break;
                case 1:
                    location =safeHouse;
                    break;
                case 2:
                    location = toolStore;
                    break;
                case 3:
                    location = cave;
                    break;
                case 4:
                    location = forest;
                    break;
                case 5:
                    location = river;
                    break;
                case 6:
                    location = mine;
                    break;
                default:
                    System.out.println("Please enter a valid id.");
                    break;
            }

            if (location != null && !location.isReachable()){
                System.out.println("Cant reach " + location.getName() + " anymore. Select another place!");
                continue;
            }

            if(location == null){
                System.out.println("Game is finished. See you again :)");
                break;
            }

            if (!location.onLocation() && location.isReachable()) {
                System.out.println("Game Over");
                break;
            }

            if (location == safeHouse
                    && safeHouse.getPlayer().getInventory().isFood()
                    && safeHouse.getPlayer().getInventory().isWater()
                    && safeHouse.getPlayer().getInventory().isWood()){
                System.out.println("Congrats, You have finished the game !!!");
                break;
            }
        }
    }
}
