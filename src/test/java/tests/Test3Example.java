package tests;

import ru.crystals.Price;

import java.util.ArrayList;
import java.util.List;

import static ru.crystals.Tools.date;

public class Test3Example implements Test {
    @Override
    public List<Price> oldPriceList() {
        List<Price> oldPriceList = new ArrayList<>();
        oldPriceList.add(new Price("122856", 1, 1, date("01.05.2019 00:20:00"), date("20.05.2019 18:10:45"), 100));
        oldPriceList.add(new Price("122856", 1, 1, date("20.05.2019 18:10:45"), date("25.06.2019 19:21:00"), 120));

        return oldPriceList;
    }

    @Override
    public List<Price> newPriceList() {
        List<Price> newPriceList = new ArrayList<>();
        newPriceList.add(new Price("122856", 1, 1, date("15.05.2019 12:12:45"), date("15.06.2019 18:15:00"), 110));

        return newPriceList;
    }

    @Override
    public List<Price> answerPriceList() {
        List<Price> answerPriceList = new ArrayList<>();
        answerPriceList.add(new Price( "122856", 1, 1, date("01.05.2019 00:20:00"), date("15.05.2019 12:12:45"), 100));
        answerPriceList.add(new Price( "122856", 1, 1, date("15.05.2019 12:12:45"), date("15.06.2019 18:15:00"), 110));
        answerPriceList.add(new Price( "122856", 1, 1, date("15.06.2019 18:15:00"), date("25.06.2019 19:21:00"), 120));

        return answerPriceList;
    }
}
