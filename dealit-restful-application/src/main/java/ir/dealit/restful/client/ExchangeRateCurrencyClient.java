package ir.dealit.restful.client;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(contentType = "application/json")
public interface ExchangeRateCurrencyClient {

    @GetExchange(url = "{apiKey}/latest/{currency}")
    ObjectNode getConversionRates(@PathVariable String apiKey, @PathVariable("currency")  String currencyCode);

    @GetExchange(url = "{apiKey}/pair/{base}/{target}")
    ObjectNode getConversionRate(@PathVariable String apiKey, @PathVariable("base") String baseCurrencyCode, @PathVariable("target") String targetCurrencyCode);

    @GetExchange(url = "{apiKey}/pair/{base}/{target}/{amount}")
    ObjectNode getConversionResult(@PathVariable String apiKey,
                               @PathVariable("base") String baseCurrencyCode,
                               @PathVariable("target") String targetCurrencyCode,
                               @PathVariable("amount") double amount);
}
