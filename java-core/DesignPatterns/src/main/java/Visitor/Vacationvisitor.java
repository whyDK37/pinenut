public class VacationVisitor extends Visitor
{
 protected int total_days;
 public VacationVisitor()
 {
   total_days = 0;
 }
 //-----------------------------
 public void visit(Employee emp)
 {
    total_days += emp.getVacDays();
 }
 //-----------------------------
 public void visit(Boss boss)
 {
    total_days += boss.getVacDays();
 }
 //-----------------------------                             
 public int getTotalDays()
 {
    return total_days;
 }
}
