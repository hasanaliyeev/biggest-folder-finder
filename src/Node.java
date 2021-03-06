import java.io.File;
import java.util.ArrayList;

public class Node {

  private File folder;
  private ArrayList<Node> children;
  private long size;

  public Node(File folder) {
    this.folder = folder;
    children = new ArrayList<>();
  }

  public File getFolder() {
    return folder;
  }

  public void addChild(Node node) {
    children.add(node);
  }

  public ArrayList<Node> getChildren() {
    return children;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public String toString() {
    return "";
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
