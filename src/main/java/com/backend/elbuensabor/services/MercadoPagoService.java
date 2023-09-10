package com.backend.elbuensabor.services;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

public interface MercadoPagoService {
    Preference generatePreference(Double totalOrder) throws MPException, MPApiException;

}
