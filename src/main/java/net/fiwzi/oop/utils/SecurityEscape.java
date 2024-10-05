package net.fiwzi.oop.utils;

import net.fiwzi.oop.infrastructure.Constant;
import org.springframework.util.StringUtils;

public class SecurityEscape {

    public static String Security(String filename) {
        String sanitized = StringUtils.cleanPath(filename);
        if (sanitized.contains("..") || !Constant.FILENAME_PATTERN.matcher(sanitized).matches()) {
            throw new SecurityException("Invalid filename");
        }
        String extension = StringUtils.getFilenameExtension(sanitized);
        if (extension == null || !Constant.ALLOWED_EXTENSIONS.contains(extension.toLowerCase())) {
            throw new SecurityException("File type not allowed");
        }
        return sanitized;
    }
    public static String SecurityFile(String filename) {
        String sanitized = StringUtils.cleanPath(filename);
//        if (sanitized.contains("..") || !Constant.FILENAME_PATTERN_FILE.matcher(sanitized).matches()) {
//            throw new SecurityException("Invalid filename");
//        }
        return sanitized;
    }
}
