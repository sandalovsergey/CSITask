package tests;

import ru.crystals.Price;

import java.util.ArrayList;
import java.util.List;

import static ru.crystals.Tools.date;

public class Test14ONOFullJoin implements Test {
    @Override
    public List<Price> oldPriceList() {
        List<Price> oldPriceList = new ArrayList<>();
        oldPriceList.add(new Price("122856", 1, 1, date("01.01.2018 00:12:45"), date("01.01.2019 23:40:59"), 50));
        oldPriceList.add(new Price("122856", 1, 1, date("01.01.2020 00:12:45"), date("01.01.2021 23:40:59"), 50));

        oldPriceList.add(new Price("122856", 1, 2, date("01.01.2018 00:12:45"), date("01.01.2019 23:40:59"), 100));
        oldPriceList.add(new Price("122856", 1, 2, date("01.01.2020 00:12:45"), date("01.01.2021 23:40:59"), 100));


        return oldPriceList;
    }

    @Override
    public List<Price> newPriceList() {
        List<Price> newPriceList = new ArrayList<>();
        newPriceList.add(new Price("122856", 1, 1, date("01.01.2019 23:40:59"), date("01.01.2020 00:12:45"), 60));
        newPriceList.add(new Price("122856", 1, 2, date("01.01.2019 23:40:59"), date("01.01.2020 00:12:45"), 100));

        return newPriceList;
    }

    @Override
    public List<Price> answerPriceList() {
        List<Price> answerPriceList = new ArrayList<>();
        answerPriceList.add(new Price("122856", 1, 1, date("01.01.2018 00:12:45"), date("01.01.2019 23:40:59"), 50));
        answerPriceList.add(new Price("122856", 1, 1, date("01.01.2019 23:40:59"), date("01.01.2020 00:12:45"), 60));
        answerPriceList.add(new Price("122856", 1, 1, date("01.01.2020 00:12:45"), date("01.01.2021 23:40:59"), 50));
        answerPriceList.add(new Price("122856", 1, 2, date("01.01.2018 00:12:45"), date("01.01.2021 23:40:59"), 100));

        return answerPriceList;
    }
}
