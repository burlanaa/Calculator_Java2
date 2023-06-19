import java.util.Scanner;

public class Main {
    public static String calc(String input) throws Exception {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new Exception("Invalid input format");
        }

        String aStr = parts[0];
        String op = parts[1];
        String bStr = parts[2];

        int a = parseNumber(aStr);
        int b = parseNumber(bStr);

        int result;
        switch (op) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new Exception("Invalid input format");
        }

        if (isRoman(aStr) && isRoman(bStr)) {
            return toRoman(result);
        } else if (!isRoman(aStr) && !isRoman(bStr)) {
            return Integer.toString(result);
        } else {
            throw new Exception("Invalid input format");
        }
    }

    private static int parseNumber(String str) throws Exception {
        if (isRoman(str)) {
            return fromRoman(str);
        } else {
            try {
                int num = Integer.parseInt(str);
                if (num < 1 || num > 10) {
                    throw new Exception("Invalid input format");
                }
                return num;
            } catch (NumberFormatException e) {
                throw new Exception("Invalid input format");
            }
        }
    }

    private static boolean isRoman(String str) {
        return str.matches("^(I|II|III|IV|V|VI|VII|VIII|IX|X)$");
    }

    private static int fromRoman(String str) throws Exception {
        int result = 0;
        int prev = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            int curr = romanToInt(c);
            if (curr < prev) {
                result -= curr;
            } else {
                result += curr;
            }
            prev = curr;
        }

        if (!toRoman(result).equals(str)) {
            throw new Exception("Invalid input format");
        }

        return result;
    }

    private static int romanToInt(char c) throws Exception {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            default:
                throw new Exception("Invalid input format");
        }
    }

    private static String toRoman(int num) {
        if (num < 1 || num > 3999) {
            throw new IllegalArgumentException("Invalid input format");
        }

        StringBuilder sb = new StringBuilder();
        while (num >= 1000) {
            sb.append("M");
            num -= 1000;
        }
        while (num >= 900) {
            sb.append("CM");
            num -= 900;
        }
        while (num >= 500) {
            sb.append("D");
            num -= 500;
        }
        while (num >= 400) {
            sb.append("CD");
            num -= 400;
        }
        while (num >= 100) {
            sb.append("C");
            num -= 100;
        }
        while (num >= 90) {
            sb.append("XC");
            num -= 90;
        }
        while (num >= 50) {
            sb.append("L");
            num -= 50;
        }
        while (num >= 40) {
            sb.append("XL");
            num -= 40;
        }
        while (num >= 10) {
            sb.append("X");
            num -= 10;
        }
        while (num >= 9) {
            sb.append("IX");
            num -= 9;
        }
        while (num >= 5) {
            sb.append("V");
            num -= 5;
        }
        while (num >= 4) {
            sb.append("IV");
            num -= 4;
        }
        while (num >= 1) {
            sb.append("I");


            num -= 1;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        try {
            String result = calc(input);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
