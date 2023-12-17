package ir.dealit.restful.service;

import jakarta.validation.constraints.Size;

public interface ExchangeRateCurrencyService {

    double exchangeRate(@Size(min = 3, max = 3) String baseCurrency, @Size(min = 3, max = 3) String targetCurrency);
    double convert(@Size(min = 3, max = 3) String baseCurrency, @Size(min = 3, max = 3) String targetCurrency, double amount);
}
