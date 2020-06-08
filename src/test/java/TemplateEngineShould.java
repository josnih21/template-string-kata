import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateEngineShould {
    /*
    "" -> ""
    "hola" -> "hola"
    "hola", da igual las variables -> "hola"
    "hola,`$user`, user = Jose
    "hola,`$user`, friend = Pedro -> "hola,`$user`
    "hola `$user`, hoy es `$day`", "user" = "carlos", "day" = "lunes" -> "hola carlos, hoy es lunes"
    "`$una`,`$dos`,`$mil`" -> "una" = a, "dos" = b, "mil" = 1000 -> "a,b,100"

    public String parse(String template,List<String> variables)
     */

    private String parse(String template, Map<String,String> variables){
        if(variables.isEmpty()){
        return template;
        }

        String key = template.substring(template.indexOf("`$")+2,template.lastIndexOf("`"));
        String value = variables.get(key);
        template = template.replace("`$"+key+"`", value);
        return template;
    }

    @Test
    public void test (){
        HashMap<String,String> variables = new HashMap<String,String>();
        assertThat(parse("",variables)).isEqualTo("");
        assertThat(parse("hola",variables)).isEqualTo("hola");
    }

    @Test
    public void return_template_with_one_variable_replaced(){
        HashMap<String,String> variables = new HashMap<String,String>();
        variables.put("user","Jose");
        assertThat(parse("hola,`$user`",variables)).isEqualTo("hola,Jose");
    }

    @Test
    public void return_template_with_any_variable_replaced(){
        HashMap<String,String> variables = new HashMap<String,String>();
        variables.put("u","Jose");
        String template = "hola,`$u`";
        assertThat(parse(template,variables)).isEqualTo("hola,Jose");
    }
}
