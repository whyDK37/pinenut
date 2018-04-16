package Visitor.t1;

import java.util.*;

public class EmployeeVisitor {

}


//员工类：抽象元素类  
interface Employee {
    void accept(Department handler); //接受一个抽象访问者访问
}

//全职员工类：具体元素类  
class FulltimeEmployee implements Employee {
    private String name;
    private double weeklyWage;
    private int workTime;

    public FulltimeEmployee(String name, double weeklyWage, int workTime) {
        this.name = name;
        this.weeklyWage = weeklyWage;
        this.workTime = workTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeeklyWage(double weeklyWage) {
        this.weeklyWage = weeklyWage;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public String getName() {
        return (this.name);
    }

    public double getWeeklyWage() {
        return (this.weeklyWage);
    }

    public int getWorkTime() {
        return (this.workTime);
    }

    public void accept(Department handler) {
        handler.visit(this); //调用访问者的访问方法  
    }
}

//兼职员工类：具体元素类  
class ParttimeEmployee implements Employee {
    private String name;
    private double hourWage;
    private int workTime;

    public ParttimeEmployee(String name, double hourWage, int workTime) {
        this.name = name;
        this.hourWage = hourWage;
        this.workTime = workTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHourWage(double hourWage) {
        this.hourWage = hourWage;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public String getName() {
        return (this.name);
    }

    public double getHourWage() {
        return (this.hourWage);
    }

    public int getWorkTime() {
        return (this.workTime);
    }

    public void accept(Department handler) {
        handler.visit(this); //调用访问者的访问方法  
    }
}

//部门类：抽象访问者类  
abstract class Department {
    //声明一组重载的访问方法，用于访问不同类型的具体元素
    public abstract void visit(FulltimeEmployee employee);

    public abstract void visit(ParttimeEmployee employee);
}

//财务部类：具体访问者类  
class FADepartment extends Department {
    //实现财务部对全职员工的访问  
    public void visit(FulltimeEmployee employee) {
        int workTime = employee.getWorkTime();
        double weekWage = employee.getWeeklyWage();
        if (workTime > 40) {
            weekWage = weekWage + (workTime - 40) * 100;
        } else if (workTime < 40) {
            weekWage = weekWage - (40 - workTime) * 80;
            if (weekWage < 0) {
                weekWage = 0;
            }
        }
        System.out.println("正式员工" + employee.getName() + "实际工资为：" + weekWage + "元。");
    }

    //实现财务部对兼职员工的访问  
    public void visit(ParttimeEmployee employee) {
        int workTime = employee.getWorkTime();
        double hourWage = employee.getHourWage();
        System.out.println("临时工" + employee.getName() + "实际工资为：" + workTime * hourWage + "元。");
    }
}

//人力资源部类：具体访问者类  
class HRDepartment extends Department {
    //实现人力资源部对全职员工的访问  
    public void visit(FulltimeEmployee employee) {
        int workTime = employee.getWorkTime();
        System.out.println("正式员工" + employee.getName() + "实际工作时间为：" + workTime + "小时。");
        if (workTime > 40) {
            System.out.println("正式员工" + employee.getName() + "加班时间为：" + (workTime - 40) + "小时。");
        } else if (workTime < 40) {
            System.out.println("正式员工" + employee.getName() + "请假时间为：" + (40 - workTime) + "小时。");
        }
    }

    //实现人力资源部对兼职员工的访问  
    public void visit(ParttimeEmployee employee) {
        int workTime = employee.getWorkTime();
        System.out.println("临时工" + employee.getName() + "实际工作时间为：" + workTime + "小时。");
    }
}

//员工列表类：对象结构  
class EmployeeList {
    //定义一个集合用于存储员工对象  
    private ArrayList<Employee> list = new ArrayList<Employee>();

    public void addEmployee(Employee employee) {
        list.add(employee);
    }

    //遍历访问员工集合中的每一个员工对象  
    public void accept(Department handler) {
        for (Object obj : list) {
            ((Employee) obj).accept(handler);
        }
    }
}  