import org.junit.jupiter.api.DisplayName;
import org.weixiao.Main;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Date 2023/9/15 10:41
 * @Created by weixiao
 */
public class BaseCommandTest {

    @DisplayName("Test MessageService.get()")
    @org.junit.jupiter.api.Test
    void testGetCommand() {
        assertEquals("test", "test");
    }
}
