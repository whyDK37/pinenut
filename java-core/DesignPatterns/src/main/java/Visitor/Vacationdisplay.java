import java.awt.*;
import java.awt.event.*;
import java.util.*;

//swing classes
import javax.swing.text.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class VacationDisplay extends JxFrame
   implements ActionListener
{
   JawtList empList;
   JTextField total, btotal;
   JButton Vac;
   Employee[] employees;
//-----------------------------------------
  public VacationDisplay()
  {
     super("Vacation Display");
     JPanel jp = new JPanel();
     getContentPane().add(jp);
     jp.setLayout(new GridLayout(1,2));
     empList = new JawtList(10);
     jp.add(empList);
          
     createEmployees();
     
     Box box = Box.createVerticalBox();
     jp.add(box);
     total = new JTextField(5);
     total.setHorizontalAlignment(JTextField.CENTER);
     box.add(total);
     box.add(Box.createVerticalStrut(10));
     btotal = new JTextField(5);
     btotal.setHorizontalAlignment(JTextField.CENTER);
     box.add(btotal);
     box.add(Box.createVerticalStrut(10));
     Vac = new JButton("Vacations");
     box.add(Vac);
     Vac.addActionListener(this);
     setSize(300,200); 
     setVisible(true); 
     total.setText("  ");
     total.setBackground(Color.white);

  }
  //-----------------------------------------
  public void createEmployees()
  {
     employees = new Employee[7];
     int i = 0;
     employees[i++] =new Employee("Susan Bear",  55000, 12, 1);
     employees[i++] = new Employee("Adam Gehr",  150000, 9, 0);
     employees[i++] = new Employee("Fred Harris",  50000, 15, 2);
     employees[i++] = new Employee("David Oakley",  57000, 12, 2);
     employees[i++] = new Employee("Larry Thomas",  100000, 20, 6);
     Boss b = new Boss("Leslie Susi", 175000, 16,4);
     b.setBonusDays(12);
     Boss b1 = new Boss("Laurence Byerly", 35000, 17,6);
     b1.setBonusDays(17);
     employees[i++] = b;
     employees[i++] = b1;

     for ( i = 0; i < employees.length; i++)
     {
      empList.add(employees[i].getName());
     }
  }
 //----------------------------------------- 
  public void actionPerformed(ActionEvent e)
  {
    VacationVisitor vac = new VacationVisitor();
    bVacationVisitor bvac = new bVacationVisitor();
     for (int i = 0; i < employees.length; i++)
     {
      employees[i].accept(vac);
      employees[i].accept(bvac);
     }
     total.setText(new Integer(vac.getTotalDays()).toString());
     btotal.setText(new Integer(bvac.getTotalDays()).toString());
  }
  //-----------------------------------------
  static public void main(String argv[])
  {
     new VacationDisplay();
  }
}
