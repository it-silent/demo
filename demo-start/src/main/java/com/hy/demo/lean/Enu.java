package com.hy.demo.lean;

/**
 * Enu
 *
 * @author silent
 * @date 2018/4/14
 */
public enum Enu {

    type("1"),

    tex("2"),

    xxx("3");

    private String number;

    Enu(String number) {
        this.number = number;
    }

    public static Enu of(String number) {
        for (Enu enu : Enu.values()) {
            if (number.equals(enu.number)) {
                return enu;
            }
        }
        return null;
    }

    public String ooo() {
        return this.number;
    }
}
