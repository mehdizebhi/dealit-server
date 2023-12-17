package ir.dealit.restful.dto.account;

import ir.dealit.restful.dto.enums.AccountType;
import lombok.Builder;
import org.joda.time.DateTime;

import java.util.List;

@Builder
public record AccountOverview(
        String displayName,
        String username,
        String phoneNumber,
        String email,
        double balance,
        int connections,
        boolean confirmedEmail,
        boolean confirmedPhone,
        List<AccountType> types,
        DateTime createdAt,
        DateTime updatedAt
) {
}
