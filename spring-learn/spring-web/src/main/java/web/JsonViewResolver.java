package web;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * View resolver for returning JSON in a view-based system. Always returns a
 * {@link MappingJackson2JsonView}.
 */
public class JsonViewResolver implements ViewResolver {

    /**
     * Get the view to use.
     *
     * @return Always returns an instance of {@link MappingJackson2JsonView}.
     */
    @Override
    public View resolveViewName(String viewName, Locale locale)
            throws Exception {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        return view;
    }

}
