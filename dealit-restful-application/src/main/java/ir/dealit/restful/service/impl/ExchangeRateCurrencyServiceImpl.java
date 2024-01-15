package ir.dealit.restful.service.impl;

import ir.dealit.restful.client.ExchangeRateCurrencyClient;
import ir.dealit.restful.service.ExchangeRateCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeRateCurrencyServiceImpl implements ExchangeRateCurrencyService {

    private final ExchangeRateCurrencyClient client;

    @Value("${services.exchangerate.apiKey}")
    private String apiKey;

    @Override
    public double exchangeRate(String baseCurrency, String targetCurrency) {
        return client.getConversionRate(apiKey, baseCurrency, targetCurrency).get("conversion_rate").asDouble();
    }

    @Override
    public double convert(String baseCurrency, String targetCurrency, double amount) {
        if (baseCurrency.equals(targetCurrency)) {
            return amount;
        }
        var conversionRate = client.getConversionRate(apiKey, baseCurrency, targetCurrency).get("conversion_rate").asDouble();
        return amount * conversionRate;
    }
}
