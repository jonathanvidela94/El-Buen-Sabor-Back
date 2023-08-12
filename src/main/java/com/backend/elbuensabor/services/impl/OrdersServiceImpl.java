package com.backend.elbuensabor.services.impl;

import com.backend.elbuensabor.DTO.OrderDetailDTO;
import com.backend.elbuensabor.DTO.OrdersDTO;
import com.backend.elbuensabor.entities.*;
import com.backend.elbuensabor.mappers.GenericMapper;
import com.backend.elbuensabor.mappers.OrderDetailMapper;
import com.backend.elbuensabor.mappers.OrdersMapper;
import com.backend.elbuensabor.repositories.*;
import com.backend.elbuensabor.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private final OrdersMapper ordersMapper = OrdersMapper.getInstance();

    private final OrderDetailMapper orderDetailMapper = OrderDetailMapper.getInstance();

    public OrdersServiceImpl(GenericRepository<Orders, Long> genericRepository, GenericMapper<Orders, OrdersDTO> genericMapper) {
        super(genericRepository, genericMapper);
    }

    @Override
    public List<OrdersDTO> getAllOrders() throws Exception {

        List<Orders> orders = ordersRepository.findAll();
        List<OrdersDTO> ordersDTOList = new ArrayList<>();

        return ordersDTOList;
    }

    @Override
    public OrdersDTO getOrder(Long id) throws Exception {
        return null;
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

            //Item on Details
            orderDetail.setItem(item);

            //Order on OrderDetails
            orderDetail.setOrder(saveOrder);

            //Save OrderDetail
            orderDetailRepository.save(orderDetail);
        }

        return saveOrder;
    }

    @Override
    public OrdersDTO updateOrder(Long id, OrdersDTO dto) throws Exception {
        return null;
    }
}
