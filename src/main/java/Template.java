import java.util.Map;

public class Template {
    public static String parse(String template, Map<String, String> variables) {
        if (variables.isEmpty()) {
            return template;
        }

        for (Map.Entry<String, String> pair : variables.entrySet()) {
            template = template.replace("`$" + pair.getKey() + "`", pair.getValue());
        }
        return template;
    }
}
