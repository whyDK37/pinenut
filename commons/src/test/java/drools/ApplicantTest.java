package drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ApplicantTest {

    @Test
    public void testIsValid() {
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();

//        KieSession kieSession = kieContainer.newKieSession();
//        SessionConfigurationImpl conf = new SessionConfigurationImpl();
//        StatelessKieSession kSession = kieContainer.newStatelessKieSession();
        Applicant applicant = new Applicant("Mr John Smith", 20);

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession1");
        kSession.insert(applicant);
        kSession.fireAllRules();

//        Assert.assertFalse(applicant.isValid());
//        kSession.execute(applicant);
//        Assert.assertTrue(applicant.isValid());
    }
}