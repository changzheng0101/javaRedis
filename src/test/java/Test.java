import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Date 2023/9/15 10:41
 * @Created by weixiao
 */
public class Test {
    public static void main(String[] args) {
        // bug fix : 不初始化ArrayList无法往对应index添加元素
        List<Integer> data = new ArrayList<>(Collections.nCopies(10, 1));
        data.set(0, 1);
    }
}
