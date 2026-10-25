package com.tp.shop.web;

import com.tp.shop.model.Product;
import com.tp.shop.service.ProductService;
import com.tp.shop.util.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** API JSON minimale : GET /api/products */
@WebServlet(name = "ProductApiServlet", urlPatterns = "/api/products")
public class ProductApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> products = ProductService.getInstance().findAll();
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (i > 0) {
                json.append(',');
            }
            json.append("{\"id\":").append(p.getId())
                .append(",\"name\":\"").append(JsonUtils.escape(p.getName())).append('"')
                .append(",\"category\":\"").append(JsonUtils.escape(p.getCategory())).append('"')
                .append(",\"price\":").append(p.getPrice().toPlainString())
                .append(",\"stock\":").append(p.getStock())
                .append('}');
        }
        json.append(']');

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json.toString());
    }
}
