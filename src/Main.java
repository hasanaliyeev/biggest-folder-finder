import java.io.File;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {

  public static void main(String[] args) {

    String folderPath = "C:/Users/aliye/Desktop/Programs";
    File file = new File(folderPath);

    long start = System.currentTimeMillis();

    FolderSizeCalculator calculator = new FolderSizeCalculator(file);
    ForkJoinPool pool = new ForkJoinPool();
    long size = pool.invoke(calculator);
    System.out.println("Size: " + size);
    long duration = (System.currentTimeMillis() - start);
    System.out.println(duration + " ms");

  }

  public static long getFolderSize(File folder) {
    if (folder.isFile()) {
      return folder.length();
    }
    long sum = 0;
    File[] files = folder.listFiles();
    for (File fls : files) {
      sum += getFolderSize(fls);
    }
    return sum;
  }

}
