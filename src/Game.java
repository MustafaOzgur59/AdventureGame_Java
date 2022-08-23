import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {

        System.out.println("Macera oyununa hoş geldiniz !!");
        System.out.println("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        Location safeHouse = new SafeHouse(player);
        Location toolStore = new ToolStore(player);
        Location cave = new Cave(player);
        Location river = new River(player);
        Location forest = new Forest(player);
        Location mine = new SafeHouse(player);

        System.out.println("Sayın " + player.getName() + " bu karanlık ve sisli adaya hoşgeldiniz ! " +
                "Burada Yaşananların hepsi gerçek !!");
        System.out.println("Lütfen bir karakter seçiniz !");
        player.selectChar();
        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("########Bölgeler########");
            System.out.println();
            System.out.println("0 - Çıkış yap.");
            System.out.println("1 - Güvenli Ev");
            System.out.println("2 - Mağaza");
            System.out.println("3 - Mağara");
            System.out.println("4 - Orman");
            System.out.println("5 - Nehir");
            System.out.println("Lütfen gitmek istediğniz yeri seçiniz : ");
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
                default:
                    System.out.println("Lütfen geçerli bir yer giriniz!");
                    break;
            }

            if (location != null && !location.isReachable()){
                System.out.println("Cant reach " + location.getName() + " anymore. Select another place!");
                continue;
            }

            if(location == null){
                System.out.println("Oyun bitti yine bekleriz :)");
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
                System.out.println("Tebrikler oyunu bitirdiniz!!!");
                break;
            }
        }
    }
}
