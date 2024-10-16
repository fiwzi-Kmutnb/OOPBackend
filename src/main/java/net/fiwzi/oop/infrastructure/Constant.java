package net.fiwzi.oop.infrastructure;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Constant {
    public static final String BASE_PATH = "/home/fiwzi/database/";
    public static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("dcm");
    public static final Pattern FILENAME_PATTERN = Pattern.compile("^(train_images|test_images)(/[a-zA-Z0-9]+)*/\\d+\\.dcm$");
    public static final Pattern FILENAME_PATTERN_FILE = Pattern.compile("^/(train_images|test_images)(/\\d+){2}$");
}
