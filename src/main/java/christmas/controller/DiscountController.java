package christmas.controller;

import christmas.domain.Customer;

import java.util.*;

public class DiscountController {

    public static int getDiscountAmount(Customer customer, String visitDay) {
        Map<String, Integer> discountList = customer.getDiscountList();

        if(customer.getPrevAmount() < 10000)
            return 0;
        if(checkMenu(customer))
            return 0;

        int discountedAmount = 0;
        discountedAmount += starDayEvent(visitDay, discountList);
        discountedAmount += christmasEvent(visitDay, discountList);
        discountedAmount += weekdayEvent(visitDay, customer.getOrder(), discountList);
        discountedAmount += weekendEvent(visitDay, customer.getOrder(), discountList);
        giftEvent(customer.getPrevAmount(), customer, discountList);
        totalDiscountAmountEvent(discountedAmount, customer);
        return discountedAmount;
    }

    public static boolean checkMenu(Customer customer){
        Map<String, String> order = customer.getOrder();
        Set<String> check = new HashSet<>();
        for(Map.Entry<String, String> e : order.entrySet()){
            check.add(e.getKey());
        }
        return check.size() == 1 &&
                (check.contains("샴페인") || check.contains("레드와인") || check.contains("제로콜라"));
    }

    public static int christmasEvent(String visitDay, Map<String, Integer> discountList){
        int sum = 1000;
        int day = Integer.parseInt(visitDay);
        if(day >= 26){
            return 0;
        }
        for(int s=1; s<day; s++){
            sum += 100;
        }
        discountList.put("크리스마스 디데이 할인: ", sum);
        return sum;
    }

    public static int weekdayEvent(String visitDay, Map<String, String> order, Map<String, Integer> discountList){
        int day = Integer.parseInt(visitDay);
        int sum = 0;
        if(day % 7 != 1 && day % 7 != 2){
            for(Map.Entry<String, String> entry : order.entrySet()){
                sum += weekdayCheck(entry.getKey(), entry.getValue());
            }
        }
        discountList.put("평일 할인: ", sum);
        return sum;
    }

    public static int weekdayCheck(String menu, String amount){
        if(menu.equals("초코케이크") || menu.equals("아이스크림")){
            return (2023 * Integer.parseInt(amount));
        }
        return 0;
    }

    public static int weekendEvent(String visitDay, Map<String, String> order, Map<String, Integer> discountList){
        int day = Integer.parseInt(visitDay);
        int sum = 0;
        List<String> specialMenuItems = Arrays.asList("티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타");
        if(day % 7 == 1 || day % 7 == 2){
            for (Map.Entry<String, String> entry : order.entrySet()) {
                sum += weekendDayCheck(entry.getKey(), entry.getValue(), specialMenuItems);
            }
        }
        discountList.put("주말 할인: ", sum);
        return sum;
    }

    public static int weekendDayCheck(String menu, String amount, List<String> specialMenuItems){
        if (specialMenuItems.contains(menu)) {
            return (2023 * Integer.parseInt(amount));
        }
        return 0;
    }

    public static void giftEvent(int prev_amount, Customer customer, Map<String, Integer> discountList){
        if(prev_amount >= 120000){
            customer.setChampagne(true);
            discountList.put("증정 이벤트: ", 25000);
        }
    }

    public static int totalDiscountAmountEvent(int discountedAmount, Customer customer) {
        int giftType = 0;
        if(discountedAmount >= 20000){
            customer.setBadge("별");
            return 1;
        }
        if(discountedAmount >= 10000){
            customer.setBadge("트리");
            return 2;
        }
        if(discountedAmount >= 5000){
            customer.setBadge("산타");
            return 3;
        }
        return 0;
    }

    public static int starDayEvent(String visitDay, Map<String, Integer> discountList){
        List<String> starDays = Arrays.asList("3", "10", "17", "24", "25", "31");
        if(starDays.contains(visitDay)){
            discountList.put("특별 할인: ", 1000);
            return 1000;
        }
        return 0;
    }
}
