package com.welloculus.apigateway.constants;

public final class Constants {

	private Constants() {
	}

	public static final String ERRORMESSAGE = "errorMessage";
	public static final String SUCCESS = "success";
	public static final String RESPONSE_CODE = "responseCode";
	public static final String SUCCESSCODE = "200";
	public static final String YES = "YES";
	public static final String NO = "N";

	public static final String JSON_CONTENT = "application/json";
	public static final String ACCEPT = "accept";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String RESPONSE_CONTENT = "responseContent";

	public static final String DEVICE_ID = "deviceId";
	public static final String USER_ID = "userId";
	public static final String PATIENT_ID = "patientId";
	public static final String ROLE = "role";
	public static final String DATA_TYPE = "data_type";
	public static final String START_DATE = "start_date";
	public static final String END_DATE = "end_date";
	public static final String QUERY = "query";
	
	public static final class ApiPath {
		private ApiPath() {
		}

		public static final String HEALTH_RECORDS = "/ApiGateway/{role}/healthRecords";
		public static final String WELLIE = "/ApiGateway/wellie";
		public static final String PATIENTS = "/ApiGateway/{role}/patients";
		public static final String DEVICES = "/ApiGateway/{role}/devices";

		public static final String GET_HEALTH_RECORDS = "/getHealthRecords";
		public static final String WELLIE_QUERY = "/query";
		
		public static final String GET_PATIENTS = "/getPatients";
		public static final String GET_PATIENT_BY_ID = "/getPatient";
		public static final String UPDATE_PATIENT_BY_ID = "/updatePatient";
		public static final String DELETE_PATIENT_BY_ID = "/deletePatient";
		public static final String ADD_PATIENT = "/addPatient";
		
		public static final String GET_DEVICES = "/getDevices";
		public static final String GET_DEVICE_BY_ID = "/getDevice";
		public static final String UPDATE_DEVICE_BY_ID = "/updateDevice";
		public static final String DELETE_DEVICE_BY_ID = "/deleteDevice";
		public static final String ADD_DEVICE = "/addDevice";
	}
}
