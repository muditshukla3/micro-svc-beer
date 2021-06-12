package com.ms.services.order;

import com.ms.config.JMSConfig;
import com.ms.domain.events.ValidateBeerOrderRequest;
import com.ms.domain.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JMSConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateBeerOrderRequest validateBeerOrderRequest){

        Boolean isValid = beerOrderValidator.validateOrder(validateBeerOrderRequest.getBeerOrderDto());

        jmsTemplate.convertAndSend(JMSConfig.VALIDATE_ORDER_RESPONSE,
                ValidateOrderResult.builder()
                .isValid(isValid)
                .orderId(validateBeerOrderRequest.getBeerOrderDto().getId())
                 .build());
    }
}
