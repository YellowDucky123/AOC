import java.util.*;

public class dirEntry {
    Map<String, List<String>> dirList = new HashMap<>();

    public dirEntry() {}

    public void insertDir(String parentDir, String subDir) {
        // Prevent circular references
        if (parentDir.equals(subDir) || hasCircularReference(parentDir, subDir, new HashSet<>())) {
            // throw new IllegalArgumentException("Circular reference detected!");
            return;
        }
        dirList.computeIfAbsent(parentDir, key -> new ArrayList<>()).add(subDir);
    }

    private boolean hasCircularReference(String parentDir, String subDir, Set<String> visitedDirs) {
        if (visitedDirs.contains(subDir)) {
            return true;
        }
        visitedDirs.add(subDir);
        if (!dirList.containsKey(subDir)) {
            return false;
        }
        for (String child : dirList.get(subDir)) {
            if (child.equals(parentDir) || hasCircularReference(parentDir, child, visitedDirs)) {
                return true;
            }
        }
        return false;
    }

    public Map<String, List<String>> getList() {
        return dirList;
    }

    // Update the records, and in the end, return the memory storage of the root using Long
    public Long updateRecord(Map<String, Long> memDir, String currDir) {
        // If the directory is not present in the map, return its memory value if available
        Long currentMemory = memDir.getOrDefault(currDir, 0L);
        Long totalMemory = currentMemory + 
            dirList.getOrDefault(currDir, new ArrayList<>())
            .stream()
            .map(subDir -> updateRecord(memDir, subDir))
            .reduce(0L, Long::sum); // Sum up the memory of subdirectories

        // Update the memory map for the current directory
        memDir.put(currDir, totalMemory);
        return totalMemory;
    }

    // Print the directory list in a readable format
    public void printList() {
        dirList.forEach((key, list) -> {
            System.out.println(key + ":");
            list.forEach(System.out::println);
            System.out.println();
        });
    }
}
