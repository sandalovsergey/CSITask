package ru.crystals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Tools {
    final private static String datePattern = "dd.MM.yyyy HH:mm:ss";
    final static SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

    public static Date date(String s) {
        Date date = null;
        try {
            date = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Метод выделять одну уникальную цену из общего списка цен, начиная с индекса beginIndex
     * Используется то, что данные предварительно отсортированы.
     * @param l
     * @param beginIndex
     * @return
     */

    private List<Price> getCluster(final List<Price> l, int beginIndex) {
        if (l.size() == 0) {
            return null;
        }
        Price curPrice = l.get(beginIndex);
        Price temp = l.get(beginIndex);
        List<Price> curList = new ArrayList<>();

        while (Price.getTimelineComparator().compare(curPrice, temp) == 0) {
            curList.add(temp);
            ++beginIndex;
            if (beginIndex < l.size()) {
                temp = l.get(beginIndex);
            } else {
                return curList;
            }
        }
        return curList;
    }

    /**
     * Метод кластеризует коллекцию имеющихся цен на уникальные
     * Уникальная цена(Cluster) -- это объединение цен с фиксированным productCode, Number, Department.
     * Уникальная цена может состоять из нескольких цен, поэтому представляется как список Price
     * @param list
     * @return Список уникальных цен
     */

    private List<List<Price>> getClusters(final List<Price> list) {
        list.sort(Price.getUniquePriceComparator());

        int beginIndex = 0;
        final List<List<Price>> res = new ArrayList<>();
        while (beginIndex < list.size()) {
            List<Price> cluster = getCluster(list, beginIndex);
            res.add(cluster);
            beginIndex += cluster.size();
        }

        return res;
    }

    private boolean isMatching(final List<Price> leftCluster, final List<Price> rightCluster) {
        final Price left = leftCluster.get(0);
        final Price right = rightCluster.get(0);

        return Price.getTimelineComparator().compare(left, right) == 0;
    }

    /**
     * Метод объединяет старую и новую уникальные цены
     * Идея объединения: новые цены полностью войдут в результат. Записываем их в ответ и итерируем по старым ценам.
     * В зависимости от того, есть ли свободное место на "временной линии" дописываем в ответ. Если value старой и новой цены
     * совпадают, то объединяем цены
     * @param oldPriceCLuster
     * @param newPriceCluster
     * @return
     */

    private List<Price> mergeClusters(List<Price> oldPriceCLuster, List<Price> newPriceCluster) {
        ArrayList<Price> res = new ArrayList<>(newPriceCluster);
        Deque<Price> oldPricesDeque = new ArrayDeque<>(oldPriceCLuster);
        int newIndex = 0; //на элемент с этим индексом смотрит newPrice
        Price oldPrice;
        Price newPrice;

        while (!oldPricesDeque.isEmpty()) { //начинаем вставку старых цен. Некоторые из новых могут подрасти в длительности
            //поддерживаем инвариант: пропускаем новые цены, если они полностью слева от текущей старой
            oldPrice = oldPricesDeque.remove(); // забираем из очереди на обработку
            while (newIndex < res.size() && res.get(newIndex).isFullLeft(oldPrice)) {
                ++newIndex;
            }
            //могло получиться так, что пропустили все новые цены. Тогда старые просто дописываем в конец к res, не забываем дописать ту, что в обработке (oldPrice)
            if (newIndex == res.size()) {
                newPrice = res.get(newIndex - 1);
                Price.addToEnd(newPrice, oldPrice, res);
                while (!oldPricesDeque.isEmpty()) {
                    Price elem = oldPricesDeque.remove();
                    Price last = res.get(res.size() - 1);
                    Price.addToEnd(last, elem, res);
                }
                return res;
            }

            newPrice = res.get(newIndex);
            //к этому моменту  может остаться 5 вариантов: старая цена "не полностью справа" от новой
            //разбираем случаи

            if (oldPrice.getEnd().getTime() > newPrice.getEnd().getTime()) { //у старой цены выставился хвост вправо
                //отрезаем хвост справа и пихаем в очередь. Новый кусок для дальнейших новых частей
                Price pRightTail = oldPrice.copy();
                pRightTail.setBegin(newPrice.getEnd());
                oldPricesDeque.addFirst(pRightTail);
            }

            Price pLeftTail = null;
            if (oldPrice.getBegin().getTime() < newPrice.getBegin().getTime()) { // у старой цены хвост выставился влево      )б
                pLeftTail = oldPrice.copy();
                if (oldPrice.getEnd().getTime() > newPrice.getBegin().getTime()) { //старая цена полностью слева от новой
                    pLeftTail.setEnd(newPrice.getBegin());
                }
            }

            if (pLeftTail != null) {
                if (pLeftTail.isSameValAndRight(newPrice)) {
                    res.get(newIndex).mergeLeft(pLeftTail);
                    if (newIndex > 0 && res.get(newIndex - 1).isSameValAndRight(res.get(newIndex))) {
                        res.get(newIndex ).mergeLeft(res.get(newIndex - 1));
                        res.remove(--newIndex);
                    }
                } else {
                    res.add(newIndex, pLeftTail);
                    newIndex++;
                }
            }
        }

        return res;
    }

    /**
     * Метод сливает старые и новые цены по правилам, указанным в ТЗ
     * Работаем с копиями данных, чтобы не изменить входные
     * @param oldPriceList
     * @param newPriceList
     * @return Результат слияния цен
     */

    public List<Price> joinPriceLists(final List<Price> oldPriceList, final List<Price> newPriceList) {
        if (newPriceList.size() == 0) {
            return oldPriceList;
        }

        final List<Price> res = new ArrayList<>(oldPriceList.size());
        final List<Price> oldPrices = new ArrayList<>(oldPriceList);
        final List<Price> newPrices = new ArrayList<>(newPriceList);

        final List<List<Price>> oldClusters = getClusters(oldPrices);
        final List<List<Price>> newClusters = getClusters(newPrices);

        Iterator<List<Price>> newIter = newClusters.iterator();
        while (newIter.hasNext()) {
            List<Price> newCluster = newIter.next();
            Iterator<List<Price>> oldIter = oldClusters.iterator();
            boolean rightMatch = false;
            while (oldIter.hasNext()) {
                List<Price> oldCluster = oldIter.next();
                if (isMatching(oldCluster, newCluster)) {
                    res.addAll(mergeClusters(oldCluster, newCluster));
                    rightMatch = true;
                }
            }
            if (!rightMatch) {
                res.addAll(newCluster);
            }
        }
        return res;
    }
}