package com.tp.shop.web;

import com.tp.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Page catalogue : liste, recherche et filtre par catégorie. */
@WebServlet(name = "ProductListServlet", urlPatterns = "/products")
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ProductService service = ProductService.getInstance();
        String query = req.getParameter("q");
        String category = req.getParameter("category");

        req.setAttribute("products", service.search(query, category));
        req.setAttribute("categories", service.getCategories());
        req.setAttribute("q", query);
        req.setAttribute("selectedCategory", category);
        req.setAttribute("cart", SessionCart.get(req));
        req.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
    }
}
