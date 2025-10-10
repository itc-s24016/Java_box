package games.word_guess;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public  class Main {
    public static void main(String[] args) {
        System.out.println("単語当てゲーム！");
        System.out.printf("難易度を選んでください%n【EASY】or【HARD】%n▶");
        Scanner sc = new Scanner(System.in);
        String level = sc.nextLine();
        String[] ans = pass(level);
        char[] answer = ans[0].toCharArray();
        char[] guess = new char[answer.length];

        //guessの初期値を「______」に設定
        Arrays.fill(guess, '_');
        int i = Integer.parseInt(ans[1]);
        while (i > 0) {
            printGuess(guess);
            life(i);
            inputAndCheckAnswer(answer, guess);
            if (checkWin(guess)) break;
            System.out.println();
            i--;
        }
        if (checkWin(guess)) {
            System.out.printf("%n%n【%S】%n  正解！%n%n", ans[0]);
        } else {
            System.out.printf("%n%nゲームオーバー！%n正解は「%s」でした%n%n", ans[0]);
        }
    }
    //「現在の状況」を繰り返すメソッド
    public static void printGuess(char[] guess){
        System.out.print("現在の状況：");
        for (char i : guess) System.out.print(i + " ");
        System.out.println();
    }

    //入力された文字があっているかチェックするメソッド
    public static void inputAndCheckAnswer(char[] answer, char[] guess){
        Scanner sc = new Scanner(System.in);
        System.out.print("１文字入力してください：");
        char input = sc.next().charAt(0);
        //大文字に変換
        input = Character.toUpperCase(input);
        for(int i = 0; i < answer.length; i++) {
            if(input == answer[i]) {
                guess[i] = input;
            }
        }
    }

    //正解したか判定するメソッド
    public static boolean checkWin(char[] guess){
        for (char i : guess) {
            if (i == '_') return false;
        }
        return true;
    }

    //答えをランダムに決めるメソッド
    public static String[] pass(String choice){
        String[] easy_list = {"COFFEE","APPLE", "CAMERA", "ANIMAL"};
        String[] hard_list = {"ISLAND", "IPHONE","UPDATE", "FLOWER", "FOOTER", "SUMMER", "SCHOOL"};
        Random ram = new Random();
        if (choice.equals("easy")) {
            System.out.println("候補の単語：" + Arrays.toString(easy_list));
            return new String[]{ easy_list[ ram.nextInt( easy_list.length ) ], "6" };
        }
        System.out.println("候補の単語：" + Arrays.toString(hard_list));
        return new String[]{ hard_list[ ram.nextInt( hard_list.length ) ], "6" };
    }

    //残りの残機数を表示するメソッド
    public static void life(int i){
        int j = i;
        String life = "♥";
        while(j > 0) {
            System.out.print(life);
            j--;
        }
        System.out.println();
    }
}