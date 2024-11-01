/**
このクラスは、ユーザーアカウントについて扱うクラスです。
ユーザー名、パスワード、IDの3つの属性を持っており、
アカウントの生成時にこれらの属性を初期化する。
 */
package accounts;

import net.goui.util.MTRandom;

public class Account {

    public String name;
    public String password;
    public int id;
    /**
    ↑の部分では、次のようなフィールドを定義しています。
    ・nameはアカウントの所有者の名前を保持している。public 
    として定義されていることから、クラス外部から直接アクセス可能。
    ・passwordはアカウントのパスワードを保持します。public 
    として定義されていることから、クラス外部から直接アクセス可能。
    ・idは各アカウントに割り当てられた一意のIDを保持している。
    public として定義されていることから、クラス外部から直接アクセス可能。
     */

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
        MTRandom mtRandom = new MTRandom();
        this.id = mtRandom.nextInt(1000);
    }
    /*
    ↑の部分では、ユーザー名とパスワードを引数として受け取って、それらを
     name フィールドと password フィールドにそれぞれ代入する。さらに、
     MTRandom クラスを使ってランダムなIDを生成して、それを id フィールド
     に代入する。
    */

    public Account(String name, String password, int id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }
    /*
    ↑の部分では、ユーザー名、パスワード、IDを引数として受け取る。外部から
    指定されたIDを使ってアカウントを初期化できる。引数として渡されたIDを
    そのまま id フィールドに代入している。
    */

}
