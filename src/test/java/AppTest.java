import org.junit.*;
import static org.junit.Assert.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import java.util.Arrays;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Allergy");
  }

  @Test
  public void AllergyCount() {
    goTo("http://localhost:4567/");
    fill("#allergyInput").with("128");
    submit(".btn");
    assertThat(pageSource()).contains("Cats: 1");
  }

  @Test
  public void AllergyCount_returnQuantityOfCoins_89() {
    App testAllergy= new App();
    String result = "Cats: 0" + "<br>" + "Pollen: 1"  + "<br>" +  "Chocolate: 0"  + "<br>" + "Tomatoes: 1"  + "<br>" + "Strawberries: 1" + "<br>" + "Shellfish: 0"  + "<br>" + "Peanuts: 0" +  "<br>" + "Eggs: 1";
    assertEquals(result, testAllergy.AllergyCount(89));
  }

  @Test
  public void AllergyCount_returnNumberOfEachCoinFromUser_128() {
    App testAllergy = new App();
    String result = "Cats: 1" + "<br>" + "Pollen: 0"  + "<br>" +  "Chocolate: 0"  + "<br>" + "Tomatoes: 0"  + "<br>" + "Strawberries: 0" + "<br>" + "Shellfish: 0"  + "<br>" + "Peanuts: 0" +  "<br>" + "Eggs: 0";
    assertEquals(result, testAllergy.AllergyCount(128));
  }

  @Test
  public void AllergyCount_returnNumberFromUser_256() {
    App testAllergy = new App();
  String result = "Cats: 1" + "<br>" + "Pollen: 1"  + "<br>" +  "Chocolate: 1"  + "<br>" + "Tomatoes: 1"  + "<br>" + "Strawberries: 1" + "<br>" + "Shellfish: 1"  + "<br>" + "Peanuts: 1" +  "<br>" + "Eggs: 1";
    assertEquals(result, testAllergy.AllergyCount(256));
  }

}
