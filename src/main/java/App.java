import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Scanner;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request,reponse)-> {
      HashMap model = new HashMap();
      model.put("template","templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/output", (request,reponse)-> {
      HashMap model = new HashMap();


      String allergyInput = request.queryParams("allergyInput");
      Integer allergyInputInt = Integer.parseInt(allergyInput);
      String allergyList = App.AllergyCount(allergyInputInt);


      model.put("result", allergyList);
      model.put("template","templates/output.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

  public static String AllergyCount(int allergyNumber) {
    int[] allergyScore = {128, 64, 32, 16, 8, 4, 2, 1};

    int[] list = new int[allergyScore.length];

    if (allergyNumber == 256) {
      for (int i=0; i < allergyScore.length; i++) {
        list[i] = 1;
      }
    } else {
      for (int i=0; i < allergyScore.length; i++) {
        while (allergyNumber >= allergyScore[i]) {
          list[i]++;
          allergyNumber -= allergyScore[i];
        }
    }
  }
  return "Cats: " + list[0] + "<br>" + "Pollen: " + list[1] + "<br>" +  "Chocolate: " + list[2] + "<br>" + "Tomatoes: " + list[3] + "<br>" + "Strawberries: " + list[4] + "<br>" + "Shellfish: " + list[5] + "<br>" + "Peanuts: " + list[6] + "<br>" + "Eggs: " + list[7];
}
}
