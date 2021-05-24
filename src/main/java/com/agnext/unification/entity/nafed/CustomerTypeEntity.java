package com.agnext.unification.entity.nafed;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the im_customer_type database table.
 * 
 */
@Entity
@Table(name="im_customer_type")
@NamedQuery(name="CustomerTypeEntity.findAll", query="SELECT c FROM CustomerTypeEntity c")
public class CustomerTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_type_id")
	private Long customerTypeId;

	@Column(name="customer_type")
	private String customerType;

	//bi-directional many-to-one association to CustomerEntity
	@OneToMany(mappedBy="customerType", cascade={CascadeType.ALL})
	private List<CustomerEntity> customers;

	public CustomerTypeEntity() {
	}

	public Long getCustomerTypeId() {
		return this.customerTypeId;
	}

	public void setCustomerTypeId(Long customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public List<CustomerEntity> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<CustomerEntity> customers) {
		this.customers = customers;
	}

	public CustomerEntity addCustomer(CustomerEntity customer) {
		getCustomers().add(customer);
		customer.setCustomerType(this);

		return customer;
	}

	public CustomerEntity removeCustomer(CustomerEntity customer) {
		getCustomers().remove(customer);
		customer.setCustomerType(null);

		return customer;
	}

}