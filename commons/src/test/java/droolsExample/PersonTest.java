//package droolsExample;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//
//import org.drools.compiler.compiler.DroolsParserException;
//import org.drools.compiler.compiler.PackageBuilder;
//import org.drools.core.RuleBase;
//import org.drools.core.RuleBaseFactory;
//import org.drools.core.WorkingMemory;
//
//import static org.testng.Assert.*;
//
//public class PersonTest {
//
//    public static void main(String[] args) throws DroolsParserException,
//            IOException {
//        PersonTest droolsTest = new PersonTest();
//        droolsTest.executeDrools();
//    }
//
//    public void executeDrools() throws DroolsParserException, IOException {
//        PackageBuilder packageBuilder = new PackageBuilder();
//
//        String ruleFile = "/com/rules/droolsRule.drl";
//        InputStream resourceAsStream = getClass().getResourceAsStream(ruleFile);
//        Reader reader = new InputStreamReader(resourceAsStream);
//        packageBuilder.addPackageFromDrl(reader);
//        org.drools.core.rule.Package rulesPackage = packageBuilder.getPackage();
//        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
//        ruleBase.addPackage(rulesPackage);
//        WorkingMemory workingMemory = ruleBase.newStatefulSession();
//        Person person = new Person();
//        person.setName("Shamik Mitra");
//        person.setTime(7);
//        workingMemory.insert(person);
//        workingMemory.fireAllRules();
//        System.out.println(person.getGreet());
//    }
//}