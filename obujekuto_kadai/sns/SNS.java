/*
メイン機能を実装したJavaプログラム。ユーザーアカウントの管理、
ログイン、投稿の作成・表示、データの保存と読み込みを行う。
*/

package sns;

import accounts.*;
import posts.*;
import util.*;

import net.goui.util.MTRandom;
import java.util.ArrayList;
import java.util.List;

/**
↑部分では、SNSクラスで使用する他のパッケージ（accounts, posts, util）や
乱数を発生させるためのMTRandomをインポートしている
*/

public class SNS {
    
    List<Account> accounts;
    List<Post> posts;
    Stdin si;

    /**
    ↑部分では、次のようなことを定義してる。
    accountsはAccountオブジェクトのリストである。登録されたユーザーの
    アカウント情報を保持する。
    postsはPostオブジェクトのリストである。作成された投稿を保持する。
    siは標準入力を処理するためのオブジェクト。
     */

    SNS() {

        accounts = new ArrayList<>();
        posts = new ArrayList<>();
        si = new Stdin();

        try {
            loadAccountsFromFile(); 
            loadPostsFromFile();    
        } catch (Exception e) {
            System.out.println("データの読み込みに失敗しました: " + e.getMessage());
        }
    }
    /**
    ↑の部分は、次のようなことを行ってる。
    ・accounts と posts のリストを初期化をしている。
    ・Stdin オブジェクトを初期化して、ユーザーからの入力を処理できるようにした。
    ・loadAccountsFromFile() と loadPostsFromFile() を呼び出して、アカウント情報
     と投稿情報をファイルから読み込む。これにより、プログラムを再起動しても前の
     状態からスタートできる。読み込みに失敗した場合はエラーメッセージを表示する。
     */

    void createAccount(String name, String password) {
        User account = new User(name, password);
        accounts.add(account);
        System.out.println("アカウントが正常に作成されました。ID: " + account.id);
    }
    /*
    ↑の部分では、アカウントを作った時に呼び出され、ユーザー名とパスワードを受け取って、
    新しい User オブジェクトを作成する。accounts リストに新しいアカウントを追加して、
    アカウントが正常に作成されたことをコンソールに表示する。
    */


    Account login(String name, String password) {

        for (Account account : accounts) {
            if (account.name.equals(name) && account.password.equals(password)) {
                System.out.println("ログインに成功しました。");
                return account;
            }
        }

        System.out.println("認証に失敗しました。ログインできませんでした。");
        return null;

    }
    /*
    ↑の部分では、ログインの時に呼び出されます。引数として受け取ったユーザー名とパスワードを使って、
    accounts リストの中から一致するアカウントを探す。一致するアカウントが見つかるとそのアカウントを返し、
    ログイン成功のメッセージを表示する。見つからない場合は、ログイン失敗のメッセージを表示し、null を返す。
    */

    void saveAccountsToFile() throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"))) {

