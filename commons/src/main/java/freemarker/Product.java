package freemarker;

public class Product {

    private String url;
    private String name;

    public Product(String url, String name) {
        this.url = url;
        this.name = name;
    }

    // As per the JavaBeans spec., this defines the "url" framework.bean property
    public String getUrl() {
        return url;
    }

    // As per the JavaBean spec., this defines the "name" framework.bean property
    public String getName() {
        return name;
    }

}