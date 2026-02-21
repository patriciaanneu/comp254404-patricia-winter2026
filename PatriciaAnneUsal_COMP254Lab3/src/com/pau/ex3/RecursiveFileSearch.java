import java.io.File;

public class RecursiveFileSearch {
    public static int find(String path, String filename) {
        int count = 0;
        File rootDir = new File(path);

        //base case - invalid path
        if (!rootDir.exists() || !rootDir.isDirectory()) {
            return count;
        }

        File[] entries = rootDir.listFiles();

        if (entries == null) {
            return count;
        }

        for (File entry : entries) {
            //check for match
            if (entry.getName().equals(filename)) {
                System.out.println("Found: " + entry.getAbsolutePath());
                count++;
            }

            //recursive call for subdirectories
            if (entry.isDirectory()) {
                count += find(entry.getAbsolutePath(), filename);
            }
        }
        return count;
    }
}

public static void main(String[] args) {
    String path = "C:\\Users\\patri\\Downloads";

    System.out.println("Searching for 'statdisk_scatter_plot.png':\n");
    int count  = RecursiveFileSearch.find(path, "statdisk_scatter_plot.png");
    if (count == 0) System.out.println("No files found");


}