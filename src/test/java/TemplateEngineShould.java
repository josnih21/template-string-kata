import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateEngineShould {
    /*
        TO-DO
        Move to separated class
        test refactor with Before annotation
        Rename test
     */

    @Test
    public void test() {
        HashMap<String, String> variables = new HashMap<String, String>();
        assertThat(Template.parse("", variables)).isEqualTo("");
        assertThat(Template.parse("hola", variables)).isEqualTo("hola");
    }

    @Test
    public void return_template_with_one_variable_replaced() {
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("user", "Jose");
        assertThat(Template.parse("hola,`$user`", variables)).isEqualTo("hola,Jose");
    }

    @Test
    public void return_template_with_any_variable_replaced() {
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("u", "Jose");
        String template = "hola,`$u`";
        assertThat(Template.parse(template, variables)).isEqualTo("hola,Jose");
    }

    @Test
    public void return_input_template_when_variables_dont_match() {
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("friend", "Pedro");
        String template = "hola,`$user`";
        assertThat(Template.parse(template, variables)).isEqualTo("hola,`$user`");
    }

    @Test
    public void parse_template_with_several_variables() {
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("user", "Carlos");
        variables.put("day", "Lunes");
        String template = "hola `$user`, hoy es `$day`";
        assertThat(Template.parse(template, variables)).isEqualTo("hola Carlos, hoy es Lunes");
    }

}
