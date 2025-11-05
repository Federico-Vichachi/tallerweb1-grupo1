package com.tallerwebi.config;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MercadoPagoConfigSpring {

    private static final String ACCESS_TOKEN = "APP_USR-6362078788221946-110116-e7279ee6500edc790ec5217e8dfacc7d-2959447709";


    @PostConstruct
    public void init() {
        try {
            MercadoPagoConfig.setAccessToken(ACCESS_TOKEN);
            System.out.println("Mercado Pago inicializado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
