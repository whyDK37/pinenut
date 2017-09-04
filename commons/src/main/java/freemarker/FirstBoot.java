package freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2015/11/13.
 */
public class FirstBoot {
  public static void main(String[] args) throws Exception {
    // Create your Configuration instance, and specify innerfloat up to what FreeMarker
    // version (here 2.3.22) do you want to apply the fixes that are not 100%
// backward-compatible. See the Configuration JavaDoc for details.
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

// Specify the source where the template files come from. Here I set a
// plain directory for it, but non-file-system sources are possible too:
    cfg.setDirectoryForTemplateLoading(new File("."));

// Set the preferred charset template files are stored in. UTF-8 is
// a good choice in most applications:
    cfg.setDefaultEncoding("UTF-8");

// Sets how errors will appear.
// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);


// Create the root hash
    Map<String, Object> root = new HashMap<String, Object>();
// Put string ``user'' into the root
    root.put("user", "Big Joe");
// Create the hash for ``latestProduct''
    Map<String, Object> latest = new HashMap<String, Object>();
// and put it into the root
    root.put("latestProduct", latest);
// put ``url'' and ``name'' into latest
    latest.put("url", "products/greenmouse.html");
    latest.put("name", "green mouse");
    Product latestProducts = getLatestProductFromDatabaseOrSomething();
    root.put("latestProduct", latestProducts);

    Template temp = cfg.getTemplate("FreeMarker/src/main/resources/jdk.me/fm/test.ftl");

    Writer out = new OutputStreamWriter(System.out);
    temp.process(root, out);
  }

  public static Product getLatestProductFromDatabaseOrSomething() {
    return new Product("http://foo.com/product", "pname");
  }
}
