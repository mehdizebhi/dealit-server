package ir.dealit.restful.dto.account;

import ir.dealit.restful.dto.enums.AccountType;

import java.util.List;

public record AccountInfo(
        String username,
        String email,
        String phoneNumber,
        String displayName,
        String pictureHref,
        List<AccountType> types) {
}
