package com.tp.shop.web;

import com.tp.shop.model.Cart;
import com.tp.shop.model.Order;
import com.tp.shop.service.CheckoutService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/** Validation de la commande : formulaire (GET) puis création de la commande (POST). */
@WebServlet(name = "CheckoutServlet", urlPatterns = "/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Cart cart = SessionCart.get(req);
        if (cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Cart cart = SessionCart.get(req);
        if (cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        CheckoutService service = CheckoutService.getInstance();
        Map<String, String> errors = service.validate(name, email, address);
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("cart", cart);
            req.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(req, resp);
            return;
        }

        Order order = service.placeOrder(cart, name, email, address);
        req.setAttribute("order", order);
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("/WEB-INF/views/confirmation.jsp").forward(req, resp);
    }
}
