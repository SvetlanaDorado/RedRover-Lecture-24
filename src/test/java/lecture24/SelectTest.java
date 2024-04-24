package lecture24;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.stream.Collectors;

public class SelectTest extends BaseTest {


    void goToSelectPage() {
        getDriver().findElement(By.linkText("selectPage.html")).click();
    }

    @Test
    public void testSimpleDropDown() throws Exception {
        goToSelectPage();
        final WebElement selectWithoutMultiple = getDriver().findElement(By.id("selectWithoutMultiple"));
        Select simpleDropDown = new Select(selectWithoutMultiple);
        Thread.sleep(1000);
        simpleDropDown.selectByValue("two");

        String newValue = selectWithoutMultiple.getAttribute("value");
        System.out.println(newValue);
        Assert.assertEquals("two", newValue);

        Thread.sleep(3000);
    }

    @Test
    public void testMultipleSelect() throws Exception {
        goToSelectPage();
        WebElement selectElement = getDriver().findElement(By.id("selectWithMultipleEqualsMultiple"));
        Select multiSelect = new Select(selectElement);
        multiSelect.selectByIndex(2);
        Thread.sleep(1000);
        multiSelect.deselectByIndex(0);
        Thread.sleep(1000);
        multiSelect.selectByVisibleText("Cheddar");

        System.out.println(selectElement.getAttribute("value"));
        System.out.println(multiSelect.getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toList()));
        Thread.sleep(3000);
    }


    @Test
    void testLongList() throws Exception {
        goToSelectPage();
        WebElement selectElement = getDriver().findElement(By.id("selectWithMultipleLongList"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("five");
        select.selectByVisibleText("six");

        System.out.println(selectElement.getAttribute("value"));
        System.out.println(select.getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toList()));
        Thread.sleep(5000);
    }

}
