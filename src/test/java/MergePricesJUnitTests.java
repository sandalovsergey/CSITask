import org.junit.jupiter.api.Test;
import ru.crystals.Price;
import ru.crystals.Tools;
import tests.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergePricesJUnitTests {
    Tools tools = new Tools();
    tests.Test test;
    List<Price> res;

    @Test
    public void testCase1() {
        test = new Test1Example();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase2() {
        test = new Test2Example();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase3() {
        test = new Test3Example();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase4() {
        test = new Test4Example();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase5() {
        test = new Test5BothEmpty();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase6() {
        test = new Test6OldEmpty();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase7() {
        test = new Test7NewEmpty();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase8() {
        test = new Test8SimplePosition1();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase9() {
        test = new Test9SimplePosition2();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase10() {
        test = new Test10SimplePosition3();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase11() {
        test = new Test11SimplePosition4();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase12() {
        test = new Test12SimplePosition5();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase13() {
        test = new Test13SimplePosition6();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase14() {
        test = new Test14ONOFullJoin();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }

    @Test
    public void testCase15() {
        test = new Test15NONFullJoin();
        res = tools.joinPriceLists(test.oldPriceList(), test.newPriceList());
        assertEquals(test.answerPriceList(), res);
    }
}
