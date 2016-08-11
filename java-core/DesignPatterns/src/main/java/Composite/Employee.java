/**
 *  A Component with some common function implementation
 *  You can abstract it.
 */
import java.util.*;

public class Employee {
    String name;
    float salary;
    Vector subordinates;
    boolean isLeaf;
    Employee parent = null;

    //-------------------------------------------
    public Employee(String _name, float _salary) {
        name = _name;
        salary = _salary;
        subordinates = new Vector();
        isLeaf = false;
    }
    //-------------------------------------------
    public Employee(Employee _parent, String _name, float _salary) {
        name = _name;
        salary = _salary;
        parent = _parent;
        subordinates = new Vector();
        isLeaf = false;
    }
    //-------------------------------------------
    public void setLeaf(boolean b) {
        isLeaf = b;    //if true, do not allow children
    }
    //-------------------------------------------
    public float getSalary() {
        return salary;
    }
    //-------------------------------------------
    public String getName() {
        return name;
    }
    //-------------------------------------------
    public boolean add(Employee e) {
        if (! isLeaf) { 
            subordinates.addElement(e);
        }
        return isLeaf;    //false if unsuccessful
    }
    //-------------------------------------------
    public void remove(Employee e) {
        if (! isLeaf) {
            subordinates.removeElement(e);
        }
    }
    //-------------------------------------------
    public Enumeration elements() {
        return subordinates.elements();
    }
    //-------------------------------------------
    public Employee getChild(String s) {
        Employee newEmp = null;

        if(getName().equals(s)) {
            return this;
        } else {
            boolean found = false;
            Enumeration e = elements();
            while(e.hasMoreElements() && (! found)) {  
                newEmp = (Employee)e.nextElement();
                found = newEmp.getName().equals(s);
                if (! found) {
                    newEmp = newEmp.getChild(s);
                    found =(newEmp != null);
                }
            }
            if (found) {
                return newEmp;
            } else {
                return null;
            }
        }
    }
    //-------------------------------------------
    public float getSalaries() {
        float sum = salary;
        for(int i = 0; i < subordinates.size(); i++) {
            sum += ((Employee)subordinates.elementAt(i)).getSalaries();
        }
        return sum;
    }
    
}