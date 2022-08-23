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
        System.out.println("Şu an buradasınız : " + this.getName());
        System.out.println("Dikkatli ol! Burada " + String.valueOf(rndObs) + " tane " + this.getObstacle().getName() + " yaşıyor");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = input.nextLine().toUpperCase();
        if(selectCase.equals("S") && combat(rndObs)){
                System.out.println(this.getName() + "tüm düşmanları yendiniz !");
                this.setReachable(false);
                switch (this.getName()){
                    case "Mağara":
                        this.getPlayer().getInventory().setFood(true);
                        break;
                    case "Orman":
                        this.getPlayer().getInventory().setWood(true);
                        break;
                    case "Nehir":
                        this.getPlayer().getInventory().setWater(true);
                        break;
                }
                return true;
        }

        if(this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber){
        for(int i=0;i<obsNumber;i++){
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i);
            while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.println("<V>ur veya <Kaç>");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")){
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
                System.out.println("Canavarı öldürdünüz");
                System.out.println(this.getObstacle().getAward() + " para kazandınız");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
            } else {
                return false;
            }

        }
        return true;
    }
    public void playerHit(){
        System.out.println("Siz vurdunuz !");
        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getDamage());
    }

    public void obstacleHit(){
        if (this.getObstacle().getHealth() > 0 ){
            System.out.println();
            System.out.println("canavar size vurdu !");
            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
            if(obstacleDamage < 0){
                obstacleDamage = 0;
            }
            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
        }
    }
    public void  playerStats(){
        System.out.println("Oyuncu Değerleri");
        System.out.println("--------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Para : " + this.getPlayer().getMoney());
        System.out.println();
    }
    public void obstacleStats(int i){
        System.out.println( i + ". " + this.getObstacle().getName() + " değerleri");
        System.out.println("----------------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + this.getObstacle().getAward());
        System.out.println();
    }

    public void afterHit(){
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı : " + this.getObstacle().getHealth());
    }
}
