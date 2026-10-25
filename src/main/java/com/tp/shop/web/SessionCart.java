package com.tp.shop.web;

import com.tp.shop.model.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/** Accès au panier stocké dans la session HTTP. */
final class SessionCart {

    static final String ATTRIBUTE = "cart";

    private SessionCart() {
        // classe utilitaire
    }

    static Cart get(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Cart cart = (Cart) session.getAttribute(ATTRIBUTE);
        if (cart == null) {
            cart = new Cart();
            session.setAttribute(ATTRIBUTE, cart);
        }
        return cart;
    }
}
