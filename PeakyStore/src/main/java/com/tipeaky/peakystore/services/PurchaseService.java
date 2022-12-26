package com.tipeaky.peakystore.services;

import com.tipeaky.peakystore.exceptions.CustomAuthenticationException;
import com.tipeaky.peakystore.exceptions.EntityNotFoundException;
import com.tipeaky.peakystore.model.dtos.AddressDTO;
import com.tipeaky.peakystore.model.dtos.CartItemDTO;
import com.tipeaky.peakystore.model.dtos.PurchaseDTO;
import com.tipeaky.peakystore.model.entities.*;
import com.tipeaky.peakystore.model.enums.StatusEnum;
import com.tipeaky.peakystore.model.forms.CartItemForm;
import com.tipeaky.peakystore.model.forms.PurchaseForm;
import com.tipeaky.peakystore.repositories.CartItemRepository;
import com.tipeaky.peakystore.repositories.ProductRepository;
import com.tipeaky.peakystore.repositories.PurchaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper mapper;

    public PurchaseDTO getPurchase(UUID id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        if(purchase.isEmpty()){
            throw new EntityNotFoundException("Pedido não encontrado");
        }

        return mapper.map(purchase.get(), PurchaseDTO.class);
    }

    @Transactional
    public PurchaseDTO save(PurchaseForm purchaseForm) {
        List<CartItemForm> cartItemFormList = purchaseForm.getCartItemList();
        purchaseForm.setCartItemList(null);
        Purchase purchase = mapper.map(purchaseForm,Purchase.class);
        purchase.setOrderMadeDateTime(LocalDateTime.now());
        purchase.setStatus(StatusEnum.ORDER_MADE);

        if(!purchase.getIsDelivered()) {
            purchase.setDeliveryAddress(null);
        } else {
            AddressDTO addressDTO = userService.findAddressById(purchaseForm.getDeliveryAddress().getId());
            purchase.setDeliveryAddress(mapper.map(addressDTO, Address.class));
        }

        BigDecimal totalValue = BigDecimal.ZERO;
        List<CartItem> cartItems = new ArrayList<>();

        for (CartItemForm cartItemForm : cartItemFormList) {

            Product produto = mapper.map(
                    productRepository.findById(cartItemForm.getProduct().getId()), Product.class);

            produto.updateStockQuantity(cartItemForm.getQuantity());

            CartItem cartItem = mapper.map(cartItemForm, CartItem.class);
            cartItem.setProduct(produto);

            cartItem.setTotalPrice(
                    cartItem.getProduct().getSalePrice().multiply( BigDecimal.valueOf(cartItem.getQuantity())));

            totalValue = totalValue.add(cartItem.getTotalPrice());
            cartItems.add(cartItem);
            produto.updateStockQuantity(cartItem.getQuantity());
            cartItem.setPurchase(purchase);
            productRepository.save(produto);
        }

        purchase.setTotalValue(totalValue);

        Purchase purchaseSaved = purchaseRepository.save(purchase);

        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            purchaseSaved.setUser(((User)principal));
        } catch (Exception e) {
            throw new CustomAuthenticationException("usuário não autenticado");
        }

        List<CartItem> savedCartItemList = cartItemRepository.saveAll(cartItems);

        List<CartItemDTO> cartItemDTOList = savedCartItemList.stream().map(cartItem ->
                mapper.map(cartItem, CartItemDTO.class)).toList();
        for(CartItemDTO cartItem : cartItemDTOList) {
            cartItem.getProduct().setDescription(null);
            cartItem.getProduct().setPurchasePrice(null);
            cartItem.getProduct().setStockQuantity(null);
            cartItem.getProduct().setLastUpdateDate(null);
        }

        PurchaseDTO purchaseDTO = mapper.map(purchaseRepository.save(purchaseSaved),PurchaseDTO.class);
        purchaseDTO.setCartItemList(cartItemDTOList);
        return purchaseDTO;
    }
}
