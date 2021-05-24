package com.agnext.unification.utility;

import org.springframework.stereotype.Component;

@Component
public class S3Operations {/*

	private S3Settings settings;
	private static final String DATETIME = "dateTime";

	private S3Service s3Service;
	private S3Bucket s3Bucket;
	@Autowired
	UserRepository userRepo;
	public static final Logger log = LoggerFactory.getLogger(S3Operations.class);

	*//**
	 * Default constructor. This will authenticate the S3 service.
	 * 
	 * @param settings S3Settings object
	 * @throws S3ServiceException Exception for use by S3Services and related
	 *                            utilities. This exception can hold useful
	 *                            additional information about errors that occur
	 *                            when communicating with S3.
	 *//*
	@Autowired
	public S3Operations(S3Settings settings) throws S3ServiceException {
		this.settings = settings;

		AWSCredentials awsCredentials = new AWSCredentials(settings.getKey().trim(), settings.getSecret().trim());
		s3Service = new RestS3Service(awsCredentials);
		s3Bucket = s3Service.getBucket(settings.getBucket() + Constants.SEPARATOR + settings.getEnvironment());
	}

	*//**
	 * This method will upload images to S3 storage. All parameters are mandatory to
	 * be passed for this service to work.
	 * 
	 * @param mpf        Multipart File to upload.
	 * @param entityId   Id of the entity.
	 * @param userId     Id of the user.
	 * @param entityType type of entity. see {@link Constants.ENTITY_TYPE}
	 * 
	 * @return S3Object that is uploaded.
	 * @throws S3ServiceException Exception for use by S3Services and related
	 *                            utilities. This exception can hold useful
	 *                            additional information about errors that occur
	 *                            when communicating with S3. algorithm is requested
	 *                            but is not available in the environment.
	 * @throws IOException        Signals that an I/O exception of some sort has
	 *                            occurred. This class is the general class of
	 *                            exceptions produced by failed or interrupted I/O
	 *                            operations.
	 *//*
	public S3Object uploadAttachment(MultipartFile mpf, Long entityId, Long userId, String entityType)
			throws S3ServiceException, IOException {
		InputStream stream = new ByteArrayInputStream(mpf.getBytes());
		if (s3Bucket == null) {
			s3Bucket = s3Service.getBucket(settings.getBucket());
		}
		if (mpf.getOriginalFilename() != null) {
			String name = mpf.getOriginalFilename().replaceAll(" ", "_");
			String extn = name.substring(name.lastIndexOf(Constants.PERIOD), name.length());
			String fileName = Instant.now().getEpochSecond() + extn;
			String filePath = settings.getEnvironment() + Constants.SEPARATOR + userId + Constants.SEPARATOR
					+ entityType + Constants.SEPARATOR + entityId + Constants.SEPARATOR + fileName;

			S3Object s3Object = new S3Object(filePath);
			s3Object.setDataInputStream(stream);
			s3Object.setContentLength(mpf.getBytes().length);
			s3Object.setContentType(Utility.getContentTypeByFileName(fileName));
			S3Object s3Obj = s3Service.putObject(s3Bucket, s3Object);
			stream.close();
			return s3Obj;
		}
		return new S3Object();
	}

	*//**
	 * This method will upload images to S3 storage. All parameters are mandatory to
	 * be passed for this service to work.
	 * 
	 * @param baoStream ByteArrayOutputStream of the uploaded file.
	 * @param userId    Id of the user.
	 * 
	 * @return S3Object that is uploaded.
	 * @throws S3ServiceException Exception for use by S3Services and related
	 *                            utilities. This exception can hold useful
	 *                            additional information about errors that occur
	 *                            when communicating with S3. algorithm is requested
	 *                            but is not available in the environment.
	 * @throws IOException        Signals that an I/O exception of some sort has
	 *                            occurred. This class is the general class of
	 *                            exceptions produced by failed or interrupted I/O
	 *                            operations.
	 *//*
	public S3Object uploadQRCode(ByteArrayOutputStream baoStream, Long userId)
			throws S3ServiceException, IOException {
		InputStream stream = new ByteArrayInputStream(baoStream.toByteArray());
		if (s3Bucket == null) {
			s3Bucket = s3Service.getBucket(settings.getBucket());
		}

		String filePath = settings.getEnvironment() + Constants.SEPARATOR + "users" + Constants.SEPARATOR + userId
				+ Constants.SEPARATOR + Constants.QRCODE_IMAGE_NAME;
		UserEntity u = userRepo.getOne(userId);
		u.setQrCodePath(filePath);
		userRepo.save(u);
		S3Object s3Object = new S3Object(filePath);
		s3Object.setDataInputStream(stream);
		s3Object.setContentLength(baoStream.size());
		s3Object.setContentType("image/png");
		S3Object s3Obj = s3Service.putObject(s3Bucket, s3Object);
		stream.close();
		return s3Obj;
	}

	public S3Object uploadFarmerQRCode(ByteArrayOutputStream baoStream, Long userId)
			throws S3ServiceException, IOException {
		InputStream stream = new ByteArrayInputStream(baoStream.toByteArray());
		if (s3Bucket == null) {
			s3Bucket = s3Service.getBucket(settings.getBucket());
		}

		String filePath = settings.getEnvironment() + Constants.SEPARATOR + "farmers" + Constants.SEPARATOR + userId
				+ Constants.SEPARATOR + Constants.QRCODE_IMAGE_NAME;

		S3Object s3Object = new S3Object(filePath);
		s3Object.setDataInputStream(stream);
		s3Object.setContentLength(baoStream.size());
		s3Object.setContentType("image/png");
		S3Object s3Obj = s3Service.putObject(s3Bucket, s3Object);
		stream.close();
		return s3Obj;
	}

	*//**
	 * This method will upload profile image to S3 storage. All parameters are
	 * mandatory to be passed for this service to work.
	 * 
	 * @param mpf    Multipart File to upload.
	 * @param userId Id of the User.
	 * 
	 * @return S3Object that is uploaded.
	 * @throws S3ServiceException Exception for use by S3Services and related
	 *                            utilities. This exception can hold useful
	 *                            additional information about errors that occur
	 *                            when communicating with S3. algorithm is requested
	 *                            but is not available in the environment.
	 * @throws IOException        Signals that an I/O exception of some sort has
	 *                            occurred. This class is the general class of
	 *                            exceptions produced by failed or interrupted I/O
	 *                            operations.
	 *//*

	public S3Object uploadProfileImage(MultipartFile mpf, Long userId) throws S3ServiceException, IOException {
		InputStream stream = new ByteArrayInputStream(mpf.getBytes());
		if (s3Bucket == null) {
			s3Bucket = s3Service.getBucket(settings.getBucket());
		}

		String fileName = Constants.PROFILE_IMAGE_NAME;
		String filePath = settings.getEnvironment() + Constants.SEPARATOR + "users" + Constants.SEPARATOR + userId
				+ Constants.SEPARATOR + Constants.PROFILE_IMAGE_NAME;

		S3Object s3Object = new S3Object(filePath);
		s3Object.setDataInputStream(stream);
		s3Object.setContentLength(mpf.getBytes().length);
		s3Object.setContentType(Utility.getContentTypeByFileName(fileName));
		S3Object s3Obj = s3Service.putObject(s3Bucket, s3Object);
		stream.close();
		return s3Obj;
	}

	public S3Object uploadChemicalScan(MultipartFile mpf, String batchId, Long clientId)
			throws IOException, S3ServiceException {
		InputStream stream = new ByteArrayInputStream(mpf.getBytes());
		if (s3Bucket == null) {
			s3Bucket = s3Service.getBucket(settings.getBucket());
		}

		String name = mpf.getOriginalFilename().replaceAll(" ", "_");

		String filePath = settings.getEnvironment() + Constants.SEPARATOR + clientId + Constants.SEPARATOR + batchId
				+ Constants.SEPARATOR + name;

		S3Object s3Object = new S3Object(filePath);
		s3Object.setDataInputStream(stream);
		s3Object.setContentLength(mpf.getBytes().length);
		s3Object.setContentType(Utility.getContentTypeByFileName(name));
		S3Object s3Obj = s3Service.putObject(s3Bucket, s3Object);
		stream.close();
		return s3Obj;
	}

// Changed API for variety updation
	public S3Object uploadBetaValues(MultipartFile mpf, Long cropId, Long analyticsId, Long sampleId, Long varietyId)
			throws IOException, S3ServiceException {
		InputStream stream = new ByteArrayInputStream(mpf.getBytes());
		if (s3Bucket == null) {
			s3Bucket = s3Service.getBucket(settings.getBucket());
		}

		Long now = Instant.now().toEpochMilli();
		String name = mpf.getOriginalFilename().replaceAll(" ", "_");
		String timeName = name.replace(".", "_" + now.toString() + ".");
		String filePath = settings.getEnvironment() + Constants.SEPARATOR + "CropId_" + cropId + Constants.SEPARATOR
				+ "AnalyticId_" + analyticsId + Constants.SEPARATOR + "VarietyId_" + varietyId + Constants.SEPARATOR
				+ timeName;
		S3Object s3Object = new S3Object(filePath);
		s3Object.setDataInputStream(stream);
		s3Object.setContentLength(mpf.getBytes().length);
		s3Object.setContentType(Utility.getContentTypeByFileName(name));
		S3Object s3Obj = s3Service.putObject(s3Bucket, s3Object);
		stream.close();
		return s3Obj;
	}

	public S3Object uploadScaleFactor(MultipartFile mpf, Long deviceId, Long cropId, Long sampleId)
			throws IOException, S3ServiceException {
		InputStream stream = new ByteArrayInputStream(mpf.getBytes());
		if (s3Bucket == null) {
			s3Bucket = s3Service.getBucket(settings.getBucket());
		}

		Long now = Instant.now().toEpochMilli();
		String name = mpf.getOriginalFilename().replaceAll(" ", "_");
		String timeName = name.replace(".", "_" + now.toString() + ".");
		System.out.println("*************name*************" + name);
		String filePath = settings.getEnvironment() + Constants.SEPARATOR + "DeviceId_" + deviceId + Constants.SEPARATOR
				+ "CropId_" + cropId + Constants.SEPARATOR + timeName;
		System.out.println("*************filePath**************" + filePath);
		S3Object s3Object = new S3Object(filePath);
		s3Object.setDataInputStream(stream);
		s3Object.setContentLength(mpf.getBytes().length);
		s3Object.setContentType(Utility.getContentTypeByFileName(name));
		S3Object s3Obj = s3Service.putObject(s3Bucket, s3Object);
		stream.close();
		return s3Obj;
	}

	private static final String FILE_SUFFIX_DATE_FORMAT = "yyyyMMddHHmmss";

//	public S3Object uploadPhysicalScanImage(MultipartFile mpf,  Long physicalScanId) throws S3ServiceException, IOException {
//		InputStream stream = new ByteArrayInputStream(mpf.getBytes());
//        if (s3Bucket == null) {
//            s3Bucket = s3Service.getBucket(settings.getBucket());
//        }
//        
//        String fileName = FilenameUtils.removeExtension(mpf.getOriginalFilename())+"_"+Utility.formatDateToString(Calendar.getInstance().getTime(), FILE_SUFFIX_DATE_FORMAT);
//        String ext = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf('.') + 1);
//        String file=fileName+"."+ext;
//        String filePath = settings.getEnvironment() + Constants.SEPARATOR + "physicalscans" + Constants.SEPARATOR + physicalScanId  + Constants.SEPARATOR + file;
//
//        S3Object s3Object = new S3Object(filePath);
//        s3Object.setDataInputStream(stream);
//        s3Object.setContentLength(mpf.getBytes().length);
//        s3Object.setContentType(Utility.getContentTypeByFileName(file));
//        S3Object s3Obj = s3Service.putObject(s3Bucket, s3Object);
//        stream.close();
//        return s3Obj;
//	}

	public InputStream downloadDocument(String docName) throws ServiceException {
		S3Object objectComplete = s3Service.getObject(settings.getBucket(),
				settings.getEnvironment() + "/iot/" + docName);
		return objectComplete.getDataInputStream();
	}

	public File downloadFileFrom(String absoluteFileName) throws ServiceException {
		S3Object objectComplete = s3Service.getObject(settings.getBucket(),
				settings.getEnvironment() + absoluteFileName);
		return objectComplete.getDataInputFile();
	}

	public InputStream downloadDocument2(String absoluteFileName) throws ServiceException {
		S3Object objectComplete = s3Service.getObject(settings.getBucket(),
				settings.getEnvironment() + absoluteFileName);
		return objectComplete.getDataInputStream();
	}
*/}
