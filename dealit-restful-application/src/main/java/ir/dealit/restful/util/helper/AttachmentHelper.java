package ir.dealit.restful.util.helper;

public class AttachmentHelper {

    public static String getFileExtension(String fileName){
        String[] split = fileName.split("\\.");
        return split[split.length - 1];
    }
}
