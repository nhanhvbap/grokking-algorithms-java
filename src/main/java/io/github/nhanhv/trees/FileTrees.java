package io.github.nhanhv.trees;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Demonstrates two approaches for traversing a file system tree: iterative (using a stack) and
 * recursive (depth-first).
 *
 * <p>A file system naturally forms a tree structure where directories are internal nodes and files
 * are leaf nodes. Both methods below visit every file in a directory subtree and print its name.
 *
 * <p>Time complexity: O(n) for both approaches — where n is the total number of files and
 * directories in the tree.
 *
 * <p>Space complexity:
 *
 * <ul>
 *   <li>Iterative: O(w) — where w is the maximum width (number of entries in the widest directory)
 *       held in the stack at one time.
 *   <li>Recursive: O(d) — where d is the maximum depth of the directory tree, due to the call
 *       stack.
 * </ul>
 */
public class FileTrees {

  /**
   * Entry point that creates a temporary sample directory tree under {@code pics/} in the current
   * working directory, traverses it with both approaches, then deletes the temporary tree on exit.
   *
   * <p>The sample tree created at runtime:
   *
   * <pre>
   *   pics/
   *   ├── beach.jpg
   *   ├── dog.jpg
   *   └── travel/
   *       ├── paris.jpg
   *       └── tokyo.jpg
   * </pre>
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    File root = new File(System.getProperty("user.dir"), "pics");

    try {
      // --- setup: build a sample directory tree ---
      createSampleTree(root);

      System.out.println("=== Iterative traversal ===");
      printAllNamesFiles(root);

      System.out.println("=== Recursive traversal ===");
      printAllNamesFilesWithRec(root);

    } catch (IOException e) {
      System.err.println("Failed to create sample directory tree: " + e.getMessage());
    } finally {
      // --- teardown: remove the temporary tree ---
      deleteTree(root);
    }
  }

  /**
   * Creates a small sample directory tree rooted at {@code root} for demonstration purposes.
   *
   * <p>Structure created:
   *
   * <pre>
   *   root/
   *   ├── beach.jpg
   *   ├── dog.jpg
   *   └── travel/
   *       ├── paris.jpg
   *       └── tokyo.jpg
   * </pre>
   *
   * @param root the root directory to create; must not already exist
   * @throws IOException if any directory or file cannot be created
   */
  private static void createSampleTree(File root) throws IOException {
    File travel = new File(root, "travel");
    if (!travel.mkdirs()) {
      throw new IOException("Failed to create directory: " + travel.getAbsolutePath());
    }

    createFile(new File(root, "beach.jpg"));
    createFile(new File(root, "dog.jpg"));
    createFile(new File(travel, "paris.jpg"));
    createFile(new File(travel, "tokyo.jpg"));
  }

  /**
   * Creates a new empty file, throwing {@link IOException} if creation fails.
   *
   * @param file the file to create
   * @throws IOException if the file already exists or could not be created
   */
  private static void createFile(File file) throws IOException {
    if (!file.createNewFile()) {
      throw new IOException("Failed to create file: " + file.getAbsolutePath());
    }
  }

  /**
   * Recursively deletes {@code file} and all of its contents.
   *
   * @param file the file or directory to delete
   */
  private static void deleteTree(File file) {
    if (file.isDirectory()) {
      File[] children = file.listFiles();
      if (children != null) {
        for (File child : children) {
          deleteTree(child);
        }
      }
    }
    file.delete();
  }

  /**
   * Iteratively prints the names of all files in the given directory and its subdirectories using a
   * stack-based traversal.
   *
   * <p>The stack is seeded with the direct contents of {@code dir}. On each iteration the top entry
   * is popped: files have their names printed, while directories have their contents pushed onto
   * the stack for later processing.
   *
   * @param dir the root directory from which to start the traversal; must be a valid, readable
   *     directory
   */
  public static void printAllNamesFiles(File dir) {
    File[] entries = dir.listFiles();
    if (entries == null) {
      return; // dir is not a directory or an I/O error occurred
    }
    // Initialize the stack with all files and directories in the starting directory
    Deque<File> filesAndDirs = new ArrayDeque<>(List.of(entries));

    // Continue until there are no more files/directories to process
    while (!filesAndDirs.isEmpty()) {
      // Get the next file or directory from the stack
      File someFileOrDir = filesAndDirs.pop();

      if (someFileOrDir.isFile()) {
        // If it is a file, print its name
        System.out.println(someFileOrDir.getName());
      } else if (someFileOrDir.isDirectory()) {
        // If it is a directory, add its contents to the stack
        filesAndDirs.addAll(Arrays.asList((someFileOrDir.listFiles())));
      }
    }
  }

  /**
   * Recursively prints the names of all files in the given directory and its subdirectories using
   * depth-first traversal.
   *
   * <p>For each entry in {@code dir}: files have their names printed immediately, while directories
   * trigger a recursive call to process their contents.
   *
   * @param dir the current directory to traverse; must be a valid, readable directory
   */
  public static void printAllNamesFilesWithRec(File dir) {
    // Get all files and directories in the current directory
    File[] filesAndDirs = dir.listFiles();
    if (filesAndDirs == null) {
      return; // dir is not a directory or an I/O error occurred
    }

    // Iterate through each file/directory
    for (File someFileOrDir : filesAndDirs) {
      if (someFileOrDir.isFile()) {
        // If it is a file, print its name
        System.out.println(someFileOrDir.getName());
      } else if (someFileOrDir.isDirectory()) {
        // If it is a directory, recursively process its contents
        printAllNamesFilesWithRec(someFileOrDir);
      }
    }
  }
}
