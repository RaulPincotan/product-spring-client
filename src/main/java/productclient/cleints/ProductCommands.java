package productclient.cleints;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import productclient.model.CategoryProduct;
import productclient.model.Product;
import productclient.service.ProductService;

import java.util.Scanner;

@ShellComponent
@RequiredArgsConstructor
public class ProductCommands {
    private final ProductService productService;

    @ShellMethod("Print-all-Products")
    void getProducts() {
        System.out.print("Products:->  ");
        productService.getProducts()
                .stream()
                .forEach(System.out::println);
    }

    @ShellMethod("Print product ")
    void printProduct() {
        System.out.println("Insert product id: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        System.out.println(productService.getProduct(id));
    }

    @ShellMethod("add-product")
    void addProduct() {
        System.out.println(" Insert product name: ");
        Scanner scanner = new Scanner(System.in);
        String productName = scanner.nextLine();
        System.out.println();
        System.out.println(" Insert product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println();
        System.out.println(" Insert product description: ");
        String description = scanner.nextLine();
        System.out.println();
        System.out.println(" Insert product category: ");
        System.out.println();
        String category = scanner.nextLine().toUpperCase();
        productService.save(new Product(123, productName, price, description, CategoryProduct.valueOf(category)));
    }

    @ShellMethod("Delete product")
    void removeProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert id");
        int id = scanner.nextInt();
        productService.removeProduct(id);
    }
}
