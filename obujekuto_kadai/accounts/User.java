/**
このクラスは、Account クラスを拡張したもので、プログラム
におけるユーザーを表している。このクラスは、ユーザーが
作成した投稿を管理して、その投稿を表示するためのメソッド
を提供している。
 */
package accounts;

import posts.Post;
import java.util.ArrayList;
import java.util.List;

public class User extends Account {

    public List<Post> posts;
    /**
    ↑の部分のフィールドは、ユーザーが作成した投稿のリストを
    保持する。ユーザーが作成したすべての投稿がここに格納される。
　　public として宣言されているので、外部からも直接アクセス可能。
     */

    public User(String name, String password) {
        super(name, password);
        posts = new ArrayList<>();
    }
    /*
    ↑の部分は、ユーザー名とパスワードを受け取って、親クラスで
    ある Account のコンストラクタを呼び出し、 name と password 
    を初期化する。posts フィールドを ArrayList の新しいインスタ
    ンスとして初期化する。
    */

    public User(String name, String password, int id) {
        super(name, password, id);
        posts = new ArrayList<>();
    }
    /*
    ↑の部分は、ユーザー名、パスワード、IDを受け取って、親クラスで
    ある Account のコンストラクタを呼び出し、 name、password、id 
    を初期化する。posts フィールドは空の ArrayList で初期化されて、
    ユーザーが投稿を作成する準備が整う。
    */

    public void createPost(String content) {
        Post post = new Post(name, content, id);
        posts.add(post);
    }
    /*
    ↑の部分は、ユーザーが新しい投稿を作成するときに使用する。
    content 引数を受け取って、ユーザーの名前、投稿内容、および
    ユーザーID を含む新しい Post オブジェクトを作成する。作成
    された投稿は posts リストに追加される。
    */

    public void displayMyPosts() {

        System.out.println("自分の投稿:");

        for (Post post : posts) {
            post.display();
        }

    }
    /*
    ↑の部分は、ユーザーがこれまでに作成したすべての投稿を表示する。
    自分の投稿:を表示し、その後に、posts リスト内のすべての Post 
    オブジェクトに対して display() メソッドを呼び出し、各投稿の
    内容をコンソールに表示する。
    */

}
