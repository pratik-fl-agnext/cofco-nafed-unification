package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TotalCollectionsModel {
	
	@JsonProperty("total_collections")
	List<TotalCollectionModel> totalCollectionModel;

	public List<TotalCollectionModel> getTotalCollectionModel() {
		return totalCollectionModel;
	}

	public void setTotalCollectionModel(List<TotalCollectionModel> totalCollectionModel) {
		this.totalCollectionModel = totalCollectionModel;
	}

}
