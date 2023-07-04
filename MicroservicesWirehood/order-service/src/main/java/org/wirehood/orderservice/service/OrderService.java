package org.wirehood.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.wirehood.orderservice.dto.InventoryResponse;
import org.wirehood.orderservice.dto.OrderLineItemsDto;
import org.wirehood.orderservice.dto.OrderRequest;
import org.wirehood.orderservice.model.Order;
import org.wirehood.orderservice.model.OrderLineItems;
import org.wirehood.orderservice.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItemsList);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map((OrderLineItems::getSkuCode)).collect(Collectors.toList());

        //Call Inventory-service and place order if product exists
        //making a synchronous req to Inv to the getMapping which returns a Boolean
        InventoryResponse[] inventoryResponseList = webClientBuilder.build().get().uri("http://inventory-service/api/inventory", uriBuilder ->
            uriBuilder.queryParam("skuCode", skuCodes).build()
                ).retrieve()
                        .bodyToMono(InventoryResponse[].class).block();

        boolean allProductsInStock = Arrays.stream(inventoryResponseList).allMatch(InventoryResponse::getIsInStock);

        if(allProductsInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock");
        }

        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
