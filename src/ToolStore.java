public class ToolStore extends NormalLoc{
     public ToolStore(Player player){
         super("ToolStore",player);
     }

     @Override
    public boolean onLocation(){
         System.out.println("-------Welcome to ToolStore-------");
         boolean showMenu = true;
         while(showMenu){
             System.out.println("1 - Weapons");
             System.out.println("2 - Armors");
             System.out.println("3 - Exit");
             System.out.println("Choice : ");
             int selectCase = Location.input.nextInt();
             while(selectCase<0 || selectCase > 3) {
                 System.out.println("Invalid value, enter again : ");
                 selectCase = Location.input.nextInt();
             }
             switch (selectCase){
                 case 1:
                     printWeapon();
                     buyWeapon();
                     break;
                 case 2:
                     printArmor();
                     buyArmor();
                     break;
                 case 3:
                     System.out.println("Come again !");
                     showMenu=false;
                     break;
             }
         }
         return true;
     }

     public void printWeapon(){
         System.out.println("-----Weaopns-----");
         System.out.println("0 - Quit.");
         for (Weapon  w : Weapon.weapons()){
             System.out.println("ID : " + w.getId()
                     + "\t<" +w.getName()
                     + "\tPrice : "
                     + w.getPrice()
                     + "\tDamage : " + w.getDamage() +">");
         }

     }

     public void buyWeapon(){
         System.out.println("Select a weapon : ");
         int selectWeapon = Location.input.nextInt();
         while(selectWeapon<0 || selectWeapon > Weapon.weapons().length) {
             System.out.println("Invalid value, enter again : ");
             selectWeapon = Location.input.nextInt();
         }
         if(selectWeapon != 0){
             Weapon selectedWeapon = Weapon.getWeaponById(selectWeapon);

             if(selectedWeapon != null){
                 // weapon purchase logic
                 if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                     System.out.println("Not enough money");
                 }
                 else{
                     System.out.println( "Purchased " + selectedWeapon.getName() + " ! ");
                     int newBalance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                     this.getPlayer().setMoney(newBalance);
                     System.out.println("Old Weapon : " + this.getPlayer().getInventory().getWeapon().getName());
                     this.getPlayer().getInventory().setWeapon(selectedWeapon);
                     System.out.println("New Weapon : " + this.getPlayer().getInventory().getWeapon().getName());
                     System.out.println("Balance : " + this.getPlayer().getMoney());
                 }
             }
         }



     }

    public void printArmor(){
        System.out.println("-----Armors-----");
        System.out.println("0 - Quit. ");
        for (Armor  a : Armor.armors()){
            System.out.println("ID : " + a.getId()
                    + "\t<" +a.getName()
                    + "\tPrice : "
                    + a.getPrice()
                    + "\tArmor : " + a.getBlock() +">");
        }
    }
    public void buyArmor(){
        System.out.println("Select an armor : ");
        int selectArmor = Location.input.nextInt();
        while(selectArmor<0 || selectArmor > Armor.armors().length) {
            System.out.println("Invalid value, enter again : ");
            selectArmor = Location.input.nextInt();
        }
        if(selectArmor != 0){
            Armor selectedArmor = Armor.getArmorById(selectArmor);

            if(selectedArmor != null){
                // weapon purchase logic
                if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Not enought money");
                }
                else{
                    System.out.println("Purchased" + selectedArmor.getName() + " ! ");
                    int newBalance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(newBalance);
                    System.out.println("Old Armor: " + this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("New Armor : " + this.getPlayer().getInventory().getArmor().getName());
                    System.out.println("Balance : " + this.getPlayer().getMoney());
                }
            }

        }


    }
}
