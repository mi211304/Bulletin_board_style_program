/*
このクラスは、ユーザー入力を扱うためのクラスです。入力を受け取り、
適切な型に変換して返す役割を持っている。
*/
package util;

import java.util.Scanner;

public class Stdin {

    private Scanner scanner;
    /**
    ↑のフィールドは、Scannerクラスのインスタンスを保持する
    フィールド。scannerを使用して、ユーザーからの入力を受け取る。
     */

    public Stdin() {
        scanner = new Scanner(System.in);
    }
    /*
    ↑の部分は、scannerフィールドを new Scanner(System.in) に
    よって初期化します。標準入力の処理する準備が整う。
    */

    public int geti() {
        return Integer.parseInt(scanner.nextLine().trim());
    }
    /*
    ↑の部分は、ユーザーからの入力を整数として返す。scanner.nextLine() 
    を使用して入力を文字列として受け取って、その後 trim()メソッドを
    使って余分な空白を取り除く。Integer.parseInt() を使って、文字列を
    整数に変換して返す。
    */

    public String gets() {
        return scanner.nextLine().trim();
    }
    /*
    ↑の部分は、ユーザーからの入力を文字列として返す。scanner.nextLine() 
    を使用して入力を受け取って、trim()メソッドを使って余分な空白を取り除い
    た結果を返す。文字列としての入力がそのまま必要な時に、このメソッドが使われる。
    */

}
