package ru.crystals;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Price {
    private String productCode;
    private int number;
    private int depart;
    private Date begin;
    private Date end;
    private long value;

    public String getProductCode() {
        return productCode;
    }

    public int getNumber() {
        return number;
    }

    public int getDepart() {
        return depart;
    }

    public Price(String productCode, int number, int depart, Date begin, Date end, long value) {
        this.productCode = productCode;
        this.number = number;
        this.depart = depart;
        this.begin = begin;
        this.end = end;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return number == price.number &&
                depart == price.depart &&
                value == price.value &&
                productCode.equals(price.productCode) &&
                begin.equals(price.begin) &&
                end.equals(price.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, number, depart, begin, end, value);
    }

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }

    public long getValue() {
        return value;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return " productCode=" + productCode +
                " number=" + number +
                " depart=" + depart +
                " begin=" + Tools.dateFormat.format(begin) +
                " end=" + Tools.dateFormat.format(end) +
                " value=" + value;
    }

    private static Comparator<Price> uniquePriceComparator = (first, second) -> {
        int n = Integer.parseInt(second.getProductCode()) - Integer.parseInt(first.getProductCode());
        if (n == 0) {
            int m = first.getDepart() - second.getDepart();
            if (m == 0) {
                int k = first.getNumber() - second.getNumber();
                if (k == 0) {
                    long beginDif = first.getBegin().getTime() - second.getBegin().getTime();
                    return beginDif == 0 ? 0 : (beginDif > 0 ? 1 : -1);
                } else {
                    return k;
                }
            } else {
                return m;
            }
        } else {
            return n;
        }
    };

    public static Comparator<Price> getUniquePriceComparator() {
        return uniquePriceComparator;
    }

    private static Comparator<Price> timelineComparator = (first, second) -> {
        int n = Integer.parseInt(first.getProductCode()) - Integer.parseInt(second.getProductCode());
        if (n == 0) {
            int m = first.getDepart() - second.getDepart();
            return m == 0 ? first.getNumber() - second.getNumber() : m;
        } else {
            return n;
        }
    };

    public static Comparator<Price> getTimelineComparator() {
        return timelineComparator;
    }

    public boolean isFullLeft(Price p) {
        return this.getEnd().getTime() <= p.getBegin().getTime() ? true : false;
    }

    public Price copy() {
        return new Price(getProductCode(), getNumber(), getDepart(), new Date(getBegin().getTime()), new Date(getEnd().getTime()),
                getValue());
    }

    public void mergeRight(Price rightPrice) {
        setEnd(rightPrice.end);
    }

    public void mergeLeft(Price leftPrice) {
        setBegin(leftPrice.getBegin());
    }

    public boolean isSameValAndRight(Price p) {
        return getValue() == p.getValue() && getEnd().getTime() == p.getBegin().getTime();
    }

    public static void addToEnd(Price last, Price elem, List<Price> res) {
        if (last.isSameValAndRight(elem)) {
            last.mergeRight(elem);
        } else {
            res.add(elem);
        }
    }
}