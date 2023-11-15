package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.Validator;

public class InputView {

    public static String inputVisitDay(){
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input;
        while(true){
            try{
                input = Console.readLine();
                Validator.validateVisitDay(input);
                return input;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static String inputMenuAndAmount(){
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input;
        while(true){
            try {
                input = Console.readLine();
                Validator.validateMenuAndAmount(input);
                break;
            }catch (IllegalArgumentException e){
//                System.out.println(e.getMessage());
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
        return input;
    }
}
