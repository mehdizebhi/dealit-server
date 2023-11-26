package ir.dealit.restful.dto.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

@Data
@RequiredArgsConstructor
public class UpdateUser {
    private ObjectId id;
    private String username;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
