package edu.ccrm.io;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupService {

    private static final String BACKUP_FOLDER = "backup";

    // ✅ Ensure backup folder exists
    static {
        File folder = new File(BACKUP_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    // 🔹 Method to create a timestamped backup of a file
    public static void backupFile(String fileName) {
        try {
            File sourceFile = new File(fileName);
            if (!sourceFile.exists()) {
                System.out.println("⚠️ File not found for backup: " + fileName);
                return;
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String backupFileName = BACKUP_FOLDER + File.separator + fileName + "_" + timestamp + ".bak";

            Files.copy(sourceFile.toPath(), Paths.get(backupFileName), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("✅ Backup created: " + backupFileName);

        } catch (IOException e) {
            System.out.println("❌ Error creating backup for: " + fileName);
            e.printStackTrace();
        }
    }

    // 🔹 Restore the latest backup of a file
    public static void restoreLatest(String originalFileName) {
        try {
            File backupDir = new File(BACKUP_FOLDER);
            File[] backups = backupDir.listFiles((dir, name) -> name.startsWith(originalFileName));

            if (backups == null || backups.length == 0) {
                System.out.println("⚠️ No backup found for: " + originalFileName);
                return;
            }

            // Find the latest backup (by last modified)
            File latestBackup = backups[0];
            for (File f : backups) {
                if (f.lastModified() > latestBackup.lastModified()) {
                    latestBackup = f;
                }
            }

            Files.copy(latestBackup.toPath(), Paths.get(originalFileName), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("✅ Restored from backup: " + latestBackup.getName());

        } catch (IOException e) {
            System.out.println("❌ Error restoring backup for: " + originalFileName);
            e.printStackTrace();
        }
    }
}
