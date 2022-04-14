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

    String folderPath = "C:/Users/aliye/Desktop/Logs";
    File file = new File(folderPath);

    long start = System.currentTimeMillis();

    FolderSizeCalculator calculator = new FolderSizeCalculator(file);
    ForkJoinPool pool = new ForkJoinPool();
    long size = pool.invoke(calculator);
    long duration = (System.currentTimeMillis() - start);
    System.out.println(duration + " ms");

    System.out.println(getHumanReadableSize(size));
    System.out.println(getSizeFromHumanReadable("264Mb"));


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

  public static String getHumanReadableSize(long size) {
    if (size > 0 && size < MAX_SIZE_BAYT) {
      return size + "B";
    }
    if (size >= MAX_SIZE_BAYT && size < MAX_SIZE_KB) {
      return size / MAX_SIZE_BAYT + "Kb";
    }
    if (size >= MAX_SIZE_KB && size < MAX_SIZE_MB) {
      return size / MAX_SIZE_KB + "Mb";
    }
    if (size >= MAX_SIZE_MB && size < MAX_SIZE_GB) {
      return size / MAX_SIZE_MB + "Gb";
    }
    if (size >= MAX_SIZE_GB) {
      return size / MAX_SIZE_GB + "Tb";
    }
    return "";
  }

  public static long getSizeFromHumanReadable(String size) {
    String str = size.replaceAll("[\\d]+", "");
    String nums = size.replaceAll("[^\\d]+", "");

    long realSize = Long.parseLong(nums);

    long baytSize = 0;

    if (str.equals("B")) {
      baytSize = realSize;
    }
    if (str.equals("Kb")) {
      baytSize = realSize * MAX_SIZE_BAYT;
    }
    if (str.equals("Mb")) {
      baytSize = realSize * MAX_SIZE_KB;
    }
    if (str.equals("Gb")) {
      baytSize = realSize * MAX_SIZE_MB;
    }
    if (str.equals("Tb")) {
      baytSize = realSize * MAX_SIZE_GB;
    }

    return baytSize;
  }

}
