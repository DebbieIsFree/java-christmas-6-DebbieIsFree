package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    // type, menu, amount
    static Map<String, Integer> menu = new HashMap<>();

    public Menu() {
        menu.put("양송이수프", 6000);
        menu.put("타파스", 5500);
        menu.put("시저샐러드", 8000);

        menu.put("티본스테이크", 55000);
        menu.put("바비큐립", 54000);
        menu.put("해산물파스타", 35000);
        menu.put("크리스마스파스타", 25000);

        menu.put("초코케이크", 15000);
        menu.put("아이스크림", 5000);

        menu.put("제로콜라", 3000);
        menu.put("레드와인", 60000);
        menu.put("샴페인", 25000);
    }

    public static Map<String, Integer> getMenu() {
        return menu;
    }
}
