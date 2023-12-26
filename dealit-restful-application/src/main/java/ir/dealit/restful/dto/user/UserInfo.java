package ir.dealit.restful.dto.user;

import ir.dealit.restful.dto.enums.AccountType;
import lombok.Builder;
import org.joda.time.DateTime;

import java.util.List;

@Builder
public record UserInfo(
        String displayName,
        String username,
        String phoneNumber,
        String email,
        int connections,
        boolean confirmedEmail,
        boolean confirmedPhone,
        String pictureHref,
        List<AccountType> types,
        DateTime createdAt,
        DateTime updatedAt
) {
}
