package com.agnext.unification.model;

import java.io.Serializable;

public class ImageModel implements Serializable {

  private String imageId;
  private String url;
  private String fileName;
  private String fileExtension;
  private Boolean isImageExists;

  public String getImageId() {
    return imageId;
  }

  public void setImageId(String imageId) {
    this.imageId = imageId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileExtension() {
    return fileExtension;
  }

  public void setFileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
  }

  @Override
  public String toString() {
    return "ImageModel{" +
        "imageId='" + imageId + '\'' +
        ", url='" + url + '\'' +
        ", fileName='" + fileName + '\'' +
        ", fileExtension='" + fileExtension + '\'' +
        '}';
  }

public Boolean getIsImageExists() {
	return isImageExists;
}

public void setIsImageExists(Boolean isImageExists) {
	this.isImageExists = isImageExists;
}
}
