
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main{
    public static void main (String[] args) {
        System.out.println("Введите сколько чисел вам нужно: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> lengths = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Введите числo: ");
            int num = scanner.nextInt();
            list.add(num);// добавляю в лист введенное в сканнер число.
            lengths.add(String.valueOf(num).length());
        }

        // Задание 1. Вариант А
        // Найти самое короткое и самое длинное число. Вывести найденные числа
        // и их длину.
        System.out.println(" Самое короткое и длинное числа. ");
        int biggest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        for (int i : list){
            int len = String.valueOf(i).length();//Получаю длину числа
            if (i > biggest){
                biggest = i;
            }
            if (i < smallest){
                smallest = i;
            }
        }
        System.out.println("Самое длинное число: " + biggest);
        System.out.println("Самое короткое число: " + smallest);

        // Задание 2. Вариант А
        // Упорядочить и вывести числа в порядке возрастания (убывания) значений
        // их длины.
        // Сортирую  список по длине чисел пузырьком
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (String.valueOf(list.get(j)).length() < String.valueOf(list.get(j + 1)).length()) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        System.out.println("Вот отсортированные по длине элементы");
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.println(list.get(i));
        }
        // Задание 3. Вариант А
        // Вывести на консоль те числа, длина которых меньше (больше) средней,
        // а также длину.
        System.out.println("Задание 3.");
        int sum=0;
        for (Integer length : lengths) {
            sum += length;
        }
        float mid = sum/lengths.size();
        for (Integer integer : list) {
            if (integer < mid) {// здесь менять больше меньше
                System.out.println("Число: " +integer + " Длина: " + String.valueOf(integer).length());
            }
        }

        // Задание 4. Вариант А
        //Найти число, в котором число различных цифр минимально. Если таких
        //чисел несколько, найти первое из них
        System.out.println("Задание 4.");
        ArrayList<Integer> minDiff = new ArrayList<>();
        int maxcounter=Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int counter=0;
            int actmin = list.get(i); //мин в данный момент
            String str = String.valueOf(actmin);
            for (int j = 0; j < str.length()-1; j++) {
                if (str.charAt(j) == str.charAt(j+1)) {// charAt получает конкретный символ строки с указанного индекса.
                    counter++;
                }
            }
            if (counter<maxcounter){
                minDiff.add(actmin);
            }
        }
        System.out.println(minDiff.getFirst());// вывожу первое попавшееся

        // Задание 5. Вариант А
        //Найти количество чисел, содержащих только четные цифры
        System.out.println("Задание 5.");
        int counter5=0;
        for (int i = 0; i < list.size(); i++) {
            int k = list.get(i);
            String str = String.valueOf(k);
            for (int j = 0; j < str.length()-1; j++) {
                if (j%2==0) {// charAt получает конкретный символ строки с указанного индекса.
                    counter5++;
                }
            }
        }
        System.out.println("Колво чисел с только четными цифрами: " + counter5);

        // Задание 6. Вариант А
        //Найти число, цифры в котором идут в строгом порядке возрастания. Если
        //таких чисел несколько, найти первое из них.
        System.out.println("Задание 6.");

        ArrayList<Integer> Asc = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean xf=true;
            int k = list.get(i); //мин в данный момент
            String str = String.valueOf(k);
            for (int j = 0; j < str.length()-1; j++) {
                if (Character.getNumericValue(str.charAt(j)) < Character.getNumericValue(str.charAt(j+1))) {// charAt получает конкретный символ строки с указанного индекса.
                    xf=true;
                }
                else{
                    xf=false;
                    break;
                }
            }
            if (xf){
                Asc.add(k);
            }

        }
        System.out.println(Asc.getFirst());// вывожу первое попавшееся
        // Задание 7 вариантА.
        // Найти число, состоящее только из различных цифр. Если таких чисел несколько, найти первое из них.
        System.out.println("Задание 7.");

        ArrayList<Integer> maxDiff = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean xf=true;
            int act = list.get(i);
            String str = String.valueOf(act);
            for (int j = 0; j < str.length()-1; j++) {
                if (Character.getNumericValue(str.charAt(j)) != Character.getNumericValue(str.charAt(j+1))) {// charAt получает конкретный символ строки с указанного индекса.
                    xf=true;
                }
                else{
                    xf=false;
                    break;
                }
            }
            if (xf){
                maxDiff.add(act);
            }
        }
        System.out.println(minDiff.getFirst());// вывожу первое попавшееся

    }
}