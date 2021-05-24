
package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.Objects;

//@JsonIgnoreProperties
//@JsonInclude(Include.NON_NULL)
public class Analytics {

    private String amountUnit;
    private String analysisId;
    private String totalAmount;
    private String analysisName;
    private String Curucumin;
    private BigDecimal result;
    private BigDecimal avgResult;
    private String analysisCode;
    private String dataCode;
    private String density;
    private String values;
    private BigDecimal byDensityResult;
    private Double labResultValue;

    /**
     * @return the dataCode
     */
    public String getDataCode() {
 return dataCode;
    }

    /**
     * @param dataCode
     *            the dataCode to set
     */
    public void setDataCode(String dataCode) {
 this.dataCode = dataCode;
    }

    /**
     * @return the analysisCode
     */
    public String getAnalysisCode() {
 return analysisCode;
    }

    /**
     * @param analysisCode
     *            the analysisCode to set
     */
    public void setAnalysisCode(String analysisCode) {
 this.analysisCode = analysisCode;
    }

    /**
     * @return the avgResult
     */
    public BigDecimal getAvgResult() {
 return avgResult;
    }

    /**
     * @param avgResult
     *            the avgResult to set
     */
    public void setAvgResult(BigDecimal avgResult) {
 this.avgResult = avgResult;
    }

    /**
     * @return the amountUnit
     */
    public String getAmountUnit() {
 return amountUnit;
    }

    /**
     * @param amountUnit
     *            the amountUnit to set
     */
    public void setAmountUnit(String amountUnit) {
 this.amountUnit = amountUnit;
    }

    /**
     * @return the analysisId
     */
    public String getAnalysisId() {
 return analysisId;
    }

    /**
     * @param analysisId
     *            the analysisId to set
     */
    public void setAnalysisId(String analysisId) {
 this.analysisId = analysisId;
    }

    /**
     * @return the totalAmount
     */
    public String getTotalAmount() {
 return totalAmount;
    }

    /**
     * @param totalAmount
     *            the totalAmount to set
     */
    public void setTotalAmount(String totalAmount) {
 this.totalAmount = totalAmount;
    }

    /**
     * @return the analysisName
     */
    public String getAnalysisName() {
 return analysisName;
    }

    /**
     * @param analysisName
     *            the analysisName to set
     */
    public void setAnalysisName(String analysisName) {
 this.analysisName = analysisName;
    }

    /**
     * @return the curucumin
     */
    public String getCurucumin() {
 return Curucumin;
    }

    /**
     * @param curucumin
     *            the curucumin to set
     */
    public void setCurucumin(String curucumin) {
 Curucumin = curucumin;
    }

    /**
     * @return the result
     */
    public BigDecimal getResult() {
 return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(BigDecimal result) {
 this.result = result;
    }



    public String getDensity() {
 return density;
    }

    public void setDensity(String density) {
 this.density = density;
    }

    public String getValues() {
 return values;
    }

    public void setValues(String values) {
 this.values = values;
    }

    public BigDecimal getByDensityResult() {
 return byDensityResult;
    }

    public void setByDensityResult(BigDecimal byDensityResult) {
 this.byDensityResult = byDensityResult;
    }

    public Double getLabResultValue() {
 return labResultValue;
    }

    public void setLabResultValue(Double labResultValue) {
 this.labResultValue = labResultValue;
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Analytics analytics = (Analytics) o;
//    return Objects.equals(amountUnit, analytics.amountUnit) &&
//        Objects.equals(analysisId, analytics.analysisId) &&
//        Objects.equals(totalAmount, analytics.totalAmount) &&
        return Objects.equals(analysisName, analytics.analysisName) ;
//        Objects.equals(Curucumin, analytics.Curucumin) &&
//        Objects.equals(result, analytics.result) &&
//        Objects.equals(avgResult, analytics.avgResult) &&
//        Objects.equals(analysisCode, analytics.analysisCode) &&
//        Objects.equals(dataCode, analytics.dataCode) &&
//        Objects.equals(density, analytics.density) &&
//        Objects.equals(values, analytics.values) &&
//        Objects.equals(byDensityResult, analytics.byDensityResult) &&
//        Objects.equals(labResultValue, analytics.labResultValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(analysisName);
 //       .hash(amountUnit, analysisId, totalAmount, analysisName, Curucumin, result, avgResult,
//            analysisCode, dataCode, density, values, byDensityResult, labResultValue);
  }

  @Override
  public String toString() {
    return "Analytics{" +
        "amountUnit='" + amountUnit + '\'' +
        ", analysisId='" + analysisId + '\'' +
        ", totalAmount='" + totalAmount + '\'' +
        ", analysisName='" + analysisName + '\'' +
        ", Curucumin='" + Curucumin + '\'' +
        ", result=" + result +
        ", avgResult=" + avgResult +
        ", analysisCode='" + analysisCode + '\'' +
        ", dataCode='" + dataCode + '\'' +
        ", density='" + density + '\'' +
        ", values='" + values + '\'' +
        ", byDensityResult=" + byDensityResult +
        ", labResultValue=" + labResultValue +
        '}';
  }
}

