package tests;

import ru.crystals.Price;

import java.util.ArrayList;
import java.util.List;

import static ru.crystals.Tools.date;

public class Test1Example implements Test {
    @Override
    public List<Price> oldPriceList() {
        List<Price> oldPriceList = new ArrayList<>();
        oldPriceList.add(new Price("122856", 1, 1, date("01.01.2013 00:00:00"), date("31.01.2013 23:59:59"), 11000));
        oldPriceList.add(new Price("122856", 2, 1, date("10.01.2013 00:00:00"), date("20.01.2013 23:59:59"), 99000));
        oldPriceList.add(new Price("6654", 1, 2, date("01.01.2013 00:00:00"), date("31.01.2013 00:00:00"), 5000));

        return oldPriceList;
    }

    @Override
    public List<Price> newPriceList() {
        List<Price> newPriceList = new ArrayList<>();
        newPriceList.add(new Price("122856", 1, 1, date("20.01.2013 00:00:00"), date("20.02.2013 23:59:59"), 11000));
        newPriceList.add(new Price("122856", 2, 1, date("15.01.2013 00:00:00"), date("25.01.2013 23:59:59"), 92000));
        newPriceList.add(new Price("6654", 1, 2, date("12.01.2013 00:00:00"), date("13.01.2013 00:00:00"), 4000));

        return newPriceList;
    }

    @Override
    public List<Price> answerPriceList() {
        List<Price> answerPriceList = new ArrayList<>();
        answerPriceList.add(new Price("122856", 1, 1, date("01.01.2013 00:00:00"), date("20.02.2013 23:59:59"), 11000));
        answerPriceList.add(new Price("122856", 2, 1, date("10.01.2013 00:00:00"), date("15.01.2013 00:00:00"), 99000));
        answerPriceList.add(new Price("122856", 2, 1, date("15.01.2013 00:00:00"), date("25.01.2013 23:59:59"), 92000));
        answerPriceList.add(new Price("6654", 1, 2, date("01.01.2013 00:00:00"), date("12.01.2013 00:00:00"), 5000));
        answerPriceList.add(new Price("6654", 1, 2, date("12.01.2013 00:00:00"), date("13.01.2013 00:00:00"), 4000));
        answerPriceList.add(new Price("6654", 1, 2, date("13.01.2013 00:00:00"), date("31.01.2013 00:00:00"), 5000));

        return answerPriceList;
    }
}
