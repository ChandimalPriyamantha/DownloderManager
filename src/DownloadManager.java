import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadManager implements Runnable{



    ExecutorService executorService = Executors.newFixedThreadPool(3);


    static String bookMarks[] = {

            "https://www.f.waseda.jp/sidoli/Einstein_Relativity.pdf",
            "https://ia801301.us.archive.org/1/items/einsteinstheoryo00born/einsteinstheoryo00born.pdf",
            "https://cdn.preterhuman.net/texts/lyrics_and_music_related/Piano/The%20Beatles%20-%20All%20You%20Need%20Is%20Love.pdf"
    };


    @Override
    public void run() {


        for(int i = 0; i < bookMarks.length; i++){
            String destinationPath = "download_files " + i + ".pdf";
            String bookMark = bookMarks[i];

            executorService.execute(() -> {

                try {
                    fileDownloader(bookMark,destinationPath);
                } catch (IOException e) {
                    System.out.println(e);
                }


            });

        }
        executorService.shutdown();


    }

    public static void fileDownloader(String fileURL, String destinationPath) throws IOException {

        URL url = new URL(fileURL);
        InputStream in = url.openStream();
        FileOutputStream fos = new FileOutputStream(destinationPath);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) != -1){
            fos.write(buffer, 0, length);
        }

        System.out.println("Download : " + destinationPath);

    }
}
