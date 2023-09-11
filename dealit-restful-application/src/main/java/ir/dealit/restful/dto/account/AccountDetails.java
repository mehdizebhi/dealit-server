package ir.dealit.restful.dto.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDetails {

    private String Id;
    private String userId;
//    private Wallet wallet;
//    private Inbox inbox;

}
