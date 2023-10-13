package ir.dealit.restful.api;

import ir.dealit.restful.dto.account.AccountOverview;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/v1/accounts")
public interface AccountApi {

    @GetMapping("/{accountId}")
    ResponseEntity<AccountOverview> getAccountOverview(
            @PathVariable("accountId") ObjectId id
    );

//    @GetMapping("/{userId}")
//    ResponseEntity<AccountOverview> getAccountOverview(
//            @PathVariable("userId") ObjectId userId
//    );

//    @PatchMapping("/{username}")
//    ResponseEntity<AccountDetails> updateAccount(
//            @PathVariable("username") String username,
//            @RequestBody AccountDetails accountDetails
//    );

}
