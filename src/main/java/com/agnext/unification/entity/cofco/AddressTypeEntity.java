package com.agnext.unification.entity.cofco;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the im_address_types database table.
 * 
 */
@Entity
@Table(name="im_address_types")
@NamedQuery(name="AddressTypeEntity.findAll", query="SELECT a FROM AddressTypeEntity a")
public class AddressTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_type_id")
	private Long addressTypeId;

	@Column(name="address_desc")
	private String addressDesc;

	@Column(name="address_type")
	private String addressType;

	//bi-directional many-to-one association to CustomerAddressEntity
	@OneToMany(mappedBy="addressType")
	private List<CustomerAddressEntity> customerAddresses;

	//bi-directional many-to-one association to UserAddressEntity
	@OneToMany(mappedBy="addressType")
	private List<UserAddressEntity> userAddresses;

	public AddressTypeEntity() {
	}

	public Long getAddressTypeId() {
		return this.addressTypeId;
	}

	public void setAddressTypeId(Long addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	public String getAddressDesc() {
		return this.addressDesc;
	}

	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}

	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public List<CustomerAddressEntity> getCustomerAddresses() {
		return this.customerAddresses;
	}

	public void setCustomerAddresses(List<CustomerAddressEntity> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public CustomerAddressEntity addCustomerAddress(CustomerAddressEntity customerAddress) {
		getCustomerAddresses().add(customerAddress);
		customerAddress.setAddressType(this);

		return customerAddress;
	}

	public CustomerAddressEntity removeCustomerAddress(CustomerAddressEntity customerAddress) {
		getCustomerAddresses().remove(customerAddress);
		customerAddress.setAddressType(null);

		return customerAddress;
	}

	public List<UserAddressEntity> getUserAddresses() {
		return this.userAddresses;
	}

	public void setUserAddresses(List<UserAddressEntity> userAddresses) {
		this.userAddresses = userAddresses;
	}

	public UserAddressEntity addUserAddress(UserAddressEntity userAddress) {
		getUserAddresses().add(userAddress);
		userAddress.setAddressType(this);

		return userAddress;
	}

	public UserAddressEntity removeUserAddress(UserAddressEntity userAddress) {
		getUserAddresses().remove(userAddress);
		userAddress.setAddressType(null);

		return userAddress;
	}

}