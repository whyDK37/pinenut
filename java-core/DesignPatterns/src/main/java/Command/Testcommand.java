/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: The9.com</p>
 * @author Jerry Shen
 * @version 0.5
 */

public class TestCommand {

    public TestCommand() {
    }
    public static void main(String[] args) {
           Light testLight = new Light();
           LightOnCommand testLOC = new LightOnCommand(testLight);
           LightOffCommand testLFC = new LightOffCommand(testLight);
           Switch testSwitch = new Switch(testLOC, testLFC);
           testSwitch.flipUp( );
           testSwitch.flipDown( );
           Fan testFan = new Fan( );
           FanOnCommand foc = new FanOnCommand(testFan);
           FanOffCommand ffc = new FanOffCommand(testFan);
           Switch ts = new Switch( foc,ffc);
           ts.flipUp( );
           ts.flipDown( );
    }
}

    //----------  Class Fan  ------------------------------
    class Fan {
          public void startRotate() {
                 System.out.println("Fan is Rotating now !");
          }
          public void stopRotate() {
                 System.out.println("Fan is stop now !");
          }
    }
    //-----------------------------------------------------
    //----------  Class Light  ----------------------------
    class Light {
          public void turnOn() {
                 System.out.println("Light is on now !");
          }
          public void turnOff() {
                 System.out.println("Light is off now !");
          }
    }
    //-----------------------------------------------------
    //----------  Class Switch  ---------------------------
    class Switch {
          private Command UpCommand, DownCommand;
          public Switch (Command Up, Command Down) {
                 UpCommand = Up;
                 DownCommand = Down;
          }
          void flipUp() {
               UpCommand.execute();
          }
          void flipDown() {
               DownCommand.execute();
          }
    }
    //-----------------------------------------------------
    //----------  Class LightOnCommand  -------------------
    class LightOnCommand implements Command {
        private Light myLight;
        public LightOnCommand (Light L) {
               myLight = L;
        }
        public void execute() {
               myLight.turnOn();
        }
    }
    //-----------------------------------------------------
    //----------  Class LightOffCommand  ------------------
    class LightOffCommand implements Command {
        private Light myLight;
        public LightOffCommand (Light L) {
               myLight = L;
        }
        public void execute() {
               myLight.turnOff();
        }
    }
    //-----------------------------------------------------
    //----------  Class FanOnCommand  ---------------------
    class FanOnCommand implements Command {
          private Fan myFan;
          public FanOnCommand (Fan F) {
                 myFan = F;
          }
          public void execute() {
                 myFan.startRotate();
          }
    }
    //-----------------------------------------------------
    //----------  Class FanOffCommand  ---------------------
    class FanOffCommand implements Command {
          private Fan myFan;
          public FanOffCommand (Fan F) {
                 myFan = F;
          }
          public void execute() {
                 myFan.stopRotate();
          }
    }
    //-----------------------------------------------------
