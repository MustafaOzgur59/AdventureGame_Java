import java.util.Random;

public abstract class  BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    private Random rand = new Random();

    public BattleLoc(String name, Player player,Obstacle obstacle,String award,int maxObstacle) {
        super(name, player);
        this.obstacle=obstacle;
        this.award=award;
        this.maxObstacle = maxObstacle;
    }

    public int randomObstacle(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    @Override
    boolean onLocation() {
        int rndObs = this.randomObstacle();
        System.out.println("You are here : " + this.getName());
        System.out.println("Careful! " + (rndObs == 1 ? "There is " : "There are ") + rndObs + " " + this.getObstacle().getName() + (rndObs == 1 ? "" : "s") + " here");
        System.out.print("<F>ight or <R>un : ");
        String selectCase = input.nextLine().toUpperCase();
        if(selectCase.equals("F") && combat(rndObs)){
                System.out.println(this.getName() + "have beaten all enemies !");
                if(!this.getName().equals("Mine")){
                this.setReachable(false);
                }
                switch (this.getName()){
                    case "Cave":
                        this.getPlayer().getInventory().setFood(true);
                        break;
                    case "Forest":
                        this.getPlayer().getInventory().setWood(true);
                        break;
                    case "River":
                        this.getPlayer().getInventory().setWater(true);
                        break;
                }
                return true;
        }

        if(this.getPlayer().getHealth() <= 0){
            System.out.println("You died");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber){
        for(int i=0;i<obsNumber;i++){
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            if(this.getObstacle().getId() == 4){
                this.getObstacle().setDamage(rand.nextInt(3)+3);
            }
            obstacleStats(i+1);
            while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.println("<H>it or <R>un");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("H")){
                    int randInt = rand.nextInt(2);
                    if (randInt >=1){
                        playerHit();
                        afterHit();
                        System.out.println("-----------------------");
                        obstacleHit();
                        afterHit();
                    }
                    else{
                        obstacleHit();
                        afterHit();
                        System.out.println("-----------------------");
                        playerHit();
                        afterHit();
                    }
                }
                else {
                    return false;
                }
            }
            if(this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.println("You have killed the monster");
                System.out.println(this.getObstacle().getAward() + (this.getObstacle().getAward() == 1 ? "coin earned" : "coins earned"));
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Current money : " + this.getPlayer().getMoney());
                if(this.getObstacle().getId() == 4 && this.getName().equals("Mine")){
                    this.getSnakeReward();
                }
            } else {
                return false;
            }

        }
        return true;
    }

    private void getSnakeReward() {
        int firstChoice = rand.nextInt(100);
        int secondChoice = rand.nextInt(100);
        Object reward = null;
        if(firstChoice <=15){
            if (secondChoice <=50){
                System.out.println("Snake dropped gun");
                reward = new Weapon("Gun",1,2,10);
            } else if (secondChoice <= 80) {
                System.out.println("snake dropped sword");
                reward = new Weapon("Sword", 2,3,35);
            } else {
                System.out.println("Snake dropped rifle");
                reward = new Weapon("Rifle", 3,7,45);
            }
        }
        else if (firstChoice <= 30){
            if (secondChoice <=50){
                System.out.println("Snake dropped light armor");
                reward = new Armor(1, 1,15,"Light");
            } else if (secondChoice <= 80) {
                System.out.println("snake dropped medium weight armor");
                reward = new Armor(2, 3,25,"Medium");
            } else {
                System.out.println("Snake dropped heavy armor");
                reward = new Armor(3, 5,40,"Heavy");
            }
        } else if (firstChoice <= 55) {
            if (secondChoice <=50){
                System.out.println("Snake dropped 1 coin");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
            } else if (secondChoice <= 80) {
                System.out.println("snake dropped 5 coin");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
            } else {
                System.out.println("Snake dropped 10 coin");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
            }
        } else {
            System.out.println("Snake did not drop anything");
        }

        if (reward != null){
            System.out.println(reward.getClass().getName());
            System.out.println("Do you wish to equip item Y/N : ");
            String choice = input.nextLine().toUpperCase();
            if (choice.equals("Y")){
                if (reward.getClass().getName().equals("Weapon")){
                    this.getPlayer().getInventory().setWeapon((Weapon) reward);
                } else {
                    this.getPlayer().getInventory().setArmor((Armor) reward);
                }
            } else{
                System.out.println();
            }
        }

    }


    public void playerHit(){
        System.out.println("You hit !");
        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getDamage());
    }

    public void obstacleHit(){
        if (this.getObstacle().getHealth() > 0 ){
            System.out.println();
            System.out.println("Monster hit !");
            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
            if(obstacleDamage < 0){
                obstacleDamage = 0;
            }
            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
        }
    }
    public void  playerStats(){
        System.out.println("Character Values");
        System.out.println("--------------------");
        System.out.println("Heatlh : " + this.getPlayer().getHealth());
        System.out.println("Weapon : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Armor : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Damage : " + this.getPlayer().getTotalDamage());
        System.out.println("Money : " + this.getPlayer().getMoney());
        System.out.println();
    }
    public void obstacleStats(int i){
        System.out.println( i + ". " + this.getObstacle().getName() + " Values");
        System.out.println("----------------------------");
        System.out.println("Health : " + this.getObstacle().getHealth());
        System.out.println("Damage : " + this.getObstacle().getDamage());
        System.out.println("Reward : " + this.getObstacle().getAward());
        System.out.println();
    }

    public void afterHit(){
        System.out.println("Health : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Health : " + this.getObstacle().getHealth());
    }
}
