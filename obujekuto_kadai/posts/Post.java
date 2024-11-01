/**
このクラスは、SNSアプリケーションにおける投稿を表している。
投稿は、作成者の名前、投稿内容、IDから構成されていて、この
クラスを使って投稿を作成し、表示することができる。
 */
package posts;

public class Post {

    public String author;
    public String content;
    public int authorId;
    /*
    ↑のフィールドは次のようなことを行っている。
    ・authorは、投稿の作成者の名前を保持する。public として
    宣言されているので、外部から直接アクセス可能。
    ・contentは、投稿の内容を保持する。public として宣言され
    ているので、外部から直接アクセス可能。
    ・authorIdは、投稿の作成者の一意のIDを保持する。public
    として宣言されているので、外部から直接アクセス可能。
    */

    public Post(String author, String content, int authorId) {
        this.author = author;
        this.content = content;
        this.authorId = authorId;
    }
    /**
    ↑の部分は、投稿を作成する際に使用する。author、content、
    authorIdを引数として受け取って、クラスのフィールドに代入
    する。
     */

    public void display() {
        System.out.println("投稿者: " + author + " (ID: " + authorId + ")");
        System.out.println("内容: " + content);
        System.out.println("------------");
    }
    /**
    ↑の部分は、投稿の情報を表示するために使用される。author、
    authorId、contentのフィールドを出力して、投稿の内容を確認
    できるようにする。
     */

}
