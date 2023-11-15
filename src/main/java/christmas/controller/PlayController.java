package christmas.controller;

import christmas.domain.Customer;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class PlayController {

    public void start(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        String visitDay = InputView.inputVisitDay();
        String menuAndAmount = InputView.inputMenuAndAmount();
        Map<String, String> order = MenuController.getMenuAndAmount(menuAndAmount);
        Customer customer = new Customer(order);
        OutputView.printEventMessage(visitDay);
        customer.printOrder();
        customer.printPrevAmount(String.valueOf(customer.getPrevAmount()));
        int discounted_amount = DiscountController.getDiscountAmount(customer, visitDay);
        printChampagne(customer);
        printDiscountList(customer, discounted_amount);
        printTotalDiscountAmount(discounted_amount, customer);
        printTotalAmount(customer, discounted_amount);
        printBadge(customer);
    }

    private void printTotalAmount(Customer customer, int discounted_amount) {
        System.out.println("<할인 후 예상 결제 금액>");
        Customer.printFormatedAmount(String.valueOf(customer.getPrevAmount() - discounted_amount), "");
    }

    private void printDiscountList(Customer customer, int discounted_amount) {
        System.out.println("<혜택 내역>");
        if(discounted_amount <= 0){
            System.out.println("없음");
        }
        Map<String, Integer> discountList = customer.getDiscountList();
        for(Map.Entry<String, Integer> e : discountList.entrySet()){
            System.out.print(e.getKey());
            customer.printFormatedAmount(String.valueOf(e.getValue()), "-");
        }
    }


    public void printChampagne(Customer customer){
        if(customer.getChampagne()){
            System.out.println("<증정 메뉴>");
            System.out.println("샴페인 1개");
        }
        if(!customer.getChampagne()){
            System.out.println("<증정 메뉴>");
            System.out.println("없음");
        }
    }


    private void printBadge(Customer customer) {
        if(!customer.getBadge().equals("")){
            System.out.println("<12월 이벤트 배지>");
            System.out.println(customer.getBadge());
        }
        if(customer.getBadge().equals("")){
            System.out.println("<12월 이벤트 배지>");
            System.out.println("없음");
        }
    }

    private void printTotalDiscountAmount(int discountedAmount, Customer customer) {
        int champagne = 0;
        if(customer.getChampagne())
            champagne = 25000;
        System.out.println("<총혜택 금액>");
        if(discountedAmount > 0){
            Customer.printFormatedAmount(String.valueOf(discountedAmount + champagne), "-");
            return;
        }
        if(discountedAmount == 0){
            System.out.println("0원");
        }
    }
}