            for (Account account : accounts) {
                writer.write(account.name + "," + account.password + "," + account.id);
                writer.newLine();
            }

        }

    }
    /*
    ↑の部分では、アカウント情報を accounts.txt に保存する。
    */

    void loadAccountsFromFile() throws IOException {

        File file = new File("accounts.txt");
        if (file.exists()) {

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                String line;
                while ((line = reader.readLine()) != null) {

                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String name = parts[0];
                        String password = parts[1];
                        int id = Integer.parseInt(parts[2]);
                        User user = new User(name, password, id);
                        accounts.add(user);
                    }

                }

            }

        }

    }
    /*
    ↑の部分では、accounts.txt からアカウント情報を読み込み、accounts リストを初期化する。
    */


    void savePostsToFile() throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("posts.txt"))) {

            for (Post post : posts) {
                writer.write(escape(post.author) + "," + escape(post.content) + "," + post.authorId);
                writer.newLine();
            }

        }

    }
    /*
    ↑の部分では、投稿情報を posts.txt に保存する。
    */

    void loadPostsFromFile() throws IOException {

        File file = new File("posts.txt");
        if (file.exists()) {

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                String line;
                while ((line = reader.readLine()) != null) {

                    String[] parts = line.split(",");
                    if (parts.length == 3) {

                        String author = parts[0];
                        String content = parts[1];
                        int authorId = Integer.parseInt(parts[2]);
                        Post post = new Post(author, content, authorId);
                        posts.add(post);

                        for (Account account : accounts) {

                            if (account instanceof User && account.name.equals(author) && account.id == authorId) {
                                ((User) account).posts.add(post);
                            }

                        }

                    } else {
                        System.out.println("不正な行のフォーマット: " + line); 
                    }

                }

            }

        } else {
            System.out.println("投稿ファイルが存在しません。新しいファイルが作成されます。"); 
        }

    }
    /*
    ↑の部分では、posts.txt から投稿情報を読み込み、posts リストを初期化する。
    */

    String escape(String text) {
        return text.replace(",", "\\,");
    }

    public static void main(String[] args) {

        SNS app = new SNS();

        while (true) {
            System.out.println("1. アカウントを作成");
            System.out.println("2. ログイン");
            System.out.println("3. 終了");
            System.out.print("オプションを選んでください: ");
            int option = app.si.geti(); 

            switch (option) {
                case 1:
                    System.out.print("名前を入力してください: ");
                    String name = app.si.gets(); 
                    System.out.print("パスワードを入力してください: ");
                    String password = app.si.gets(); 
                    app.createAccount(name, password);
                    break;

                case 2:
                    System.out.print("名前を入力してください: ");
                    String loginName = app.si.gets(); 
                    System.out.print("パスワードを入力してください: ");
                    String loginPassword = app.si.gets(); 
                    Account loggedInAccount = app.login(loginName, loginPassword);

                    if (loggedInAccount != null) {

                        if (loggedInAccount instanceof User) {

                            User user = (User) loggedInAccount;
                            while (true) {
                                System.out.println("1. 投稿を作成");
                                System.out.println("2. 自分の投稿を見る");
                                System.out.println("3. すべての投稿を見る");
                                System.out.println("4. ログアウト");
                                System.out.print("オプションを選んでください: ");
                                int userOption = app.si.geti(); 

                                switch (userOption) {
                                    case 1:
                                        System.out.print("投稿内容を入力してください: ");
                                        String content = app.si.gets(); 
                                        user.createPost(content);
                                        app.posts.add(new Post(user.name, content, user.id)); 
                                        break;

                                    case 2:
                                        user.displayMyPosts();
                                        break;

                                    case 3:
                                        app.displayAllPosts();
                                        break;

                                    case 4:
                                        System.out.println("ログアウトしました。");
                                        break;

                                    default:
                                        System.out.println("無効なオプションです。");
                                        break;
                                }

                                if (userOption == 4) {
                                    break;
                                }

                            }

                        } else if (loggedInAccount instanceof Guest) {
                            Guest guest = (Guest) loggedInAccount;
                            guest.displayAllPosts(app.posts);
                        }

                    }
                    break;

                case 3:
                    System.out.println("アプリケーションを終了します。");

                    try {
                        app.saveAccountsToFile(); 
                        app.savePostsToFile();  
                    } catch (IOException e) {
                        System.out.println("データの保存に失敗しました: " + e.getMessage());
                    }

                    System.exit(0);
                    break;

                default:
                    System.out.println("無効なオプションです。");
                    break;
            }

        }

    }
    /*
    ↑の部分では、SNS クラスのインスタンス app を作成して、無限ループを開始する。これにより、アプリの
    メインの動きを開始する。ユーザーに対してアカウントの作成、ログイン、終了を表示して、選択に応じて
    適切なメソッドを呼び出す。ログイン成功すると、投稿を作成したり投稿したものの表示ができる。3を選択
    するとアプリケーションを終了して、アカウント情報と投稿情報をファイルに保存する。これにより、また
    プログラムを開始した時に今までの情報がつかえる。
    */

    void displayAllPosts() {

        System.out.println("すべての投稿:");
        for (Post post : posts) {
            post.display();
        }

    }
    /*
    ↑の部分が呼び出されると、postsリスト内の全ての投稿を表示する。
    各Post オブジェクトの display()メソッドを呼び出して、投稿の内容をコンソールに出力する。
    */

}
