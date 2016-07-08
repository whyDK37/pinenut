package boot.seq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class SeqControllerTest {
    Log logger = LogFactory.getLog(SeqControllerTest.class);
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new SeqController()).build();
    }

//    @Test
    public void getSeq() throws Exception {
        int count = 20000;
        for (int i = 0; i < count; i++) {
            logger.info("do get seq");
            ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/seq")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
//				.andExpect(content().string(equalTo("Greetings from Spring Boot!")));
            System.out.println(resultActions.andReturn().getResponse().getContentAsString());
            TimeUnit.MILLISECONDS.sleep(5);
        }
    }

}