package ir.dealit.restful.api;

import ir.dealit.restful.dto.account.AccountActivity;
import ir.dealit.restful.dto.account.AccountOverview;
import ir.dealit.restful.dto.account.TinyStats;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/v1/account")
public interface AccountApi {

    @GetMapping("/{accountId}")
    ResponseEntity<AccountOverview> getAccountOverview(
            @PathVariable("accountId") ObjectId id
    );

//    @GetMapping("/stats/{accountId}")
//    ResponseEntity<TinyStats> getAccountStats(
//            @PathVariable("accountId") ObjectId id
//    );

//    @GetMapping("/activities/{accountId}")
//    ResponseEntity<List<AccountActivity>> getAccountActivities(
//            @PathVariable("accountId") ObjectId id
//    );


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
