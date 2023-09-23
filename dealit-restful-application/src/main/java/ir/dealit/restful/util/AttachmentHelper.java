package ir.dealit.restful.util;

public class AttachmentHelper {

    public static String getFileExtension(String fileName){
        String[] split = fileName.split("\\.");
        return split[split.length - 1];
    }
}
