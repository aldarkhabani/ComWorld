package com.example.myapplication;

public class InvoiceDetail {
    final Integer id,invoice_id,material_id,number;
    final double price,total;

    public InvoiceDetail(Integer id, Integer invoice_id, Integer material_id, Integer number, double price, double total) {
        this.id = id;
        this.invoice_id = invoice_id;
        this.material_id = material_id;
        this.number = number;
        this.price = price;
        this.total = total;
    }
}
