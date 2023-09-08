package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.*;
import com.backend.elbuensabor.entities.*;
import com.backend.elbuensabor.events.StockChangeEvent;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.OrderDetailMapper;
import com.backend.elbuensabor.mappers.OrdersMapper;
import com.backend.elbuensabor.repositories.*;
import com.backend.elbuensabor.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl extends GenericServiceImpl<Orders, OrdersDTO,Long> implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;

    @Autowired
    PaymentTypeRepository paymentTypeRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    ItemCurrentStockRepository itemCurrentStockRepository;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    ItemProductServiceImpl itemProductService;

    @Autowired
    ItemDrinkServiceImpl itemDrinkService;

    private final OrdersMapper ordersMapper = OrdersMapper.getInstance();

    private final OrderDetailMapper orderDetailMapper = OrderDetailMapper.getInstance();

    public OrdersServiceImpl(GenericRepository<Orders, Long> genericRepository, GenericMapper<Orders, OrdersDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }

    @Override
    public List<OrdersDTO> getAllOrders() throws Exception {

        //Orders
        List<Orders> orders = ordersRepository.findAll();
        List<OrdersDTO> ordersDTOList = new ArrayList<>();

        for (Orders order: orders) {
            OrdersDTO ordersDTO = this.ordersMapper.toDTO(order);

            //OrderDetail
            List<OrderDetail> orderDetails = order.getOrderDetails();
            List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();

            for (OrderDetail detail: orderDetails) {
                OrderDetailDTO detailDTO = this.orderDetailMapper.toDTO(detail);

                Item item = detail.getItem();

                //Products
                if (item.getItemType().getId() == 2){
                    detailDTO.setQuantity(detail.getQuantity());
                    detailDTO.setSubtotal(detail.getSubtotal());
                    ItemProductDTO itemProductDTO = itemProductService.getItemProduct(detail.getItem().getId());
                    detailDTO.setItemProduct(itemProductDTO);

                //Drinks
                } else if (item.getItemType().getId() == 3){
                    detailDTO.setQuantity(detail.getQuantity());
                    detailDTO.setSubtotal(detail.getSubtotal());
                    ItemDrinkDTO itemDrinkDTO = itemDrinkService.getItemDrink(detail.getItem().getId());
                    detailDTO.setItemDrink(itemDrinkDTO);
                }

                //Add item info to OrderDetail
                orderDetailDTOList.add(detailDTO);
            }

            ordersDTO.setOrderDetails(orderDetailDTOList);
            ordersDTOList.add(ordersDTO);
        }
        return ordersDTOList;
    }

    @Override
    public List<OrdersDTO> getAllOrdersByCustomerId(Long id) throws Exception {

        //Orders
        List<Orders> orders = ordersRepository.findAllByCustomerId(id);
        List<OrdersDTO> ordersDTOList = new ArrayList<>();

        for (Orders order: orders) {
            OrdersDTO ordersDTO = this.ordersMapper.toDTO(order);

            //OrderDetail
            List<OrderDetail> orderDetails = order.getOrderDetails();
            List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();

            for (OrderDetail detail: orderDetails) {
                OrderDetailDTO detailDTO = this.orderDetailMapper.toDTO(detail);

                Item item = detail.getItem();

                //Products
                if (item.getItemType().getId() == 2){
                    detailDTO.setQuantity(detail.getQuantity());
                    detailDTO.setSubtotal(detail.getSubtotal());
                    ItemProductDTO itemProductDTO = itemProductService.getItemProduct(detail.getItem().getId());
                    detailDTO.setItemProduct(itemProductDTO);

                    //Drinks
                } else if (item.getItemType().getId() == 3){
                    detailDTO.setQuantity(detail.getQuantity());
                    detailDTO.setSubtotal(detail.getSubtotal());
                    ItemDrinkDTO itemDrinkDTO = itemDrinkService.getItemDrink(detail.getItem().getId());
                    detailDTO.setItemDrink(itemDrinkDTO);
                }

                //Add item info to OrderDetail
                orderDetailDTOList.add(detailDTO);
            }

            ordersDTO.setOrderDetails(orderDetailDTOList);
            ordersDTOList.add(ordersDTO);
        }
        return ordersDTOList;
    }

    @Override
    public OrdersDTO getOrder(Long id) throws Exception {

        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new Exception("Producto no encontrado con el ID: " + id));

        OrdersDTO ordersDTO = ordersMapper.toDTO(order);

        //OrderDetail
        List<OrderDetail> orderDetails = order.getOrderDetails();
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();

        for (OrderDetail detail: orderDetails) {
            OrderDetailDTO detailDTO = this.orderDetailMapper.toDTO(detail);

            Item item = detail.getItem();

            //Products
            if (item.getItemType().getId() == 2){
                detailDTO.setQuantity(detail.getQuantity());
                detailDTO.setSubtotal(detail.getSubtotal());
                ItemProductDTO itemProductDTO = itemProductService.getItemProduct(detail.getItem().getId());
                detailDTO.setItemProduct(itemProductDTO);

                //Drinks
            } else if (item.getItemType().getId() == 3){
                detailDTO.setQuantity(detail.getQuantity());
                detailDTO.setSubtotal(detail.getSubtotal());
                ItemDrinkDTO itemDrinkDTO = itemDrinkService.getItemDrink(detail.getItem().getId());
                detailDTO.setItemDrink(itemDrinkDTO);
            }
            //Add item info to OrderDetail
            orderDetailDTOList.add(detailDTO);
        }
        ordersDTO.setOrderDetails(orderDetailDTOList);

        return ordersDTO;
    }

    @Override
    public Orders saveOrder(OrdersDTO dto) throws Exception {

        // Convertir DTO a entidad
        Orders order = ordersMapper.toEntity(dto);

        //Customer
        if (dto.getCustomerId() != null) {
            if (customerRepository.existsById(dto.getCustomerId())) {
                Customer customer = customerRepository.findById(dto.getCustomerId()).get();
                order.setCustomer(customer);
            } else {
                throw new Exception("El cliente no existe");
            }
        }

        //Delivery Type
        if (dto.getDeliveryTypeId() != null) {
            if (deliveryTypeRepository.existsById(dto.getDeliveryTypeId())) {
                DeliveryType deliveryType = deliveryTypeRepository.findById(dto.getDeliveryTypeId()).get();
                order.setDeliveryType(deliveryType);
            } else {
                throw new Exception("El tipo de delivery no existe");
            }
        }

        //PaymentType
        if (dto.getPaymentTypeId() != null) {
            if (paymentTypeRepository.existsById(dto.getPaymentTypeId())) {
                PaymentType paymentType = paymentTypeRepository.findById(dto.getPaymentTypeId()).get();
                order.setPaymentType(paymentType);
            } else {
                throw new Exception("El tipo de pago no existe");
            }
        }

        //Order Status
        if (dto.getOrderStatusId() != null) {
            if (orderStatusRepository.existsById(dto.getOrderStatusId())) {
                OrderStatus orderStatus = orderStatusRepository.findById(dto.getOrderStatusId()).get();
                order.setOrderStatus(orderStatus);
            } else {
                throw new Exception("El estado de orden no existe");
            }
        }

        Orders saveOrder = ordersRepository.save(order);

        //Order Date
        saveOrder.setOrderDate(LocalDateTime.now());

        //Estimated Time init
        int orderPrepTime = 0;

        //Order Detail
        OrderDetail orderDetail;
        for (OrderDetailDTO orderDetailDTO : dto.getOrderDetails()){
            orderDetail = orderDetailMapper.toEntity(orderDetailDTO);

            //Item
            Item item = null;
            if(orderDetailDTO.getItemId() != null && itemRepository.existsById(orderDetailDTO.getItemId())){
                item = itemRepository.findById(orderDetailDTO.getItemId()).get();
            } else {
                throw new Exception("Item invalido o no existente");
            }

            //Prep Time & Stock update
            //Food
            if (item.getItemType().getId() == 2){
                Recipe recipe = recipeRepository.findByItemId(item.getId());
                Integer itemPrepTime = recipe.getPreparationTime();

                //Get Max prepTime inside orderDetail
                if(itemPrepTime > orderPrepTime) {
                    orderPrepTime = itemPrepTime;
                }

                //New Stock for Food products
                for (RecipeDetail recipeDetail: recipe.getRecipeDetails()) {

                    int totalQuantityUsed = recipeDetail.getQuantity() * orderDetail.getQuantity();
                    ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(recipeDetail.getItem().getId());

                    // Subtract "-"
                    int newCurrentStock = latestItemCurrentStock.getCurrentStock() - totalQuantityUsed;

                    if (newCurrentStock != latestItemCurrentStock.getCurrentStock()) {
                        ItemCurrentStock newItemCurrentStock = new ItemCurrentStock();
                        newItemCurrentStock.setCurrentStock(newCurrentStock);
                        newItemCurrentStock.setCurrentStockDate(LocalDateTime.now());
                        newItemCurrentStock.setItem(recipeDetail.getItem());
                        itemCurrentStockRepository.save(newItemCurrentStock);

                        eventPublisher.publishEvent(new StockChangeEvent(this, recipeDetail.getItem().getId()));
                    }

                }

                //Drinks
            } else {

                ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(item.getId());
                int totalQuantityUsed = orderDetail.getQuantity();

                // Subtract "-"
                int newCurrentStock = latestItemCurrentStock.getCurrentStock() - totalQuantityUsed;

                if (newCurrentStock != latestItemCurrentStock.getCurrentStock()) {
                    ItemCurrentStock newItemCurrentStock = new ItemCurrentStock();
                    newItemCurrentStock.setCurrentStock(newCurrentStock);
                    newItemCurrentStock.setCurrentStockDate(LocalDateTime.now());
                    newItemCurrentStock.setItem(item);
                    itemCurrentStockRepository.save(newItemCurrentStock);
                }
            }

            //Item on Details
            orderDetail.setItem(item);

            //Order on OrderDetails
            orderDetail.setOrder(saveOrder);

            //Save OrderDetail
            orderDetailRepository.save(orderDetail);
        }

        //Get all orders on kitchen
        List<Orders> ordersKitchen = ordersRepository.findAllByOrderStatusId(2L);
        int ordersOnKitchenPrepTime = 0;
        for (Orders ordKit: ordersKitchen) {
            ordersOnKitchenPrepTime += ordKit.getEstimatedTime();
        }

        //Get all Cooks who are working now
        List<Customer> cooksList = customerRepository.findAllLoggedCocinero();
        int cooks = 0;

        //Avoid dividing by 0
        if (!cooksList.isEmpty()) {
            cooks = cooksList.size();
        } else {
            cooks = 1;
        }

        //Calculation of Estimated Time of the order
        orderPrepTime = (orderPrepTime + (ordersOnKitchenPrepTime / cooks));

        //Delivery
        if (saveOrder.getDeliveryType().getId() == 1){
            orderPrepTime += 10;
        }

        //Estimated Time
        saveOrder.setEstimatedTime(orderPrepTime);

        //Update order with Estimated Time
        saveOrder = ordersRepository.save(saveOrder);

        return saveOrder;
    }

    @Override
    public OrdersDTO updateOrder(Long id, OrdersDTO dto) throws Exception {

        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new Exception("Producto no encontrado con el ID: " + id));

        //Paid
        order.setPaid(dto.isPaid());

        //Estimated Time
        order.setEstimatedTime(dto.getEstimatedTime());

        //Order Status
        if (dto.getOrderStatusId() != null) {
            if (orderStatusRepository.existsById(dto.getOrderStatusId())) {
                OrderStatus orderStatus = orderStatusRepository.findById(dto.getOrderStatusId()).get();
                order.setOrderStatus(orderStatus);
            } else {
                throw new Exception("El estado de orden no existe");
            }
        }

        Orders updateOrder = ordersRepository.save(order);

        return ordersMapper.toDTO(updateOrder);
    }

    @Override
    public OrdersDTO cancelOrder(Long id, OrdersDTO dto) throws Exception {
        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new Exception("Producto no encontrado con el ID: " + id));

        //Cancel
        order.setCancelled(dto.isCancelled());

        //Order Status
        if (dto.getOrderStatusId() != null) {
            if (orderStatusRepository.existsById(dto.getOrderStatusId())) {
                OrderStatus orderStatus = orderStatusRepository.findById(dto.getOrderStatusId()).get();
                order.setOrderStatus(orderStatus);
            } else {
                throw new Exception("El estado de orden no existe");
            }
        }

        //Order Detail
        OrderDetail orderDetail;
        for (OrderDetailDTO orderDetailDTO : dto.getOrderDetails()){
            orderDetail = orderDetailMapper.toEntity(orderDetailDTO);

            //Item
            Item item = null;
            if(orderDetailDTO.getItemId() != null && itemRepository.existsById(orderDetailDTO.getItemId())){
                item = itemRepository.findById(orderDetailDTO.getItemId()).get();
            } else {
                throw new Exception("Item invalido o no existente");
            }

            //Prep Time & Stock update
            //Food
            if (item.getItemType().getId() == 2){
                Recipe recipe = recipeRepository.findByItemId(item.getId());

                //New Stock for Food products
                for (RecipeDetail recipeDetail: recipe.getRecipeDetails()) {

                    int totalQuantityUsed = recipeDetail.getQuantity() * orderDetail.getQuantity();
                    ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(recipeDetail.getItem().getId());

                    // Sum "+"
                    int newCurrentStock = latestItemCurrentStock.getCurrentStock() + totalQuantityUsed;

                    if (newCurrentStock != latestItemCurrentStock.getCurrentStock()) {
                        ItemCurrentStock newItemCurrentStock = new ItemCurrentStock();
                        newItemCurrentStock.setCurrentStock(newCurrentStock);
                        newItemCurrentStock.setCurrentStockDate(LocalDateTime.now());
                        newItemCurrentStock.setItem(recipeDetail.getItem());
                        itemCurrentStockRepository.save(newItemCurrentStock);

                        eventPublisher.publishEvent(new StockChangeEvent(this, recipeDetail.getItem().getId()));
                    }

                }

                //Drinks
            } else {

                ItemCurrentStock latestItemCurrentStock = itemCurrentStockRepository.findLatestByItemId(item.getId());
                int totalQuantityUsed = orderDetail.getQuantity();

                // Sum "+"
                int newCurrentStock = latestItemCurrentStock.getCurrentStock() + totalQuantityUsed;

                if (newCurrentStock != latestItemCurrentStock.getCurrentStock()) {
                    ItemCurrentStock newItemCurrentStock = new ItemCurrentStock();
                    newItemCurrentStock.setCurrentStock(newCurrentStock);
                    newItemCurrentStock.setCurrentStockDate(LocalDateTime.now());
                    newItemCurrentStock.setItem(item);
                    itemCurrentStockRepository.save(newItemCurrentStock);
                }
            }
        }

        Orders cancelOrder = ordersRepository.save(order);

        return ordersMapper.toDTO(cancelOrder);
    }

    @Override
    public List<CustomerSummaryDTO> getCustomerSummaryBetweenDates(Date startDate, Date endDate) throws Exception {
        try {
            List<CustomerSummaryDTO> customerSummaryDTOList = ordersRepository.getCustomerSummaryBetweenDates(startDate, endDate);
            return customerSummaryDTOList;
        } catch (Exception e) {
            throw new Exception("Ha ocurrido un error al obtener el resumen de clientes");
        }
    }

    @Override
    public List<ItemSalesDTO>getItemsWithSoldQuantitiesBetweenDates(Date startDate, Date endDate) throws Exception {
        try {
            List<ItemSalesDTO> itemSalesDTOS = ordersRepository.getItemsWithSoldQuantitiesAndOrderTypeAndDateRange(startDate, endDate);
            return itemSalesDTOS;
        } catch (Exception e) {
            throw new Exception("Ha ocurrido un error al obtener el resumen de productos vendidos");
        }
    }

}
