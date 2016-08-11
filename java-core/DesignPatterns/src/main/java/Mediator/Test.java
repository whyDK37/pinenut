/**
 *  A test client
 */
public class Test  {
    public static void main(String[] args) {
        Mediator myMed = new ConcreteMediator();
        ColleagueA a = new ColleagueA(myMed);
        ColleagueB b = new ColleagueB(myMed);
        ColleagueC c = new ColleagueC(myMed);

        a.Change();
        b.Change();
        c.Change();
    }
}