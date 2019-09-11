package tests;

import ru.crystals.Price;
import java.util.List;

public interface Test {
    public List<Price> oldPriceList();
    public List<Price> newPriceList();
    public List<Price> answerPriceList();
}
