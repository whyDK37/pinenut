package c3;

import c0.ShowMe;

/**
 * Created by drug on 2016/3/18.
 */
public abstract class ShowMeTest {
    public void showMe(){
        this.getBean().showMe();
    }

    public abstract ShowMe getBean();
}
