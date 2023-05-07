package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoice_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceOrder extends GenericEntity{
    @Column(name = "invoice_number")
    private Long invoiceNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_orders")
    private Orders orders;
}
