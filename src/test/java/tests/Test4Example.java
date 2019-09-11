package tests;

import ru.crystals.Price;

import java.util.ArrayList;
import java.util.List;

import static ru.crystals.Tools.date;

public class Test4Example implements Test {
    @Override
    public List<Price> oldPriceList() {
        List<Price> oldPriceList = new ArrayList<>();
        oldPriceList.add(new Price("122856", 1, 1, date("01.01.2019 00:00:00"), date("01.02.2019 00:00:00"), 80));
        oldPriceList.add(new Price("122856", 1, 1, date("01.02.2019 00:00:00"), date("01.03.2019 00:00:00"), 87));
        oldPriceList.add(new Price("122856", 1, 1, date("01.03.2019 00:00:00"), date("01.04.2019 00:00:00"), 90));

        return oldPriceList;
    }

    @Override
    public List<Price> newPriceList() {
        List<Price> newPriceList = new ArrayList<>();
        newPriceList.add(new Price("122856", 1, 1, date("20.01.2019 00:00:00"), date("20.02.2019 00:00:00"), 80));
        newPriceList.add(new Price("122856", 1, 1, date("20.02.2019 00:00:00"), date("20.03.2019 00:00:00"), 85));

        return newPriceList;
    }

    @Override
    public List<Price> answerPriceList() {
        List<Price> answerPriceList = new ArrayList<>();
        answerPriceList.add(new Price("122856", 1, 1, date("01.01.2019 00:00:00"), date("20.02.2019 00:00:00"), 80));
        answerPriceList.add(new Price("122856", 1, 1, date("20.02.2019 00:00:00"), date("20.03.2019 00:00:00"), 85));
        answerPriceList.add(new Price("122856", 1, 1, date("20.03.2019 00:00:00"), date("01.04.2019 00:00:00"), 90));

        return answerPriceList;
    }
}
