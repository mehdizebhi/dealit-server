package ir.dealit.restful.util.helper;

import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.module.attachment.entity.AttachmentEntity;

public class AttachmentHelper {

    public static String getKey(Attachment attachment) {
        return attachment.getFileId() + "." + AttachmentHelper.getFileExtension(attachment.getFileName());
    }

    public static String getKey(AttachmentEntity attachment) {
        return attachment.getFileId() + "." + AttachmentHelper.getFileExtension(attachment.getFileName());
    }

    public static String getFileExtension(String fileName){
        String[] split = fileName.split("\\.");
        return split[split.length - 1];
    }
}
