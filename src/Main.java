public class Main {

    public static void main(String[] args) {


       Thread myThead = new Thread(new DownloadManager());
       myThead.start();

    }
}
