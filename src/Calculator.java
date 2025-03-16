import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    private static final ArrayList<String> history = new ArrayList<>(); // Список для истории

    // Простые математические операции
    public static double add(double a, double b) { return a + b; }
    public static double subtract(double a, double b) { return a - b; }
    public static double multiply(double a, double b) { return a * b; }
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Ошибка: Деление на ноль!"); // Проверяем деление на 0
        }
        return a / b;
    }
    public static double modulus(double a, double b) {  // Остаток
        if (b == 0) {
            throw new ArithmeticException("Ошибка: Остаток от деления на ноль!");
        }
        return a % b;
    }

    // Доп функции
    public static double power(double base, double exponent) { return Math.pow(base, exponent); }  // степень
    public static double sqrt(double number) {  // Квадратный корень
        if (number < 0) {
            throw new ArithmeticException("Ошибка: Квадратный корень из отрицательного числа!");
        }
        return Math.sqrt(number);
    }
    public static double abs(double number) { return Math.abs(number); }  // Абсолютное значение
    public static long round(double number) { return Math.round(number); }  // Округление

    // Метод для вычисляения выражения
    public static double evaluateExpression(String expression) {
        try {
            return evaluatePostfix(infixToPostfix(expression));
        } catch (Exception e) {
            System.out.println("Ошибка: Некорректное выражение!");
            return Double.NaN;  // Возвращаем не число
        }
    }

    // Нужен для правильного вычисления
    private static String infixToPostfix(String expression) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch) || ch == '.') {
                output.append(ch);
            } else {
                output.append(" ");
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(ch);  // Кладём оператор в стек
            }
        }
        while (!stack.isEmpty()) {
            output.append(" ").append(stack.pop());  // Вытаскиваем оставшиеся операторы
        }
        return output.toString();
    }

    // Вычисление выражения в постфиксной форме
    private static double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        Scanner sc = new Scanner(postfix);
        while (sc.hasNext()) {
            if (sc.hasNextDouble()) {
                stack.push(sc.nextDouble());  // Если число кладём в стек
            } else {
                double b = stack.pop();  // Достаём второе
                double a = stack.pop();  // Достаём первое
                switch (sc.next()) {
                    case "+": stack.push(add(a, b)); break;
                    case "-": stack.push(subtract(a, b)); break;
                    case "*": stack.push(multiply(a, b)); break;
                    case "/": stack.push(divide(a, b)); break;
                    case "%": stack.push(modulus(a, b)); break;
                }
            }
        }
        return stack.pop();
    }

    // Чем больше число, тем выше приоритет
    private static int precedence(char op) {
        return switch (op) {
            case '+', '-' -> 1;  // Низкий
            case '*', '/', '%' -> 2;  // Высокий
            default -> 0;
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Создаём сканер
        System.out.println("Добро пожаловать в калькулятор!");

        while (true) {  // Запускаем бесконечный цикл
            System.out.print("Введите выражение (или 'history' для истории, 'exit' для выхода): ");
            String input = scanner.nextLine().trim();  // Ввод пользователя

            if (input.equalsIgnoreCase("exit")) {  // Выходим
                System.out.println("Спасибо за использование калькулятора!");
                break;
            } else if (input.equalsIgnoreCase("history")) {  // Показываем историю
                System.out.println("История вычислений:");
                if (history.isEmpty()) {
                    System.out.println("История пуста.");
                } else {
                    for (int i = 0; i < history.size(); i++) {
                        System.out.println((i + 1) + ". " + history.get(i));  // Выводим вычисление
                    }
                }
            } else {
                double result = evaluateExpression(input);  // Вычисляем
                if (!Double.isNaN(result)) {
                    System.out.println("Результат: " + result);
                    history.add(input + " = " + result);  // Добавляем в историю
                }
            }
        }
        scanner.close();
    }
}
