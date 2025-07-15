package com.data.wrapper;
import com.data.modal.entity.Product;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "products")
public class ProductList {
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Product> product;

    public ProductList(List<Product> product) {
        this.product = product;
    }

    public List<Product> getProduct() {
        return product;
    }
}