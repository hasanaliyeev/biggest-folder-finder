import java.io.File;
import java.nio.LongBuffer;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {

  public static final long MAX_SIZE_BAYT = 1024;
  public static final long MAX_SIZE_KB = 1024 * MAX_SIZE_BAYT;
  public static final long MAX_SIZE_MB = 1024 * MAX_SIZE_KB;
  public static final long MAX_SIZE_GB = 1024 * MAX_SIZE_MB;

  public static void main(String[] args) {

    String folderPath = "C:/Users/aliye/Desktop/Programs";
    File file = new File(folderPath);
    Node root = new Node(file);

    long start = System.currentTimeMillis();

    FolderSizeCalculator calculator = new FolderSizeCalculator(root);
    ForkJoinPool pool = new ForkJoinPool();
    long size = pool.invoke(calculator);
    long duration = (System.currentTimeMillis() - start);
    System.out.println(duration + " ms");

    System.out.println(root);


  }


}
