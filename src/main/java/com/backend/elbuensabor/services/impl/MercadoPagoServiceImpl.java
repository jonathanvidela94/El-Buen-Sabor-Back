package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.services.MercadoPagoService;
import com.mercadopago.*;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

@Service
public class MercadoPagoServiceImpl implements MercadoPagoService {

    @Value("${MP_ACCESS_TOKEN}")
    String accesTokenMercadoPago;
    String uniqueID = UUID.randomUUID().toString();

    @PostConstruct
    public void initMPConfig(){
        MercadoPagoConfig.setAccessToken(accesTokenMercadoPago);
        System.out.println("Access Token: " + accesTokenMercadoPago);
    }

    @Override
    public Preference generatePreference(Double totalOrder) {
        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .title("Pago de pedido")
                .description("Pago para el pedido")
                .id(uniqueID)
                .quantity(1)
                .currencyId("ARS")
                .unitPrice(BigDecimal.valueOf(totalOrder))
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(Collections.singletonList(itemRequest))
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = null;

        try {
            preference = client.create(preferenceRequest);
        } catch (MPException e) {
            System.out.println("Error MPException: " + e.getMessage());
        } catch (MPApiException e) {
            System.out.println("Error MPApiException: " + e.getMessage());
            System.out.println("Response body: " + e.getApiResponse().getContent());
        }
        return preference;
    }

}
