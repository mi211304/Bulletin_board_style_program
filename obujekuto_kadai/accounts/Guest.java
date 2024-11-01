/**
このクラスは、Account クラスを拡張したものであり、
プログラムにおけるゲストユーザーを表す。今のところ
ゲストユーザーとは、ログイン状態で他のユーザーの投稿
を見ようとした時にゲストユーザーとして扱われる状態である。
 */
package accounts;

import posts.Post;
import java.util.List;

public class Guest extends Account {

    public Guest(String name, String password) {
        super(name, password);
    }
    /*
    ↑の部分は、ユーザー名とパスワードを引数として受け取って、
    親クラスである Account のコンストラクタを呼び出し、 name
    とpasswordを初期化する。
    */

    public Guest(String name, String password, int id) {
        super(name, password, id);
    }
    /*
    ↑の部分は、ユーザー名、パスワード、IDを引数として受け取って、
    親クラスである Account のコンストラクタを呼び出し、 name
    とpassword、IDを初期化する。
    */

    public void displayAllPosts(List<Post> posts) {
        System.out.println("すべての投稿:");

        for (Post post : posts) {
            post.display();
        }

    }
    /**
    ↑の部分は、すべての投稿を表示するために使用される。List<Post> 型
    のリスト posts を引数として受け取って、リスト内の各 Post オブジェ
    クトに対して display() メソッドを呼び出す。すべての投稿を表示する
    前に、すべての投稿:という文字をコンソールに出力する。
     */

}
