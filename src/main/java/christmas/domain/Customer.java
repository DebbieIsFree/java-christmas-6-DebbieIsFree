package christmas.domain;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Customer {

    Map<String, String> order = new HashMap<>();
    private int prev_amount = 0;
    int discounted_amount = 0;
    String badge = "";
    Boolean champagne;

    Map<String, Integer> discountList = new HashMap<>();

    public Customer(Map<String, String> order) {
        this.order = order;
        this.champagne = false;
        calculatePrevAmount();
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public void setChampagne(Boolean champagne) {
        this.champagne = champagne;
    }

    public Map<String, String> getOrder() {
        return order;
    }

    public int getPrevAmount() {
        return prev_amount;
    }

    public String getBadge() {
        return badge;
    }

    public Boolean getChampagne() {
        return champagne;
    }

    public Map<String, Integer> getDiscountList() {
        return discountList;
    }

    public void printOrder(){
        System.out.println("<주문 메뉴>");
        for(Map.Entry<String, String> entry : order.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue() + "개");
        }
    }

    public void printPrevAmount(String amount) {
        System.out.println("<할인 전 총주문 금액>");
        printFormatedAmount(amount, "");
    }

    public static void printFormatedAmount(String amount, String sign) {
        // 숫자 설정
        int number = Integer.parseInt(amount);
        if(number == 0) {
            return;
        }
        // 숫자를 3자리씩 콤마로 구분하여 형식화
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(number);
        System.out.println(sign + formattedNumber + "원");
    }

    public void calculatePrevAmount(){
        Menu menuInstance = new Menu();
        Map<String, Integer> menu = menuInstance.getMenu();

        for(Map.Entry<String, String> entry : order.entrySet()){
            String menuName = entry.getKey();
            Integer menuQuantity = Integer.valueOf(entry.getValue());

            // Menu.getMenu()에서 메뉴의 가격을 가져와서 기존 prev_amount에 더함.
            Integer menuPrice = menu.get(menuName);
            prev_amount += menuPrice * menuQuantity;
        }
    }
}
