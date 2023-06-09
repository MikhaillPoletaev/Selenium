import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallBackTest {
        private WebDriver driver;
        @BeforeAll
        static void setUpAll() {
            WebDriverManager.chromedriver().setup();

        }

        @BeforeEach
        void setUp() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            driver.get("http://localhost:9999/");
        }
        @AfterEach
        void tearDown() {
            driver.quit();
            driver = null;
        }
        @Test
        void shouldTestSomething() {
            driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Гагарин Юрий");
            driver.findElement(By.cssSelector("[data-test-id=phone] input" )).sendKeys("+79775558822");
            driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
            driver.findElement(By.cssSelector("button.button")).click();
            driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
            var actualText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
            assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);

        }

    }
