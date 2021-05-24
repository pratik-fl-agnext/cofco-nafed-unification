package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Value Object class for {@link Devices}.<br>
 * Used to transfer objects
 * 
 * @author PIYUSH RANJAN
 * @since 1.0
 */
@JsonInclude(Include.NON_NULL)
public class DevicesVO {

    private String name;

    private String serialNo;

    private String scalingFactor;

    private Long cropId;

    private Long id;

    private Long ccId;

    private String ccName;

    private String cropName;

    private Long fId;

    private Long dId;

    private Integer sampleId;

    private List<Double> sfValues;

    private Long sampleType;

    private Long cId;

    private String clientName;

    private Long clientId;
    
    private String allocation;
    
    private String deviceType;
    
    private Long scanCount;
    
    private String awake;
    
    private Integer deviceStatus;
    
    private String statusMessage;
    
 
    private List<PackagesVO>  packages;
	@JsonProperty("device_cost")
	private Double devicePrice;
	@JsonProperty("device_type_name")
	private String deviceTypeName;
	@JsonProperty("device_type_id")
	private Long deviceTypeId;
	
	private Long deviceId;
	private String deviceName;
	
	List<PackageCommoditiesPriceVO> pcList;
    
    

    /**
	 * @return the packages
	 */
	public List<PackagesVO> getPackages() {
		return packages;
	}

	/**
	 * @param packages the packages to set
	 */
	public void setPackages(List<PackagesVO> packages) {
		this.packages = packages;
	}

	/**
	 * @return the devicePrice
	 */
	public Double getDevicePrice() {
		return devicePrice;
	}

	/**
	 * @param devicePrice the devicePrice to set
	 */
	public void setDevicePrice(Double devicePrice) {
		this.devicePrice = devicePrice;
	}

	/**
	 * @return the deviceTypeName
	 */
	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	/**
	 * @param deviceTypeName the deviceTypeName to set
	 */
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	/**
	 * @return the deviceTypeId
	 */
	public Long getDeviceTypeId() {
		return deviceTypeId;
	}

	/**
	 * @param deviceTypeId the deviceTypeId to set
	 */
	public void setDeviceTypeId(Long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	/**
	 * @return the deviceId
	 */
	public Long getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @return the pcList
	 */
	public List<PackageCommoditiesPriceVO> getPcList() {
		return pcList;
	}

	/**
	 * @param pcList the pcList to set
	 */
	public void setPcList(List<PackageCommoditiesPriceVO> pcList) {
		this.pcList = pcList;
	}

	/**
	 * @return the scanCount
	 */
	public Long getScanCount() {
		return scanCount;
	}

	/**
	 * @param scanCount the scanCount to set
	 */
	public void setScanCount(Long scanCount) {
		this.scanCount = scanCount;
	}


    public String getDeviceType() {

		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the ccId
     */
    public Long getCcId() {
        return ccId;
    }

    /**
     * @param ccId
     *            the ccId to set
     */
    public void setCcId(Long ccId) {
        this.ccId = ccId;
    }

    /**
     * @return the ccName
     */
    public String getCcName() {
        return ccName;
    }

    /**
     * @param ccName
     *            the ccName to set
     */
    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the serialNo
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * @param serialNo
     *            the serialNo to set
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * @return the scalingFactor
     */
    public String getScalingFactor() {
        return scalingFactor;
    }

    /**
     * @param scalingFactor
     *            the scalingFactor to set
     */
    public void setScalingFactor(String scalingFactor) {
        this.scalingFactor = scalingFactor;
    }

    /**
     * @return the cropId
     */
    public Long getCropId() {
        return cropId;
    }

    /**
     * @param cropId
     *            the cropId to set
     */
    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    /**
     * @return the fId
     */
    public Long getfId() {
        return fId;
    }

    /**
     * @param fId
     *            the fId to set
     */
    public void setfId(Long fId) {
        this.fId = fId;
    }

    /**
     * @return the dId
     */
    public Long getdId() {
        return dId;
    }

    /**
     * @param dId
     *            the dId to set
     */
    public void setdId(Long dId) {
        this.dId = dId;
    }

    /**
     * @return the cropName
     */
    public String getCropName() {
        return cropName;
    }

    /**
     * @param cropName
     *            the cropName to set
     */
    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    /**
     * @return the sampleId
     */
    public Integer getSampleId() {
        return sampleId;
    }

    /**
     * @param sampleId
     *            the sampleId to set
     */
    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    /**
     * @return the sfValues
     */
    public List<Double> getSfValues() {
        return sfValues;
    }

    /**
     * @param sfValues
     *            the sfValues to set
     */
    public void setSfValues(List<Double> sfValues) {
        this.sfValues = sfValues;
    }

    /**
     * @return the sampleType
     */
    public Long getSampleType() {
        return sampleType;
    }

    /**
     * @param sampleType
     *            the sampleType to set
     */
    public void setSampleType(Long sampleType) {
        this.sampleType = sampleType;
    }

    /**
     * @return the cId
     */
    public Long getcId() {
        return cId;
    }

    /**
     * @param cId
     *            the cId to set
     */
    public void setcId(Long cId) {
        this.cId = cId;
    }

    /**
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName
     *            the clientName to set
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return the clientId
     */
    public Long getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     *            the clientId to set
     */
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

	public String getAllocation() {
		return allocation;
	}

	public void setAllocation(String allocation) {
		this.allocation = allocation;
	}

	public String getAwake() {
		return awake;
	}

	public void setAwake(String awake) {
		this.awake = awake;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
    
  
}
