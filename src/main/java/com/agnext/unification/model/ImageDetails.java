package com.agnext.unification.model;

import java.util.List;

public class ImageDetails {

  private String totalCount;
  private List<ImageModel> imageList;

  public String getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(String totalCount) {
    this.totalCount = totalCount;
  }

  public List<ImageModel> getImageList() {
    return imageList;
  }

  public void setImageList(List<ImageModel> imageList) {
    this.imageList = imageList;
  }
}
