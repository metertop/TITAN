package com;

import java.util.List;

/**
 * Created by haoyuexun on 2018/3/21.
 */
public class Apple {

    private int weight;

    private String color;

    private String catalog;

    public Apple(int weight, String color, String catalog) {
        this.weight = weight;
        this.color = color;
        this.catalog = catalog;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }


    //以颜色和品类决定两个苹果是否一样
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apple apple = (Apple) o;

        if (color != null ? !color.equals(apple.color) : apple.color != null) return false;
        return catalog != null ? catalog.equals(apple.catalog) : apple.catalog == null;

    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (catalog != null ? catalog.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "重量:"+weight+",颜色:"+color+",品类:"+catalog;
    }
}

