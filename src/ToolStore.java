public class ToolStore extends NormalLoc{
     public ToolStore(Player player){
         super("Mağaza",player);
     }

     @Override
    public boolean onLocation(){
         System.out.println("-------Mağazaya Hoşgeldiniz-------");
         System.out.println("1 - Silahlar");
         System.out.println("2 - Zırhlar");
         System.out.println("3 - Çıkış");
         System.out.println("Seçiminiz : ");
         int selectCase = Location.input.nextInt();
         while(selectCase<0 || selectCase > 3) {
             System.out.println("Geçersiz değer, tekrar giriniz : ");
             selectCase = Location.input.nextInt();
         }
         switch (selectCase){
             case 1:
                 printWeapon();
                 buyWeapon();
                 break;
             case 2:
                 printArmor();
                 break;
             case 3:
                 System.out.println("Bir daha bekleriz !");
                 return true;
         }

         return true;
     }

     public void printWeapon(){
         System.out.println("-----Silahlar-----");
         for (Weapon  w : Weapon.weapons()){
             System.out.println("ID : " + w.getId()
                     + "\t<" +w.getName()
                     + "\tFiyat : "
                     + w.getPrice()
                     + "\tHasar : " + w.getDamage() +">");
         }

     }

     public void buyWeapon(){
         System.out.println("Bir silah seçiniz : ");
         int selectWeapon = Location.input.nextInt();
         while(selectWeapon<0 || selectWeapon > Weapon.weapons().length) {
             System.out.println("Geçersiz değer, tekrar giriniz : ");
             selectWeapon = Location.input.nextInt();
         }

         Weapon selectedWeapon = Weapon.getWeaponById(selectWeapon);

         if(selectedWeapon != null){
             // weapon purchase logic
             if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                 System.out.println("Yeterli paranız bulunmamaktadır");
             }
             else{
                 System.out.println(selectedWeapon.getName() + " silahını satın aldınız ! ");
                 int newBalance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                 this.getPlayer().setMoney(newBalance);
                 System.out.println("Eski silah : " + this.getPlayer().getInventory().getWeapon().getName());
                 this.getPlayer().getInventory().setWeapon(selectedWeapon);
                 System.out.println("Yeni silah : " + this.getPlayer().getInventory().getWeapon().getName());
                 System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
             }
         }

     }

    public void printArmor(){
        System.out.println("-----Zırhlar-----");

    }
}
