public class Employee
{
   int sickDays, vacDays;
   float Salary;
   String Name;

   public Employee(String name, float salary, int vacdays, int sickdays)
   {
      vacDays = vacdays;
      sickDays = sickdays;
      Salary = salary;
      Name = name;
   }
   public String getName()
   {
      return Name;
   }
   public int getSickdays()
   {
      return sickDays;
   }
   public int getVacDays()
   {
      return vacDays;
   }
   public float getSalary()
   {
      return Salary;
   }
   public void accept(Visitor v)
   {
      v.visit(this);
   }
}
