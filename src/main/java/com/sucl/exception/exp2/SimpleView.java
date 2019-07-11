package com.sucl.exception.exp2;

import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author sucl
 * @since 2019/7/10
 */
public class SimpleView implements View {
    private String template;

    public SimpleView(String template){
        this.template = template;
    }

    @Override
    public String getContentType() {
        return "text/html;charset=utf-8";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (response.getContentType() == null) {
            response.setContentType(getContentType());
        }
        Map<String, Object> map = new HashMap<String, Object>(model);
        map.put("path", request.getContextPath());
        ViewPlaceholderHelper helper = new ViewPlaceholderHelper("${","}");

        PlaceholderResolver resolver = new ViewPlaceholderResolver( map);

        String result = helper.replacePlaceholders(template,resolver);

        response.getWriter().append(result);
    }

    private class ViewPlaceholderHelper extends PropertyPlaceholderHelper{

        public ViewPlaceholderHelper(String placeholderPrefix, String placeholderSuffix) {
            super(placeholderPrefix, placeholderSuffix);
        }

        public String parseStringValue(String template,PlaceholderResolver resolver, Set<String> variables){
            return super.parseStringValue(template,new NonRecursivePlaceholderResolver(resolver),variables);
        }
    }

    private class ViewPlaceholderResolver implements PlaceholderResolver {
        private Map<String, Object> variables;

        public ViewPlaceholderResolver(Map<String, Object> variables){
            this.variables = variables;
        }

        @Override
        public String resolvePlaceholder(String placeholderName) {
            return Objects.toString(variables.get(placeholderName));
        }
    }

    private static class NonRecursivePlaceholderResolver implements PlaceholderResolver {

        private final PlaceholderResolver resolver;

        NonRecursivePlaceholderResolver(PlaceholderResolver resolver) {
            this.resolver = resolver;
        }

        @Override
        public String resolvePlaceholder(String placeholderName) {
            if (this.resolver instanceof NonRecursivePlaceholderResolver) {
                return null;
            }
            return this.resolver.resolvePlaceholder(placeholderName);
        }

    }
}
