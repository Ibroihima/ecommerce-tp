package com.tp.shop.web;

import com.tp.shop.model.Cart;
import com.tp.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Gestion du panier : affichage (GET) et actions add / update / remove / clear (POST). */
@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("cart", SessionCart.get(req));
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = SessionCart.get(req);
        String action = req.getParameter("action") == null ? "" : req.getParameter("action");
        int id = parseInt(req.getParameter("id"), -1);

        switch (action) {
            case "add":
                int quantity = Math.max(1, parseInt(req.getParameter("quantity"), 1));
                ProductService.getInstance().findById(id).ifPresent(p -> cart.add(p, quantity));
                break;
            case "update":
                cart.updateQuantity(id, parseInt(req.getParameter("quantity"), 1));
                break;
            case "remove":
                cart.remove(id);
                break;
            case "clear":
                cart.clear();
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue");
                return;
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    private static int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
