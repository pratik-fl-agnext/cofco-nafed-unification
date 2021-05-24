package com.agnext.unification.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.agnext.unification.common.Constants;
import com.agnext.unification.config.AnalyticsVariations;
import com.agnext.unification.model.Analytics;
import com.agnext.unification.model.PhysicalScanModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class GeneratePhysicalScanPdf {

    //	 public static void main(String[] args)throws Exception {
    //		 new GeneratePhysicalScanPdf().generatePhysicalScanPdf(null);
    //	        String url = "scan.pdf";
    //
    //	        if(Desktop.isDesktopSupported()){
    //	            Desktop desktop = Desktop.getDesktop();
    //	            try {
    //	                desktop.browse(new URI(url));
    //	            } catch (IOException | URISyntaxException e) {
    //	                // TODO Auto-generated catch block
    //	                e.printStackTrace();
    //	            }
    //	        }else{
    //	            Runtime runtime = Runtime.getRuntime();
    //	            try {
    //	                runtime.exec("xdg-open " + url);
    //	            } catch (IOException e) {
    //	                // TODO Auto-generated catch block
    //	                e.printStackTrace();
    //	            }
    //	        }
    //	    }

    //	 List<AnalysisData> analysisData = null;
    //	 {
    //		 analysisData = new ArrayList<AnalysisData>();
    //		 analysisData.add(new AnalysisData("Defective Analysis", "Defect Count", "15", "Count in numbers"));
    //		 analysisData.add(new AnalysisData("Mitigation Analysis", "Mitigation SRN Value", "1.5", "In Grams"));
    //		 analysisData.add(new AnalysisData("Residual Complexity Analysis", "Residual Evoporation Technique", "35", "In Percentage"));
    //		 analysisData.add(new AnalysisData("Gama Verification Analysis", "RDG Gama-SRN-234", "3.9", "In GBP"));
    //		 analysisData.add(new AnalysisData("Defective Analysis", "Defect Count", "15", "Count in numbers"));
    //		 analysisData.add(new AnalysisData("Mitigation Analysis", "Mitigation SRN Value", "1.5", "In Grams"));
    //		 analysisData.add(new AnalysisData("Residual Complexity Analysis", "Residual Evoporation Technique", "35", "In Percentage"));
    //		 analysisData.add(new AnalysisData("Gama Verification Analysis", "RDG Gama-SRN-234", "3.9", "In GBP"));
    //		 analysisData.add(new AnalysisData("Defective Analysis", "Defect Count", "15", "Count in numbers"));
    //		 analysisData.add(new AnalysisData("Mitigation Analysis", "Mitigation SRN Value", "1.5", "In Grams"));
    //		 analysisData.add(new AnalysisData("Residual Complexity Analysis", "Residual Evoporation Technique", "35", "In Percentage"));
    //		 analysisData.add(new AnalysisData("Gama Verification Analysis", "RDG Gama-SRN-234", "3.9", "In GBP"));
    //		 
    //	 }

	 
	
    private static final int TABLE_DEFAULT_WIDTH = 530;

    public static ResponseEntity generatePhysicalScanPdf(PhysicalScanModel scan, HttpServletResponse response,
	    String tenantName, List<String> standardList, AnalyticsVariations analyticsVariations) throws Exception {

	Document document = new Document();
	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("scan-p.pdf"));
	// File file = new File("scan-p.pdf");
	// PdfWriter writer = PdfWriter.getInstance(document, new
	// FileOutputStream(file));
	document.open();
	//addLogo(document);
	createLOGOs(document);
	addReportHeading(document);
	addReportAttributes(document, scan, tenantName);
	document.add(new LineSeparator());
	addReportSubAttributes(document, scan);
	addAnalysis(document, scan,standardList,analyticsVariations);
	addDisclaimerAndQRRedirection(document, writer);

	document.close();
	File file = new File("scan-p.pdf");
	Path path = Paths.get(file.getAbsolutePath());
	ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
	HttpHeaders headers = new HttpHeaders();
	headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=scan-p.pdf");
	headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	headers.add("Pragma", "no-cache");
	headers.add("Expires", "0");

	return ResponseEntity.ok().headers(headers).contentLength(file.length())
		.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);

    }

    public static void createLOGOs(Document document) throws IOException, DocumentException {
	PdfPTable table = new PdfPTable(4);
	table.setWidthPercentage(100);
	float[] columnWidths = { 0.02f, 0.05f, 0.05f, 0.04f };
	table.setWidths(columnWidths);

	table.addCell(createNafedImageCell());
	table.addCell(createCell("", null, Element.ALIGN_RIGHT, null, 10));
	table.addCell(createCell("", null, Element.ALIGN_RIGHT, null, 10));
	table.addCell(createImageCell());
	document.add(table);
    }

    public static PdfPCell createNafedImageCell() throws DocumentException, IOException {
	Resource resource = new ClassPathResource("/images/nafed_logo.png", GeneratePhysicalScanPdf.class);
	Image image = Image.getInstance(resource.getURL());

	PdfPCell cell = new PdfPCell(image, true);
	Paragraph p = new Paragraph("");
	p.setAlignment(Element.ALIGN_LEFT);
	p.add(image);
	cell.addElement(p);
	cell.setBorder(PdfPCell.NO_BORDER);
	return cell;
    }

    public static PdfPCell createImageCell() throws DocumentException, IOException {

	Resource resource = new ClassPathResource("/images/logo.png", GeneratePhysicalScanPdf.class);
	Image image = Image.getInstance(resource.getURL());

	PdfPCell cell = new PdfPCell(image, true);
	Paragraph p = new Paragraph("");
	p.setAlignment(Element.ALIGN_LEFT);
	p.add(image);
	cell.addElement(p);
	cell.setBorder(PdfPCell.NO_BORDER);
	cell.setPaddingTop(15f);
	return cell;
    }

    private static String disclaimerLabel = "The models used to create this prediction have been built by AgNext technologies Pvt Ltd using its best endeavours. As"
	    + " new data becomes available, new model versions may be created to improve model accuracy, and therefore results with"
	    + " future models may differ from those made with the current models. While AgNext and the model owner have used their"
	    + " best endeavours to provide accurate predictions, neither AgNext nor the model owner provide any assurance of their"
	    + " accuracy. AgNext and the model owner accept no liability for decisions made as a consequence of using the predictions"
	    + " from these models.";

    private static void addDisclaimerAndQRRedirection(Document document, PdfWriter writer) throws Exception {
	PdfPTable table = new PdfPTable(4);
	Font data = new Font(Font.getFamily(FontFactory.COURIER_BOLD), 7, Font.BOLDITALIC);

	BaseColor bgColor = new BaseColor(213, 213, 213);
	int cellHeight = 100;

	table.setSpacingBefore(200f);
	float[] columnWidths = { 0.01f, 1.2f, 0.4f, 0.03f };
	table.setWidths(columnWidths);

	table.setLockedWidth(true);
	table.addCell(createCell("", data, Element.ALIGN_RIGHT, bgColor, cellHeight));
	PdfPCell cell = createCell(disclaimerLabel, data, Element.ALIGN_LEFT, bgColor, cellHeight);
	//cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	table.addCell(cell);
	table.addCell(createCell(
		"For support contact us" + "\n" + "Phone: 9700720005" + "\n" + " Email: nafed_support@agnext.in", data,
		Element.ALIGN_LEFT, bgColor, cellHeight));
	table.addCell(createCellWithQRCode(data, Element.ALIGN_LEFT, bgColor, cellHeight, writer));
	table.addCell(createCell("", data, Element.ALIGN_CENTER, bgColor, cellHeight));

	table.setTotalWidth(PageSize.A4.getWidth());
	table.setLockedWidth(true);

	PdfContentByte canvas = writer.getDirectContent();
	canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
	table.writeSelectedRows(0, -1, 0, 90, canvas);
	canvas.endMarkedContentSequence();

    }

    private static PdfPCell createCellWithQRCode(Font font, int alignment, BaseColor backgroundColor, int height,
	    PdfWriter writer) throws Exception {
	//		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	//		Resource resources = resolver.getResource("classpath*:test/*.json");

	//		Resource resource = new ClassPathResource("/images/qr-code.png", GeneratePhysicalScanPdf.class);
	//		Image image = null;
	//		image.scaleToFit(80, 80);
	PdfPCell cell = new PdfPCell();
	cell.setBackgroundColor(backgroundColor);
	cell.setHorizontalAlignment(alignment);
	if (alignment == Element.ALIGN_CENTER)
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	cell.setPaddingTop(5);
	cell.setPaddingBottom(10);
	cell.setBorder(PdfPCell.NO_BORDER);
	cell.setMinimumHeight(height);
	return cell;
    }

    private Image getQRCodeImage(PdfWriter writer) throws Exception {
	// Image image =
	// Image.getInstance(ClassLoader.getSystemResource("images/qr-code.png"));
	//		Resource resource = new ClassPathResource("/images/qr-code.png", GeneratePhysicalScanPdf.class);
	//		Image image = Image.getInstance(resource.getURL());
	//		image.scaleToFit(80, 80);
	//		float width = image.getScaledWidth();
	//		float height = image.getScaledHeight();
	//		PdfTemplate template = writer.getDirectContent().createTemplate(width, height);
	//		template.ellipse(0, 0, width, height);
	//		template.clip();
	//		template.newPath();
	//		template.addImage(image, width, 0, 0, height, 0, 0);
	//		Image clipped = Image.getInstance(template);
	//		return clipped;
	return null;
    }

    private static void addAnalysis(Document document, PhysicalScanModel scanVO, List<String> standardList, AnalyticsVariations analyticsVariations) throws Exception {
	//PdfPTable table = new PdfPTable(4);
	PdfPTable table = new PdfPTable(2);
	Font heading = new Font(Font.getFamily("TIMES_ROMAN"), 13, Font.BOLD);
	heading.setColor(BaseColor.WHITE);
	Font data = new Font(Font.getFamily("TIMES_ROMAN"), 11, Font.NORMAL);
	BaseColor[] bgColors = { BaseColor.WHITE, new BaseColor(228, 229, 233) };

	table.setSpacingBefore(35f);
	//float[] columnWidths = { 0.7f, 0.7f, 0.4f, 0.4f };
	float[] columnWidths = { 0.7f, 0.7f };

	table.setWidths(columnWidths);

	table.setTotalWidth(TABLE_DEFAULT_WIDTH);
	table.setLockedWidth(true);
	BaseColor headingBgColor = new BaseColor(94, 94, 94);
	table.addCell(createCell("Analysis", heading, Element.ALIGN_CENTER, headingBgColor));
	//table.addCell(createCell("Method", heading, Element.ALIGN_CENTER, headingBgColor));
	table.addCell(createCell("Result", heading, Element.ALIGN_CENTER, headingBgColor));
	//table.addCell(createCell("Unit", heading, Element.ALIGN_CENTER, headingBgColor));
	List<Analytics> analysisDatas = scanVO.getsAnalysisResults();
	if (analysisDatas != null && analysisDatas.size() > 0) {
	    for (int i = 0; i < analysisDatas.size(); i++) {
		if (analysisDatas.get(i).getAnalysisName().equalsIgnoreCase("moisturecontent")
			&& !analysisDatas.get(0).getAnalysisName().equalsIgnoreCase("moisturecontent")) {
		    Collections.swap(analysisDatas, 0, i);
		} else if (analysisDatas.get(i).getAnalysisName().equalsIgnoreCase("shelling")
			&& !analysisDatas.get(1).getAnalysisName().equalsIgnoreCase("shelling")) {
		    Collections.swap(analysisDatas, 1, i);
		}
	    }
	}

	if (analysisDatas != null && analysisDatas.size() > 0) {
		Boolean moisturePresent = false;
		BigDecimal moisture = null;
//List<Analytics> quality= new ArrayList<Analytics>();
		for ( int i=0;i<analysisDatas.size();i++) {
			Analytics result= analysisDatas.get(i);
			
			if (result.getAnalysisName() != null) {
				Analytics analyticsNew= new Analytics();
				
				if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.ADMIXTURE.getAnalytics()).contains(result.getAnalysisName())) {
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAmountUnit("%");
					analyticsNew.setAnalysisName(Constants.ANALYTICS.ADMIXTURE.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.DAMAGED.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAmountUnit("%");
					analyticsNew.setAnalysisName(Constants.ANALYTICS.DAMAGED.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				}

				else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.FOREIGNMATTER.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAmountUnit("%");
					analyticsNew.setAnalysisName(Constants.ANALYTICS.FOREIGNMATTER.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (result.getAnalysisName().trim().equalsIgnoreCase("immature")) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAmountUnit("%");
					analyticsNew.setAnalysisName(result.getAnalysisName());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.MOISTURECONTENT.getAnalytics()).contains(result.getAnalysisName())) {
					
					 moisture = result.getResult();
					    moisturePresent = true;
					
						if (moisture != null) {
							analyticsNew.setAmountUnit("");
							analyticsNew.setAnalysisName(Constants.ANALYTICS.MOISTURECONTENT.getAbbr());
							analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(moisture)));
//							analyticsNew.setTotalAmount(String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(moisture))));
						}
					    
//					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
//					analyticsNew.setAmountUnit("%");
//					analyticsNew.setAnalysisName(customAnalyticsModel.getMoisturecontent());
//					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));


				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.PODSOFOTHERVAR.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAmountUnit("%");
					analyticsNew.setAnalysisName(Constants.ANALYTICS.PODSOFOTHERVAR.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SHELLING.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAmountUnit("%");
					analyticsNew.setAnalysisName(Constants.ANALYTICS.SHELLING.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SHRIVELLEDANDIMMATURE.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAmountUnit("%");
					analyticsNew.setAnalysisName(Constants.ANALYTICS.SHRIVELLEDANDIMMATURE.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SLIGHTLYDAMAGED.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAmountUnit("%");
					analyticsNew.setAnalysisName(Constants.ANALYTICS.SLIGHTLYDAMAGED.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.WEEVILLED.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAmountUnit("%");
					analyticsNew.setAnalysisName(Constants.ANALYTICS.WEEVILLED.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				} else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SPLITCRACKED.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAmountUnit("%");
					analyticsNew.setAnalysisName(Constants.ANALYTICS.SPLITCRACKED.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				}
				else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.DAMAGEDANDWEEVILLED.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAmountUnit("%");
					analyticsNew.setAnalysisName(Constants.ANALYTICS.DAMAGEDANDWEEVILLED.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//					analyticsNew.setTotalAmount(
//						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//					if (map != null && map.containsKey(result.getAnalysisName())) {
//						analyticsNew.setByDensityResult(Utility
//						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//					}

				}
//               quality.add(analyticsNew);
				if (analyticsNew.getResult() != null) {
				    BaseColor bgColor = bgColors[i % 2];
					
				    table.addCell(createCell(analyticsNew.getAnalysisName().toUpperCase(), data, Element.ALIGN_CENTER,
					    bgColor));
				    //table.addCell(createCell("Image processing", data, Element.ALIGN_CENTER, bgColor));

				    table.addCell(createCell(analyticsNew.getResult().toString().concat("%"), data,
					    Element.ALIGN_CENTER, bgColor));
				    //table.addCell(createCell("%", data, Element.ALIGN_CENTER, bgColor));
			}	
			
		}
		
//	    for (int i = 0; i < analysisDatas.size(); i++) {
//		Analytics analysisData = analysisDatas.get(i);
//
//		if (analysisData.getResult() != null) {
//		    BaseColor bgColor = bgColors[i % 2];
//		    table.addCell(createCell(analysisData.getAnalysisName().toUpperCase(), data, Element.ALIGN_CENTER,
//			    bgColor));
//		    //table.addCell(createCell("Image processing", data, Element.ALIGN_CENTER, bgColor));
//
//		    table.addCell(createCell(analysisData.getResult().toString().concat("%"), data,
//			    Element.ALIGN_CENTER, bgColor));
//		    //table.addCell(createCell("%", data, Element.ALIGN_CENTER, bgColor));
//		}
	    }
	}

	table.setSpacingAfter(5);

	document.add(table);

    }

    private static void addReportSubAttributes(Document document, PhysicalScanModel scan) throws Exception {
	PdfPTable table = new PdfPTable(2);
	Font heading = new Font(Font.getFamily("TIMES_ROMAN"), 12, Font.BOLD);
	Font data = new Font(Font.getFamily("TIMES_ROMAN"), 10, Font.NORMAL);

	table.setSpacingBefore(12f);
	float[] columnWidths = { 1.0f, 1.0f };
	table.setWidths(columnWidths);

	table.setTotalWidth(TABLE_DEFAULT_WIDTH);
	table.setLockedWidth(true);
	table.addCell(createCell("Sample ID : ", heading, Element.ALIGN_LEFT));
	table.addCell(createCell(scan.getSampleId(), data, Element.ALIGN_RIGHT));
	table.addCell(createCell("Commodity : ", heading, Element.ALIGN_LEFT));
	table.addCell(createCell(scan.getCommodityName(), data, Element.ALIGN_RIGHT));
	table.addCell(createCell("Variety : ", heading, Element.ALIGN_LEFT));
	table.addCell(createCell(scan.getVariety(), data, Element.ALIGN_RIGHT));
	table.addCell(createCell("Truck Number : ", heading, Element.ALIGN_LEFT));
	table.addCell(createCell(scan.getTruckNumber(), data, Element.ALIGN_RIGHT));
	table.addCell(createCell("Sample Weight : ", heading, Element.ALIGN_LEFT));
	table.addCell(createCell(scan.getSampleWeight().toString() + " " + scan.getSampleWeightUnit(), data,
		Element.ALIGN_RIGHT));
	//	table.addCell(createCell("Batch Id : ", heading, Element.ALIGN_LEFT));
	//	table.addCell(createCell(scan.getBatchId(), data, Element.ALIGN_RIGHT));
	table.addCell(createCell("Test Date : ", heading, Element.ALIGN_LEFT));
	LocalDateTime date = Instant.ofEpochMilli(scan.getCreatedOn()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	table.addCell(createCell(Utility.formatLocalMonthDateTimeToString(date), data, Element.ALIGN_RIGHT));
	table.setSpacingAfter(5);

	document.add(table);

    }

    private static void addReportAttributes(Document document, PhysicalScanModel scan, String tenantName)
	    throws Exception {
	PdfPTable table = new PdfPTable(4);
	Font heading = new Font(Font.getFamily("TIMES_ROMAN"), 12, Font.BOLD);
	Font data = new Font(Font.getFamily("TIMES_ROMAN"), 10, Font.NORMAL);

	table.setSpacingBefore(12f);
	float[] columnWidths = { 0.5f, 1.5f, 0.5f, 0.6f };
	table.setWidths(columnWidths);

	table.setTotalWidth(TABLE_DEFAULT_WIDTH);
	table.setLockedWidth(true);
	table.addCell(createCell("", heading, Element.ALIGN_LEFT));
	table.addCell(createCell("", data, Element.ALIGN_LEFT));
	//	if (scan.getDeviceType().equalsIgnoreCase("Visio")) {
	//	    if (tenantName != null && !tenantName.isEmpty())
	//		table.addCell(createCell("Physical Test For " + tenantName, data, Element.ALIGN_LEFT));
	//	    else {
	//		table.addCell(createCell("Physical Test ", data, Element.ALIGN_LEFT));
	//	    }
	//	} else {
	//	    if (tenantName != null && !tenantName.isEmpty())
	//		table.addCell(createCell("Chemical Test For " + tenantName, data, Element.ALIGN_LEFT));
	//	    else {
	//		table.addCell(createCell("Chemical Test ", data, Element.ALIGN_LEFT));
	//	    }
	//	}
	table.addCell(createCell("Date : ", heading, Element.ALIGN_RIGHT));
	table.addCell(createCell(Utility.getCurrentMonthDateTimeString(), data, Element.ALIGN_RIGHT));
	table.addCell(createCell("", heading, Element.ALIGN_LEFT));
	table.addCell(createCell("", data, Element.ALIGN_LEFT));
	table.addCell(createCell("Device Id : ", heading, Element.ALIGN_RIGHT));
	table.addCell(createCell(scan.getDeviceSerialNumber(), data, Element.ALIGN_RIGHT));

	table.setSpacingAfter(5);

	document.add(table);

    }

    private static PdfPCell createCell(String label, Font font, int alignment) {
	PdfPCell cell = new PdfPCell(new Phrase(label, font));
	cell.setPaddingTop(7);
	cell.setHorizontalAlignment(alignment);
	cell.setBorder(PdfPCell.NO_BORDER);
	return cell;
    }

    private static PdfPCell createCell(String label, Font font, int alignment, BaseColor backgroundColor) {
	PdfPCell cell = new PdfPCell(new Phrase(label, font));
	cell.setBackgroundColor(backgroundColor);
	cell.setHorizontalAlignment(alignment);
	if (alignment == Element.ALIGN_CENTER)
	    cell.setVerticalAlignment(alignment);
	cell.setPaddingTop(5);
	cell.setPaddingBottom(10);
	cell.setBorder(PdfPCell.NO_BORDER);
	return cell;
    }

    private static PdfPCell createCell(String label, Font font, int alignment, BaseColor backgroundColor, int height) {
	PdfPCell cell = createCell(label, font, alignment, backgroundColor);
	cell.setMinimumHeight(height);
	return cell;
    }

    private static void addReportHeading(Document document) throws Exception {
	Font font = new Font(Font.getFamily("TIMES_ROMAN"), 15, Font.BOLD | Font.UNDERLINE);
	Paragraph heading = new Paragraph("Test Report", font);
	heading.setAlignment(Element.ALIGN_CENTER);
	document.add(heading);

    }

    private static void addLogo(Document document) throws Exception {
	Resource resource = new ClassPathResource("/images/logo.png",
		com.agnext.unification.utility.GeneratePhysicalScanPdf.class);
	Image image = Image.getInstance(resource.getURL());
	image.scaleToFit(150, 150);
	Paragraph logo = new Paragraph("");
	logo.add(image);
	document.add(logo);
    }

    class AnalysisData {

	String analysisName, analysisMethod, result, unit;

	public AnalysisData(String analysisName, String analysisMethod, String result, String unit) {
	    this.analysisName = analysisName;
	    this.analysisMethod = analysisMethod;
	    this.result = result;
	    this.unit = unit;
	}

	public String getAnalysisName() {
	    return analysisName;
	}

	public void setAnalysisName(String analysisName) {
	    this.analysisName = analysisName;
	}

	public String getAnalysisMethod() {
	    return analysisMethod;
	}

	public void setAnalysisMethod(String analysisMethod) {
	    this.analysisMethod = analysisMethod;
	}

	public String getResult() {
	    return result;
	}

	public void setResult(String result) {
	    this.result = result;
	}

	public String getUnit() {
	    return unit;
	}

	public void setUnit(String unit) {
	    this.unit = unit;
	}

    }

}
