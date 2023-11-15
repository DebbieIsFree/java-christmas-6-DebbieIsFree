package christmas.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MenuController {

    public static Map<String, String> getMenuAndAmount(String menuAndAmount) {
        String[] parsedStrings = menuAndAmount.split(",");

        Map<String, String> order = new HashMap<>();

        for(String str : parsedStrings){
            String[] parsedMenuAndAmount = str.split("-");
            String menu = parsedMenuAndAmount[0];
            Integer amount = Integer.valueOf(parsedMenuAndAmount[1]);
            Integer prev_amount = Optional.ofNullable(order.get(menu))
                    .map(Integer::valueOf)
                    .orElse(0);
            // 새 값을 맵에 추가한 후에 prev_amount를 업데이트
            order.put(menu, String.valueOf(prev_amount + amount));
            prev_amount = Integer.valueOf(order.get(menu));
        }
        return order;
    }
}
