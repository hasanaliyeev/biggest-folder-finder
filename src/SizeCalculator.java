public class SizeCalculator {
  public static final long MAX_SIZE_BAIT = 1024;
  public static final long MAX_SIZE_KB = 1024 * MAX_SIZE_BAIT;
  public static final long MAX_SIZE_MB = 1024 * MAX_SIZE_KB;
  public static final long MAX_SIZE_GB = 1024 * MAX_SIZE_MB;

  public static String getHumanReadableSize(long size) {
    if (size > 0 && size < MAX_SIZE_BAIT) {
      return size + "B";
    }
    if (size >= MAX_SIZE_BAIT && size < MAX_SIZE_KB) {
      return size / MAX_SIZE_BAIT + "Kb";
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
    if (str.contains("Kb")) {
      baytSize = realSize * MAX_SIZE_BAIT;
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

  public static String getReadableSize(long size) {
    char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};

    for (int i = 0; i < sizeMultipliers.length; i++) {
      double value = size / Math.pow(1024, i);
      if (value < 1024) {
        return Math.round(value) + "" + sizeMultipliers[i] + (i > 0 ? "b" : "");
      }

    }
    return "";
  }

}
