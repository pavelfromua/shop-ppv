package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.service.ProductService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    private ProductService productService = (ProductService) INJECTOR
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/admin/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        if (price.matches("\\d+\\.\\d+") || price.matches("\\d+")) {
            productService.create(new Product(name, Double.valueOf(price)));
            resp.sendRedirect(req.getContextPath() + "/products/admin/catalog");
        } else {
            req.setAttribute("name", name);
            req.setAttribute("message", "Enter correct data");
            req.getRequestDispatcher("/WEB-INF/views/products/admin/add.jsp").forward(req, resp);
        }
    }
}
