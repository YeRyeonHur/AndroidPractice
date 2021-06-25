package com.kyh.knucommunity.model;

public class RepositoryUserDataView {
    private static String index;
    private static String name;
    private static String tel;

    public static String getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        RepositoryUserDataView.index = Integer.toString(index);
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        RepositoryUserDataView.name = name;
    }

    public static String getTel() {
        return tel;
    }

    public static void setTel(String tel) {
        RepositoryUserDataView.tel = tel;
    }
}
