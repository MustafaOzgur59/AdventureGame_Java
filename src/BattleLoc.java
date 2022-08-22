import java.util.Random;

public abstract class  BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

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
        if(selectCase.equals("S")){
            System.out.println("Savaş işlemleri");
        }
        return true;
    }
}
