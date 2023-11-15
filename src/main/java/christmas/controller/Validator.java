package christmas.controller;

import christmas.domain.Menu;

import java.util.*;

public class Validator {

    public static void validateVisitDay(String visitDay){
        String regex = "^[0-9]+$";
        if (!visitDay.matches(regex)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }

        int day = Integer.parseInt(visitDay);
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateMenuAndAmount(String input) {
        Map<String, String> menuAndAmount = MenuController.getMenuAndAmount(input);
        isValidOrderFormat(input);
        isValidAmount(input);
        isValidMenu(menuAndAmount);
        isDuplicatedMenu(input);
    }

    public static void isValidMenu(Map<String, String> menuAndAmount) {
        Set<String> orderedMenus = menuAndAmount.keySet();
        Menu menuInstance = new Menu();
        Set<String> menuList = menuInstance.getMenu().keySet();
        for(String menu : orderedMenus){
            if(!menuList.contains(menu))
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void isValidAmount(String input) {
        String regex = "^[0-9]+$";

        String[] parsedStrings = input.split(",");
        for(String str : parsedStrings){
            String[] parsedMenuAndAmount = str.split("-");
            if(parsedMenuAndAmount.length != 2) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            String amountStr = parsedMenuAndAmount[1];
            if (!amountStr.matches(regex)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            int amount = Integer.parseInt(amountStr);
            if (amount < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    public static void isValidOrderFormat(String input) {
        String regex = "^([ㄱ-ㅎ가-힣\\w\\s]+-\\d+)(,[ㄱ-ㅎ가-힣\\w\\s]+-\\d+)*$";
        if (!input.matches(regex)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void isDuplicatedMenu(String input){
        String[] parsedStrings = input.split(",");
        Set<String> list = new HashSet<>();
        for(String str : parsedStrings){
            String[] parsedMenuAndAmount = str.split("-");
            String menu = parsedMenuAndAmount[0];
            if (list.contains(menu)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            list.add(menu);
        }
    }
}
