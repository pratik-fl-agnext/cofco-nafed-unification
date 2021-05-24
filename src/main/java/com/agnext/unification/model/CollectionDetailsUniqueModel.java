package com.agnext.unification.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class CollectionDetailsUniqueModel {
	@JsonProperty("inst_center_id")
	private Long instCenterId;

	@JsonProperty("inst_center_name")
	private String instCenterName;

	@JsonProperty("inst_center_type_id")
	private Long instCenterTypeId;

	@JsonProperty("inst_center_type_name")
	private String instCenterTypeName;

	@JsonProperty("total_quantity")
	private String totalQuantity;

	@JsonProperty("quantity_unit")
	private String quantityUnit;
	
//	@JsonProperty("inst_center_details")
//	private List<InstallationCenterDetails> installationCenterDetails;
	
	@JsonProperty("collection")
	private CollectionModel collection;

	public Long getInstCenterId() {
		return instCenterId;
	}

	public void setInstCenterId(Long instCenterId) {
		this.instCenterId = instCenterId;
	}

	public String getInstCenterName() {
		return instCenterName;
	}

	public void setInstCenterName(String instCenterName) {
		this.instCenterName = instCenterName;
	}

	public Long getInstCenterTypeId() {
		return instCenterTypeId;
	}

	public void setInstCenterTypeId(Long instCenterTypeId) {
		this.instCenterTypeId = instCenterTypeId;
	}

	public String getInstCenterTypeName() {
		return instCenterTypeName;
	}

	public void setInstCenterTypeName(String instCenterTypeName) {
		this.instCenterTypeName = instCenterTypeName;
	}

	public String getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public CollectionModel getCollection() {
		return collection;
	}

	public void setCollection(CollectionModel collection) {
		this.collection = collection;
	}

}
