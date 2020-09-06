package com.journi.challenge.service;

import com.journi.challenge.service.CurrencyConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class CurrencyConverterTest {

    @Mock
    private CurrencyConverter currencyConverter;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(currencyConverter, "supportedCountriesCurrency", new HashMap<String, String>(){{
            put("AT", "EUR");
            put("BR", "BRL");
        }});
        Mockito.lenient().when(currencyConverter.getCurrencyForCountryCode(anyString())).thenCallRealMethod();

        ReflectionTestUtils.setField(currencyConverter, "currencyEurRate", new HashMap<String, Double>(){{
            put("EUR", 1.0);
            put("BRL", 5.1480);
        }});
        Mockito.lenient().when(currencyConverter.convertEurToCurrency(anyString(), anyDouble())).thenCallRealMethod();
    }

    @Test
    void findCurrencyCodeForSupportedCountry() {
        assertEquals("EUR", currencyConverter.getCurrencyForCountryCode("AT"));
        assertEquals("BRL", currencyConverter.getCurrencyForCountryCode("BR"));
    }

    @Test
    void findCurrencyCodeForNonSupportedCountry() {
        assertEquals("EUR", currencyConverter.getCurrencyForCountryCode("CH"));
    }

    @Test
    void convertEurValueToSupportedCurrency() {
        assertEquals(25.0, currencyConverter.convertEurToCurrency("EUR", 25.0));
        assertEquals(25.0 * 5.1480, currencyConverter.convertEurToCurrency("BRL", 25.0));
        assertEquals(25.0, currencyConverter.convertEurToCurrency("AT", 25.0));
    }
}