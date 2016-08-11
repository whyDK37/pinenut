public class Boss extends Employee
{
   private int bonusDays;

   public Boss(String name, float salary, int vacdays, int sickdays)
   {
      super(name, salary, vacdays, sickdays);
   }
   public void setBonusDays(int bonus)
   {
      bonusDays = bonus;
   }
   public int getBonusDays()
   {
      return bonusDays;
   }   
   public void accept(Visitor v)
   {
      v.visit(this);
   }
}
