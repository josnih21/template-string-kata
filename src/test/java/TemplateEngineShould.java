import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateEngineShould {
    /*

    "`$una`,`$dos`,`$mil`" -> "una" = a, "dos" = b, "mil" = 1000 -> "a,b,100"

    public String parse(String template,List<String> variables)
     */

    private String parse(String template, Map<String, String> variables) {
        if (variables.isEmpty()) {
            return template;
        }

        for (Map.Entry<String, String> pair : variables.entrySet()) {
            template = template.replace("`$" + pair.getKey() + "`", pair.getValue());
        }
        return template;
    }

    @Test
    public void test() {
        HashMap<String, String> variables = new HashMap<String, String>();
        assertThat(parse("", variables)).isEqualTo("");
        assertThat(parse("hola", variables)).isEqualTo("hola");
    }

    @Test
    public void return_template_with_one_variable_replaced() {
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("user", "Jose");
        assertThat(parse("hola,`$user`", variables)).isEqualTo("hola,Jose");
    }

    @Test
    public void return_template_with_any_variable_replaced() {
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("u", "Jose");
        String template = "hola,`$u`";
        assertThat(parse(template, variables)).isEqualTo("hola,Jose");
    }

    @Test
    public void return_input_template_when_variables_dont_match() {
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("friend", "Pedro");
        String template = "hola,`$user`";
        assertThat(parse(template, variables)).isEqualTo("hola,`$user`");
    }

    @Test
    public void parse_template_with_several_variables() {
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("user", "Carlos");
        variables.put("day", "Lunes");
        String template = "hola `$user`, hoy es `$day`";
        assertThat(parse(template, variables)).isEqualTo("hola Carlos, hoy es Lunes");
    }

}
