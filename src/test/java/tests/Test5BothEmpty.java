package tests;

import ru.crystals.Price;

import java.util.ArrayList;
import java.util.List;

import static ru.crystals.Tools.date;

public class Test5BothEmpty implements Test {
    @Override
    public List<Price> oldPriceList() {
        List<Price> oldPriceList = new ArrayList<>();

        return oldPriceList;
    }

    @Override
    public List<Price> newPriceList() {
        List<Price> newPriceList = new ArrayList<>();

        return newPriceList;
    }

    @Override
    public List<Price> answerPriceList() {
        List<Price> answerPriceList = new ArrayList<>();

        return answerPriceList;
    }
}
